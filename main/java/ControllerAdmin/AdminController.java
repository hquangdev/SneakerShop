package ControllerAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDAO.AdminDAO;
import AdminEntity.Admins;

@RequestMapping("/admin")
@Controller
public class AdminController extends BaseAdminController {
	@Autowired
	private AdminDAO adminDAO;

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		_mvAdmin.setViewName("admin/index");
		return _mvAdmin;
	}

	@RequestMapping(value = "/manager-personnel")
	public ModelAndView EmployeeManager() {
		List<Admins> admin = adminService.getAllAdmin();

		_mvAdmin.setViewName("admin/personnel/list");
		_mvAdmin.addObject("personnel", admin);// Thiết lập tên view
		return _mvAdmin;
	}

	// phần chuyển hướng tới thêm nhân viên
	@RequestMapping(value = "/add-personnel")
	public ModelAndView addEmployeeForm() {
		_mvAdmin.setViewName("admin/personnel/add");
		_mvAdmin.addObject("personnel", new Admins());
		return _mvAdmin;

	}

	@RequestMapping(value = "/addPersonnel", method = RequestMethod.POST)
	public ModelAndView addEmployee(HttpSession session, @ModelAttribute("admin") Admins admin) {
	    Admins currentUser = (Admins) session.getAttribute("LoginInfo");

	    // Kiểm tra chiều dài số điện thoại
	    String phone = admin.getPhone();
	    if (phone.length() > 9) {
	        return new ModelAndView("admin/personnel/add")
	            .addObject("status", "Số điện thoại quá dài.")
	            .addObject("employee", admin);
	    }

	    // Kiểm tra quyền admin
	    if (currentUser != null && currentUser.getRole() == 1) {
	        try {
	            String status = adminService.addEmployee(admin);

	            if (status.equals("Tên tài khoản đã tồn tại")) {
	                // Trả về trang thêm nhân viên kèm theo thông báo lỗi
	                ModelAndView mv = new ModelAndView("admin/personnel/add");
	                mv.addObject("status", status);
	                mv.addObject("employee", admin);
	                return mv;
	            } else {
	                // Nếu thành công, chuyển hướng về trang quản lý nhân viên
	                return new ModelAndView("redirect:/admin/manager-personnel");
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Ghi chi tiết lỗi
	            ModelAndView mv = new ModelAndView("admin/personnel/add");
	            mv.addObject("status", "Có lỗi xảy ra: " + e.getMessage());
	            mv.addObject("employee", admin);
	            return mv;
	        }
	    } else {
	    	
	        return new ModelAndView("admin/personnel/add")
	        .addObject("status", "Bạn không có quyền thêm")
            .addObject("employee", admin);
	    }
	}


	// Phân quyền xóa nhân viên
	@RequestMapping(value = "/manager-personnel/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpSession session, @PathVariable("id") int id) {
		Admins currentUser = (Admins) session.getAttribute("LoginInfo");

		if (currentUser != null && currentUser.getRole() == 1) { // Admin
			adminService.deleteEmployee(id);
			_mvAdmin.setViewName("redirect:/admin/manager-personnel");
		}
		return _mvAdmin;
	}

	// Chuyển tới trang sửa nhân viên
	@RequestMapping(value = "/manager-personnel/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpSession session, @PathVariable("id") int id, @RequestParam(value = "status", required = false) String status) {
	    Admins currentUser = (Admins) session.getAttribute("LoginInfo");
	    List<Admins> admin2 = adminService.getAllAdmin();
	    
	    if (currentUser != null && currentUser.getRole() == 1) { // Admin
	        Admins admin = adminService.getAdminId(id);
	        ModelAndView mv = new ModelAndView("admin/personnel/edit");
	        mv.addObject("personnel", admin);
	        if (status != null) {
	            mv.addObject("status", status);
	        }
	        return mv;
	    } else {
	        return new ModelAndView("redirect:/admin/manager-personnel")
	        		 .addObject("status", "Bạn không có quyền sửa")
	                .addObject("personnel", admin2);
	    }
	}


	// chuc năng sửa nhân viên
	@RequestMapping(value = "/manager-personnel/edit/update-personnel", method = RequestMethod.POST)
	public String updatePersonnel(HttpSession session, @RequestParam("id") int id,
			@ModelAttribute("personnel") Admins admin) throws IOException {

		Admins currentUser = (Admins) session.getAttribute("LoginInfo");

		if (currentUser != null && currentUser.getRole() == 1) { // Admin
			admin.setId(id);
		
			adminService.editEmployee(admin);

			return "redirect:/admin/manager-personnel";
		} else {
			return null;
		}
	}

	@GetMapping("/manager-personnel")
	public ModelAndView listPersonnel(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {

		// Tính toán trang bắt đầu và số lượng mục mỗi trang
		int pageNumber = page <= 0 ? 1 : page;
		int pageSize = size <= 0 ? 10 : size;

		// Lấy dữ liệu phân trang từ DAO
		List<Admins> employees = adminDAO.getEmployeesWithPagination(pageNumber, pageSize);

		// Đếm tổng số nhân viên để hiển thị số trang và điều hướng phân trang
		int totalEmployees = adminDAO.getTotalEmployeeCount();
		int totalPages = (int) Math.ceil((double) totalEmployees / pageSize);

		// Tạo ModelAndView và thêm dữ liệu vào mô hình
		_mvAdmin.setViewName("admin/personnel/list");
		_mvAdmin.addObject("personnel", employees);
		_mvAdmin.addObject("currentPage", pageNumber);
		_mvAdmin.addObject("totalPages", totalPages);
		_mvAdmin.addObject("pageSize", pageSize);

		return _mvAdmin;
	}

}