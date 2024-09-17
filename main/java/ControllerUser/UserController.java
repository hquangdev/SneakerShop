package ControllerUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Service.user.IAccountService;
import SneakerShop.Entity.Users;

@Controller
public class UserController extends BaseController {

	@Autowired
	private IAccountService accountService;

	// Hiển thị form thanh toán
	@GetMapping("checkoutVn")
	public ModelAndView checkout() {

		_mvShare.setViewName("payment");

		return _mvShare;
	}

	@RequestMapping(value = { "/registe" }, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("user/account/register");
		mv.addObject("user", new Users());
		return mv;
	}

	@RequestMapping(value = "/registe", method = RequestMethod.POST)
	public ModelAndView createAcc(@ModelAttribute("user") Users user) {

		try {
			int count = accountService.AddAcount(user);
			if (count > 0) {
				_mvShare.addObject("status", "Đăng ký tài khoản thành công");
			} else {
				_mvShare.addObject("status", "Đăng ký thất bại");
			}
		} catch (Exception e) {
			_mvShare.addObject("status", "Đã xảy ra lỗi: " + e.getMessage());
		}

		_mvShare.setViewName("user/account/register");
		return _mvShare;
	}

	@RequestMapping(value = "/Login-account", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @ModelAttribute("user") Users user) {
	    ModelAndView _mvShare = new ModelAndView();
	    
	    // Kiểm tra tài khoản
	    Users loggedInUser = accountService.CheckAcount(user);

	    if (loggedInUser != null) {
	        Long userId = loggedInUser.getId();
	        
	        // Kiểm tra nếu ID hợp lệ
	        if (userId != null && userId > 0) {
	            // Đăng nhập thành công
	            _mvShare.setViewName("redirect:/home-page");
	            session.setAttribute("LoginInfo", loggedInUser);
	            session.setAttribute("userId", userId); 
	        } else {
	            // ID người dùng không hợp lệ
	            _mvShare.setViewName("user/account/register");
	            _mvShare.addObject("statuslogin", "Đăng nhập thất bại: ID người dùng không hợp lệ.");
	        }
	    } else {
	        // Đăng nhập thất bại
	        _mvShare.setViewName("user/account/register");
	        _mvShare.addObject("statuslogin", "Đăng nhập thất bại: Không tìm thấy người dùng.");
	    }
	    return _mvShare;
	}


	@RequestMapping(value = "/log-out", method = RequestMethod.GET)
	public String Login(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("LoginInfo");
		return "redirect:" + request.getHeader("referer");
	}

}
