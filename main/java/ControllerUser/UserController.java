package ControllerUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = {"/registe"}, method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("user/account/register");
        mv.addObject("user", new Users());
        return mv;
    }

    @RequestMapping(value = "/registe", method = RequestMethod.POST)
    public ModelAndView createAcc(@ModelAttribute("user") Users user) {
        ModelAndView mav = new ModelAndView();

        try {
            int count = accountService.AddAcount(user);
            if (count > 0) {
                mav.addObject("status", "Đăng ký tài khoản thành công");
            } else {
                mav.addObject("status", "Đăng ký thất bại");
            }
        } catch (Exception e) {
            mav.addObject("status", "Đã xảy ra lỗi: " + e.getMessage());
        }

        mav.setViewName("user/account/register");
        return mav;
    }

    
    @RequestMapping(value = "/Login-account", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, @ModelAttribute("user") Users user) {
        ModelAndView mv = new ModelAndView(); 

        // Kiểm tra tài khoản
        Users loggedInUser = accountService.CheckAcount(user);
        
        if (loggedInUser != null) {
            // Đăng nhập thành công
            mv.setViewName("redirect:/home-page"); 
            session.setAttribute("LoginInfo", loggedInUser);
        } else {
            // Đăng nhập thất bại
            mv.setViewName("user/account/Login-account");
            mv.addObject("statuslogin", "Đăng nhập thất bại");
        }
        return mv;
    }
    
    @RequestMapping(value = "/log-out", method = RequestMethod.GET)
    public String Login(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("LoginInfo");
        return "redirect:"+request.getHeader("referer");
    }


}
