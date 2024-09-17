package ControllerAdmin;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDAO.BillsDao;
import AdminDTO.BillDetailDTO;
import AdminService.BillService;
import SneakerShop.Entity.BillDetail;
import SneakerShop.Entity.Bills;
import SneakerShop.Entity.Categorys;

@RequestMapping("/admin")
@Controller
public class BillsContronller extends BaseAdminController {

	// xóa hóa đơn
	@RequestMapping("/manager-bill/delete/{id}")
	public ModelAndView deleteBill(@PathVariable("id") int id) {
		billService.deleteBill(id);

		_mvAdmin.setViewName("redirect:/admin/manager-bill");

		return _mvAdmin;
	}

	// trả về trang chi tiết bill
	@RequestMapping(value = "/manager-bill/view-detail/{id}", method = RequestMethod.GET)
	public ModelAndView viewBillDetails(@PathVariable("id") long id_bills) {
		// Gọi service để lấy danh sách chi tiết hóa đơn theo id_bills
		List<BillDetailDTO> listBillDetails = billService.getBillDetailByID(id_bills);

		// Tạo ModelAndView và cấu hình tên view
		ModelAndView mv = new ModelAndView("/admin/Bills/listDetail");
		mv.addObject("listBill", listBillDetails);

		return mv;
	}

	// cập nhật trạng thái
	@RequestMapping(value = "/manager-bill/view-detail/updateStatus", method = RequestMethod.POST)
	public String updateStatus(@RequestParam("id") long id, @RequestParam("status") int status) {
		if (id <= 0 || (status != 1 && status != 2)) {
			// Xử lý lỗi ở đây
			return "redirect:/admin/manager-bill?error=invalidParameters";
		}
		billService.updateStatus(id, status);
		return "redirect:/admin/manager-bill";
	}

	// nút bấm quay trở lại
	@RequestMapping("/manager-bill/view-detail/bill")
	public ModelAndView retu() {

		List<Bills> ListBill = billService.getAllBil();
		_mvAdmin.addObject("listBill", ListBill);
		_mvAdmin.setViewName("/admin/Bills/list");
		return _mvAdmin;

	}
	

	// phân trang
	@GetMapping("/manager-bill")
	public ModelAndView Pagination(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size) {

		// Tính toán trang bắt đầu và số lượng mục mỗi trang
		int pageNumber = page <= 0 ? 1 : page;
		int pageSize = size <= 0 ? 8 : size;

		// Lấy dữ liệu phân trang từ DAO
		List<Bills> ListBill = billDao.getBillWithPagination(pageNumber, pageSize);

		// Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
		int totalbill = billDao.getTotalBillCount();
		int totalPages = (int) Math.ceil((double) totalbill / pageSize);

		// Tạo ModelAndView và thêm dữ liệu vào mô hình
		_mvAdmin.setViewName("/admin/Bills/list");
		_mvAdmin.addObject("listBill", ListBill);
		_mvAdmin.addObject("currentPage", pageNumber);
		_mvAdmin.addObject("totalPages", totalPages);
		_mvAdmin.addObject("pageSize", pageSize);

		return _mvAdmin;
	}
}