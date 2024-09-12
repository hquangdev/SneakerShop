package ControllerAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDAO.AdminDAO;
import AdminEntity.Admins;
import AdminService.AdminServiceImpl;

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

	// Phân quyền thêm nhân viên
	@RequestMapping(value = "/addPersonnel", method = RequestMethod.POST)
	public ModelAndView addEmployee(HttpSession session, @ModelAttribute("admin") Admins admin) {
		Admins currentUser = (Admins) session.getAttribute("LoginInfo");

		if (currentUser != null && currentUser.getRole() == 1) { // Admin
			String status = adminService.addEmployee(admin);

			if (status.equals("Tên tài khoản đã tồn tại")) {
				ModelAndView mv = new ModelAndView("admin/personnel/add");
				mv.addObject("status", status);
				mv.addObject("employee", admin);
				return mv;
			} else {
				return new ModelAndView("redirect:/admin/manager-personnel");
			}
		} else {
			return new ModelAndView("error/403"); // Trang lỗi quyền truy cập
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
	public ModelAndView editEmployee(HttpSession session, @PathVariable("id") int id) {
		Admins currentUser = (Admins) session.getAttribute("LoginInfo");

		if (currentUser != null && currentUser.getRole() == 1) { // Admin
			Admins admin = adminService.getAdminId(id);
			ModelAndView mv = new ModelAndView("admin/personnel/edit");
			mv.addObject("personnel", admin);
			return mv;
		} else {
			return new ModelAndView("error/403"); // Trang lỗi quyền truy cập
		}
	}

	// chuc năng sửa nhân viên
	@RequestMapping(value = "/management-personnel/update/personnel", method = RequestMethod.POST)
	public String updatePersonnel(HttpSession session, @RequestParam("id") int id,
			@ModelAttribute("employee") Admins admin) throws IOException {

		Admins currentUser = (Admins) session.getAttribute("LoginInfo");

		if (currentUser != null && currentUser.getRole() == 1) { // Admin
			admin.setId(id);
			System.out.println("Email: " + admin.getEmail());
			System.out.println("Username: " + admin.getUsername());
			System.out.println("Password: " + admin.getPassword());
			System.out.println("Address: " + admin.getAddress());
			System.out.println("Phone: " + admin.getPhone());
			System.out.println("Role: " + admin.getRole());
			System.out.println("ID: " + admin.getId());

			adminService.editEmployee(admin);

			return "redirect:/admin/manager-personnel";
		} else {
			return null;
		}
	}

	@GetMapping("/management-personnel")
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