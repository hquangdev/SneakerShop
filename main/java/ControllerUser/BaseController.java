package ControllerUser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import Service.user.HomeServiceImpl;

@Controller
public class BaseController {
    @Autowired
    HomeServiceImpl _homService;
    
    public ModelAndView _mvShare = new ModelAndView();
    
    @PostConstruct
    public ModelAndView Init() {
        _mvShare.addObject("menus", _homService.GetDataMenus());
        return _mvShare;
    }
}
