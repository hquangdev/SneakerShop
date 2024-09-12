package ControllerAdmin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import AdminEntity.Admins;
import Service.user.CategoryServiceImpl;
import SneakerShop.Entity.Categorys;
import SneakerShop.Entity.Slides;
import SneakerShopDAO.CategorysDAO;

@RequestMapping("/admin")
@Controller
public class CategoryAdminController extends BaseAdminController {

	@Autowired
	CategoryServiceImpl categoryService;
	
	@Autowired
	CategorysDAO categoryDao;

//	// chuyển tới danh sách
//	@RequestMapping(value = ("/manager-category"))
//	public ModelAndView listCategory() {
//
//		List<Categorys> category = categoryService.GetAllCategory();
//
//		_mvAdmin.setViewName("admin/category/list");
//		_mvAdmin.addObject("category", category);
//		_mvAdmin.addObject("categorys", new Categorys());
//		return _mvAdmin;
//	}

	// chuyển tới trang sửa danh mục
	@RequestMapping(value = "/manager-category/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		Categorys Listeditcategory = categoryService.getCategoryID(id);

		_mvAdmin.addObject("category", Listeditcategory);
		_mvAdmin.setViewName("admin/category/edit");
		return _mvAdmin;
	}

	// chức năng xóa danh mục
	@RequestMapping(value = ("/manager-category/delete/{id}"), method = RequestMethod.GET)
	public ModelAndView deleteCategory(@PathVariable("id") int id) {

		categoryService.deleteCategory(id);
		_mvAdmin.setViewName("redirect:/admin/manager-category");
		return _mvAdmin;

	}

	// chức năng thêm danh mục
	@RequestMapping(value = ("/add-category"), method = RequestMethod.POST)
	public ModelAndView addcategory(@ModelAttribute("categorys") Categorys category) {

		int add = categoryService.addCategory(category);

		if (add > 0) {
			_mvAdmin.addObject("categorys", category);
			_mvAdmin.setViewName("redirect:/admin/manager-category");
		}
		return _mvAdmin;

	}

	// chức năng sửa danh mục
	@PostMapping(value = "/manager-category/edit/editcategory")
	public String editcategory(@ModelAttribute("category") Categorys category) throws IOException {
		categoryService.editCategory
		(category.getName(), category.getDescription(), category.getId());
		
		return ("redirect:/admin/manager-category");
	}
	
	// phân trang danh mục
	@GetMapping("/manager-category")
	public ModelAndView listSlide(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "8") int size) {

	    // Tính toán trang bắt đầu và số lượng mục mỗi trang
	    int pageNumber = page <= 0 ? 1 : page;
	    int pageSize = size <= 0 ? 8 : size;

	    // Lấy dữ liệu phân trang từ DAO
	    List<Categorys> category = categoryDao.getCategoryWithPagination(pageNumber, pageSize);

	    // Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
	    int totalSlide = categoryDao.getTotalCategoryCount();
	    int totalPages = (int) Math.ceil((double) totalSlide / pageSize);

	    // Tạo ModelAndView và thêm dữ liệu vào mô hình
	    _mvAdmin.setViewName("admin/category/list");
	    _mvAdmin.addObject("category", category);
	    _mvAdmin.addObject("currentPage", pageNumber);
	    _mvAdmin.addObject("totalPages", totalPages);
	    _mvAdmin.addObject("pageSize", pageSize);

	    return _mvAdmin;
	}
}