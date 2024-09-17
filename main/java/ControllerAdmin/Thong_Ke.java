package ControllerAdmin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import AdminDTO.CustomStatusDTO;

@RequestMapping("/admin")
@Controller
public class Thong_Ke extends BaseAdminController{

	
	@GetMapping("/home")
    public String getTotalShoesInStock(Model model) {
        int totalShoes = productSv.getTotalShoesInStock();
        int totalShoesSold = productSv.getTotalShoesSold();
        int totalBill = billService.getTotalShoesInStock();
        Double totalrevenue = billService.getTotalRevenue();
        int totalNewClient = clientService.getTotalNewCustomersForCurrentYear();
        int totalRepeatCustomerCount = clientService.getRepeatCustomerCount();
        List<CustomStatusDTO> customerStats = clientService.getCustomerStats();
        
        model.addAttribute("totalShoes", totalShoes);
        model.addAttribute("totalShoesSold", totalShoesSold);
        model.addAttribute("totalBill", totalBill);
        model.addAttribute("totalrevenue", totalrevenue);
        model.addAttribute("totalNewClient", totalNewClient);
        model.addAttribute("totalRepeatCustomerCount", totalRepeatCustomerCount);
        model.addAttribute("customerStats", customerStats);
        return "/admin/index";
    }

}
