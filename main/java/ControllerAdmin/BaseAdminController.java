package ControllerAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import AdminService.AdminServiceImpl;
import AdminService.BillService;
import AdminService.HomeAdminSV;
import AdminService.ProductService;
import AdminService.ClientService;
import Service.user.CategoryServiceImpl;
import Service.user.ContactService;


@Controller
public class BaseAdminController {

	public ModelAndView _mvAdmin = new ModelAndView();
	
	@Autowired
	HomeAdminSV homeAdminSv;
	
	@Autowired
	CategoryServiceImpl categorySv;
	
	@Autowired
	ProductService productSv;
	
	@Autowired
	BillService billService;
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ContactService contactSv;

}
