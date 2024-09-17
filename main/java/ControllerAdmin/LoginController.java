package ControllerAdmin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import AdminEntity.Admins;
import Service.user.HomeServiceImpl;
@Controller
public class LoginController extends BaseAdminController{

	@Autowired
	private HomeServiceImpl _homService;
		
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public ModelAndView home() {
		_mvAdmin.setViewName("login");
		_mvAdmin.addObject("admin", new Admins()); 
		return _mvAdmin;
	}
	 
	@RequestMapping(value = "/logout-admin", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
	    // Xóa thông tin người dùng khỏi session
	    session.removeAttribute("LoginInfo");
		_mvAdmin.addObject("ProductHighlight", _homService.GetHighlightProduct());
		_mvAdmin.addObject("ProductNew", _homService.GetNewProducts());
		_mvAdmin.addObject("Products", _homService.GetProduct());
		_mvAdmin.addObject("slides", _homService.GetDataSlide());
		_mvAdmin.addObject("categorys", _homService.GetDataCategorys());
	    
	    // Chuyển hướng đến trang chính sau khi đăng xuất
	    _mvAdmin.setViewName("user/index");
	    return _mvAdmin;
	}

 
    @RequestMapping(value = "/admin/admin-login", method = RequestMethod.POST)
    public String loginAdmin(HttpSession session, @ModelAttribute("admin") Admins admin, Model model) {
        Admins loginAdmin = adminService.checkAdmin(admin);
        if (loginAdmin != null) {
            session.setAttribute("LoginInfo", loginAdmin);
            return "redirect:/admin/home";
        } else {
            model.addAttribute("statuslogin", "Đăng nhập thất bại");
            model.addAttribute("admin", new Admins());
            return "login";
        }
    }

    
    


}
