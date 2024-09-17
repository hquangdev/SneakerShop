package ControllerAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AdminDAO.productDAO;
import AdminDTO.ProductDTO;
import AdminEntity.ProductSize;
import AdminEntity.products;
import SneakerShop.Entity.Categorys;
import SneakerShop.Entity.Slides;

@RequestMapping("/admin")
@Controller
public class ProductsController extends BaseAdminController {
	
	@Autowired
	productDAO productDao;
	
	// lấy giữ liệu in ra list
	@RequestMapping(value = "/manager-product", method = RequestMethod.GET)
	public ModelAndView getAllProductDto() {		
		 _mvAdmin.addObject("productDto", homeAdminSv.getAllProductDto());
		 _mvAdmin.addObject("categories", categorySv.GetAllCategory());
		 _mvAdmin.setViewName("admin/products/list");
		return _mvAdmin;

	}
	
	//chuyển tới trang thêm snar phẩm
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public ModelAndView formAddProduct() {
		 _mvAdmin.addObject("categories", categorySv.GetAllCategory());	
		_mvAdmin.setViewName("admin/products/add");
		
		return _mvAdmin;
	}
	
	
	//thêm sản phẩm
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addPr(
	        @RequestParam("id_category") int id_category,
	        @RequestParam("name") String name,
	        @RequestParam("img")  MultipartFile[] img,
	        @RequestParam("highlight") int highlight,
	        @RequestParam("new_product") int new_product,
	        @RequestParam("price") double price,
	        @RequestParam("sizes") List<String> sizes,
	        @RequestParam("quantity") List<String> quantity,
	        @RequestParam("sale") int sale,
	        @RequestParam("title") String title,
	        @RequestParam("details") String details,
	        RedirectAttributes redirectAttributes) throws IOException, SQLException {

	    try {
	        List<ProductSize> productSizeList = convertToProductSize(sizes, quantity); // Xử lý yêu cầu với các tham số
	        productSv.addProduct(id_category, name, new_product, img, highlight, price, productSizeList, sale, title, details);

	        // Thêm thông báo thành công
	        redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được thêm thành công!");
	    } catch (Exception e) {
	        // Thêm thông báo lỗi
	        redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi thêm sản phẩm: " + e.getMessage());
	    }

	    return "redirect:/admin/manager-product";
	}
	
	
	//chuyẻn chuỗi thành int
	private List<ProductSize> convertToProductSize(List<String> sizes, List<String> quantity) {
	    List<ProductSize> productSizeList = new ArrayList<>();
	    
	    // Giả sử kích thước, số lượng, id_productsize và id_tablesize tương ứng theo chỉ số
	    for (int i = 0; i < sizes.size(); i++) {
	        ProductSize productSize = new ProductSize();
	        productSize.setSizes(Integer.parseInt(sizes.get(i)));// Gán kích thước
	        productSize.setQuantity(Integer.parseInt(quantity.get(i))); // Gán số lượng
	   
	        productSizeList.add(productSize);
	    }
	    
	    return productSizeList;
	}
	
	//chuyẻn chuỗi thành int
	private List<ProductSize> convertToProductSizeList(List<String> sizes, List<String> quantity, List<Integer> id_productsize, List<Integer> id_tablesize) {
	    List<ProductSize> productSizeList = new ArrayList<>();
	    
	    // Giả sử kích thước, số lượng, id_productsize và id_tablesize tương ứng theo chỉ số
	    for (int i = 0; i < sizes.size(); i++) {
	        ProductSize productSize = new ProductSize();
	        productSize.setSizes(Integer.parseInt(sizes.get(i)));// Gán kích thước
	        productSize.setQuantity(Integer.parseInt(quantity.get(i))); // Gán số lượng
	        productSize.setId_productsize(id_productsize.get(i)); // Gán id_productsize
	        productSize.setId(id_tablesize.get(i));  // Gán id_tablesize
	        productSizeList.add(productSize);
	    }
	    
	    return productSizeList;
	}
	
	
	//chuyẻn hướng tới sửa
	@GetMapping("/manager-product/edit/{id}")
	public String showEditForm(@PathVariable("id") long id_product, Model model) {
	    ProductDTO productDTO = productSv.getProductByIDD(id_product);
	    
	    model.addAttribute("categories", categorySv.GetAllCategory());

	    model.addAttribute("productDTO", productDTO);
	    return "admin/products/edit";
	}
	
	
		// hiện thị trang chi tiết sản phẩm
		@GetMapping("/manager-product/product-detail/{id}")
		public String showFormDetalis(@PathVariable("id") long id_product, Model model) {
				ProductDTO productDTO = productSv.getProductByIDD(id_product);    
			    model.addAttribute("categories", categorySv.GetAllCategory());
			    model.addAttribute("productDTO", productDTO);
	
			    return "admin/products/ProductDetails";
		}

	// xóa sản phẩm
		@RequestMapping(value = "/manager-product/delete/{id}", method = RequestMethod.GET)
		public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		    try {
		        productSv.deleteProduct(id); 
		        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công!");
		    } catch (Exception e) {
		        redirectAttributes.addFlashAttribute("error", "Xóa sản phẩm thất bại!");
		    }
		    return "redirect:/admin/manager-product"; 
		}

		//sửa sảm phẩm
		  @RequestMapping(value = "/manager-product/edit/updateProduct", method = RequestMethod.POST)
		  public String updateProduct(@RequestParam("id_product") int id_product,
		          @RequestParam("id_category") int id_category,
		          @RequestParam("name") String name,
		          @RequestParam("img") MultipartFile[] img,
		          @RequestParam("highlight") int highlight,
		          @RequestParam("new_product") int new_product,
		          @RequestParam("price") double price,
		          @RequestParam("sizes") List<String> sizes,
		          @RequestParam("quantity") List<String> quantity,
		          @RequestParam("id_productsize") List<Integer> id_productsize,
		          @RequestParam("id_tablesize") List<Integer> id_tablesize,
		          @RequestParam("sale") int sale,
		          @RequestParam("title") String title,
		          @RequestParam("details") String details,
		          Model model) throws IOException {
			  		
		// Chuyển đổi dữ liệu từ các tham số thành danh sách ProductSize
		List<ProductSize> productSizeList = convertToProductSizeList(sizes, quantity, id_productsize, id_tablesize);
		
		// Gọi service để xử lý cập nhật sản phẩm
		productSv.updateProduct(id_product, id_category, name, new_product, img, highlight, price, productSizeList, sale, title, details);
		
		return "redirect:/admin/manager-product";
		}
		  
		  
		  
		  //phần tìm theo tên sản phẩm
		  
		  
//		//phân trang 
//			@GetMapping("/manager-product")
//			public ModelAndView PanigationProducts(
//			        @RequestParam(defaultValue = "1") int page,
//			        @RequestParam(defaultValue = "10") int size) {
//
//			    // Tính toán trang bắt đầu và số lượng mục mỗi trang
//			    int pageNumber = page <= 0 ? 1 : page;
//			    int pageSize = size <= 0 ? 10 : size;
//
//			    // Lấy dữ liệu phân trang từ DAO
//			    List<ProductDTO> product = productDao.getSlideWithPagination(pageNumber, pageSize);
//			    
//			    // Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
//			    int totalProducts = productDao.getTotalSlideCount();
//			    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
//
//			    // Tạo ModelAndView và thêm dữ liệu vào mô hình
//			    _mvAdmin.setViewName("admin/products/list");
//			    _mvAdmin.addObject("productDto", product);
//			   
//			    _mvAdmin.addObject("categories", categorySv.GetAllCategory());
//			    _mvAdmin.addObject("currentPage", pageNumber);
//			    _mvAdmin.addObject("totalPages", totalPages);
//			    _mvAdmin.addObject("pageSize", pageSize);
//
//			    return _mvAdmin;
//			}


}
