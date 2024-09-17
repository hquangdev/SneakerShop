package ControllerAdmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDAO.ClientDAO;
import AdminEntity.Clients;
import SneakerShop.Entity.Slides;

@Controller
@RequestMapping("/admin")
public class UserControllerAdmin extends BaseAdminController{
	
	@Autowired
	ClientDAO clientDao;
//	
//	@RequestMapping("/management-client")
//	public ModelAndView listUser() {
//		
//		List<Clients> listUser = clientService.getAllUser();
//		
//		_mvAdmin.addObject("clients", listUser);
//		_mvAdmin.setViewName("admin/Clients/list");
//		return _mvAdmin;
//		
//		
//	}
	
	// chuyểm hướng tới trang sửa
	@RequestMapping(value="/manager-client/edit/{id}", method=RequestMethod.GET)
	public ModelAndView GetClientsByID(@PathVariable("id") int id) {
		Clients client = clientService.GetClientByID(id);
		
		_mvAdmin.addObject("client", client);
		_mvAdmin.setViewName("admin/Clients/edit");
		return _mvAdmin;
	}
	
	
	//chức năng sửa khách hàng
	@RequestMapping(value="/manager-client/edit/updateClient", method = RequestMethod.POST)
	public String EditClients(@ModelAttribute Clients client) {
		clientService.EditClients(client);
		return "redirect:/admin/management-client";
	}

	
	//phân trang 
	@GetMapping("/management-client")
	public ModelAndView listSlide(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    // Tính toán trang bắt đầu và số lượng mục mỗi trang
	    int pageNumber = page <= 0 ? 1 : page;
	    int pageSize = size <= 0 ? 10 : size;

	    // Lấy dữ liệu phân trang từ DAO
	    List<Clients> client = clientDao.getSlideWithPagination(pageNumber, pageSize);
	    List<Clients> listUser = clientService.getAllUser();
	    // Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
	    int totalSlide = clientDao.getTotalSlideCount();
	    int totalPages = (int) Math.ceil((double) totalSlide / pageSize);

	    // Tạo ModelAndView và thêm dữ liệu vào mô hình
	    _mvAdmin.setViewName("admin/Clients/list");
	    _mvAdmin.addObject("clients", client);
	    _mvAdmin.addObject("currentPage", pageNumber);
	    _mvAdmin.addObject("totalPages", totalPages);
	    _mvAdmin.addObject("pageSize", pageSize);

	    return _mvAdmin;
	}

	
}
