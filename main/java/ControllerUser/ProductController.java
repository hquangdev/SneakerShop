package ControllerUser;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminDTO.ProductDTO;

@Controller
public class ProductController extends BaseController {
	
	@RequestMapping(value = { "/product-detail/{id}"})
	public ModelAndView showFormDetalis(@PathVariable("id") long id_product, Model model) {
		ProductDTO productDTO = productsv.getProductByIDD(id_product);
		
		  int idCategory = _productService.GetProductsByID(id_product).getId_category();
		
		model.addAttribute("productDTO", productDTO);
		_mvShare.addObject("productByIDCategory", _productService.GetProductByIDCategory(idCategory));
		_mvShare.addObject("ProductHighlight", _homService.GetHighlightProduct());
		 _mvShare.addObject("categorys", _homService.GetDataCategorys());
		_mvShare.setViewName("user/product/product");
		
		return _mvShare;
		
	}
	
	
    @RequestMapping(value = {"/search", "/product-detail/search"}, method = RequestMethod.POST)
    public String search(@RequestParam("query") String query, Model model) {
        // Tìm kiếm sản phẩm theo tên chứa chuỗi truy vấn
        List<ProductDTO> products = productsv.findByNameContaining(query);
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


	
    @RequestMapping(value = {"/admin/search"}, method = RequestMethod.POST)
    public String searchAdmin(@RequestParam("keyname") String query, Model model) {
        // Tìm kiếm sản phẩm theo tên chứa chuỗi truy vấn
        List<ProductDTO> products = productsv.findByNameContaining(query);
        model.addAttribute("productDto", products);
        model.addAttribute("categorys", _homService.GetDataCategorys());
        
        // Kiểm tra nếu không có sản phẩm nào được tìm thấy
        if (products.isEmpty()) {
            model.addAttribute("error", "Không có sản phẩm nào với tên: " + query);
        }

        return "admin/products/list";
    }

}
