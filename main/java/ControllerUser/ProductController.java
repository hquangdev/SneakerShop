package ControllerUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import AdminDTO.ProductDTO;
import AdminEntity.products;
import AdminService.ProductService;
import Service.user.IProductService;
import SneakerShop.Entity.Categorys;
@Controller
public class ProductController extends BaseController {
	@Autowired
	private IProductService _productService;
	
	@Autowired
	ProductService productsv;
	 @GetMapping("/search-product")
	    public String searchProduct(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
	        List<ProductDTO> products;
	        if (keyword != null && !keyword.trim().isEmpty()) {
	            products = productsv.findByNameContaining(keyword);
	        } else {
	            products = productsv.getAllProductDto(); // Lấy tất cả sản phẩm nếu không có từ khóa
	        }
	        model.addAttribute("listProduct", products);
	        return "listProduct"; // Tên file JSP hiển thị danh sách sản phẩm
	    }
	
	
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
	


}
