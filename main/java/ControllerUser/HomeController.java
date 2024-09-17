package ControllerUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {


    @RequestMapping(value = { "/", "/home-page" })
    public ModelAndView Index() {
    	 
    	_mvShare.addObject("ProductHighlight", _homService.GetHighlightProduct());
    	_mvShare.addObject("ProductNew", _homService.GetNewProducts());
    	_mvShare.addObject("Products", _homService.GetProduct());
        _mvShare.addObject("slides", _homService.GetDataSlide());
        _mvShare.addObject("categorys", _homService.GetDataCategorys());
        
        _mvShare.setViewName("user/index");
        return _mvShare;
    }



}
