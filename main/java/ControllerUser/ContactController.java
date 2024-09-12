package ControllerUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactController extends BaseController {
	
	@RequestMapping(value = "/contact")
	public ModelAndView getAllContact() {
		_mvShare.addObject("contact", _homService.GetContact());
	
		_mvShare.setViewName("user/Contact/list");
		return _mvShare;
	}

}
