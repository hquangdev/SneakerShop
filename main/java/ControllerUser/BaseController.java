package ControllerUser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import AdminService.ProductService;
import Service.user.HomeServiceImpl;
import Service.user.IProductService;

@Controller
public class BaseController {
    @Autowired
    HomeServiceImpl _homService;
    
    public ModelAndView _mvShare = new ModelAndView();
    
    @Autowired
	ProductService productsv;
    
	@Autowired
	 IProductService _productService;
    
    @PostConstruct
    public ModelAndView Init() {
        _mvShare.addObject("menus", _homService.GetDataMenus());
        return _mvShare;
    }
}
