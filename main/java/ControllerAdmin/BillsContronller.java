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
public class BillsContronller extends BaseAdminController{
	
	@Autowired
	BillsDao billDao;
	@RequestMapping("/manager-bill")
	public ModelAndView getAllBill(){
		
		List<Bills> ListBill = 	billService.getAllBil();		
		_mvAdmin.addObject("listBill", ListBill);
		_mvAdmin.setViewName("/admin/Bills/list");
		return _mvAdmin;

	}
	
	//xóa hóa đơn
		@RequestMapping("/delete-bill/{id}")
	    public ModelAndView deleteBill(@PathVariable("id") int id) {
	        billService.deleteBill(id);

	         _mvAdmin.setViewName("redirect:/admin/management-bill"); 
	  
	        return _mvAdmin;
	    }
		
	//lấy theo id
	  @RequestMapping("/manager-bill/view-detail/{id}")
	  public ModelAndView getBillDetailByID(@PathVariable("id") long id_bills) {
        BillDetailDTO billDetail = billService.getBillDetailByID(id_bills);
        _mvAdmin.addObject("billdetail", billDetail);

        _mvAdmin.setViewName("/admin/Bills/BillDetail");
		return _mvAdmin;
        
	}
	  
	  @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	  public String updateStatus(@RequestParam("id") long id, @RequestParam("status") int status) {
		  billService.updateStatus(id, status);
		  
		  return "redirect:/admin/manager-bill";
	  }
//	// phân trang danh mục
//		@GetMapping("/manager-category")
//		public ModelAndView listSlide(
//		        @RequestParam(defaultValue = "1") int page,
//		        @RequestParam(defaultValue = "8") int size) {
//
//		    // Tính toán trang bắt đầu và số lượng mục mỗi trang
//		    int pageNumber = page <= 0 ? 1 : page;
//		    int pageSize = size <= 0 ? 8 : size;
//
//		    // Lấy dữ liệu phân trang từ DAO
//		    List<BillsDao> category = billDao.getSlideWithPagination(pageNumber, pageSize);
//
//		    // Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
//		    int totalSlide = billDao.getTotalCategoryCount();
//		    int totalPages = (int) Math.ceil((double) totalSlide / pageSize);
//
//		    // Tạo ModelAndView và thêm dữ liệu vào mô hình
//		    _mvAdmin.setViewName("admin/category/list");
//		    _mvAdmin.addObject("category", category);
//		    _mvAdmin.addObject("currentPage", pageNumber);
//		    _mvAdmin.addObject("totalPages", totalPages);
//		    _mvAdmin.addObject("pageSize", pageSize);
//
//		    return _mvAdmin;
//		}
	}