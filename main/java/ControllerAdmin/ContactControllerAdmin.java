package ControllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import SneakerShop.Entity.Contact;


@Controller
@RequestMapping("/admin")
public class ContactControllerAdmin extends BaseAdminController {
	
	@RequestMapping(value="/management-contact")
	public ModelAndView GetAllContact() {
		_mvAdmin.addObject("contact", contactSv.getAllContact());
		
		_mvAdmin.setViewName("admin/Contact/list");
		return _mvAdmin;
	}
	
	@RequestMapping(value = "/management-contact/edit/{id}", method = RequestMethod.GET)
    public ModelAndView updateContact(@PathVariable("id") int id) {
        Contact contact = contactSv.GetContactByID(id);
        _mvAdmin.addObject("contact", contact);
        _mvAdmin.setViewName("admin/Contact/edit");
        return _mvAdmin;
    }
	
	@RequestMapping(value = "/management-contact/edit/updateContact", method = RequestMethod.POST)
	    public String updateContact(@ModelAttribute Contact contact) {
		contactSv.editContact(contact);
	        return "redirect:/admin/management-contact"; // Điều hướng về danh sách sau khi cập nhật
	    }

}
