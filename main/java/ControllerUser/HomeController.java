package ControllerUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDTO.ProductDTO;
import AdminService.ProductService;

import java.util.List;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private ProductService productSv;

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

    @RequestMapping(value = {"/search", "/product-detail/search"}, method = RequestMethod.POST)
    public String search(@RequestParam("query") String query, Model model) {
        // Tìm kiếm sản phẩm theo tên chứa chuỗi truy vấn
        List<ProductDTO> products = productSv.findByNameContaining(query);
        model.addAttribute("Products", products);
        model.addAttribute("ProductHighlight", _homService.GetHighlightProduct());
        model.addAttribute("ProductNew", _homService.GetNewProducts());
        model.addAttribute("slides", _homService.GetDataSlide());
        model.addAttribute("categorys", _homService.GetDataCategorys());
        // Kiểm tra nếu không có sản phẩm nào được tìm thấy
        if (products.isEmpty()) {
            model.addAttribute("statusMessage", "Không có sản phẩm nào với tên: " + query);
        }

        return "user/index";
    }


}
