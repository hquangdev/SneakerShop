package ControllerAdmin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AdminService.SlideService;
import SneakerShop.Entity.Slides;
import SneakerShopDAO.SlidesDAO;

@Controller
@RequestMapping("/admin")
public class SlideController extends BaseAdminController{

	@Autowired
	private SlideService slidesService;
	
	@Autowired
	private SlidesDAO slideDao;

	
	@RequestMapping(value = "/add-slide", method = RequestMethod.GET)
	public ModelAndView showAddSlideForm() {
		_mvAdmin.setViewName("admin/slides/add");
		_mvAdmin.addObject("slideAdmin", new Slides());
		return _mvAdmin;
	}

	// dùng dể chuyển tới trang sửa slide và hiện thị form
	@RequestMapping(value = "/manager-slide/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editSlideForm(@PathVariable("id") int id) {
		// Lấy slide từ cơ sở dữ liệu bằng ID
		Slides slide = slidesService.getSlideById(id);
		_mvAdmin.setViewName("admin/slides/edit");
		_mvAdmin.addObject("slideAdmin", slide);
		return _mvAdmin;
	}

	// Thêm slide
	@PostMapping("/add-slide")
	public String addSlide(@RequestParam("img") MultipartFile img,
	                       @RequestParam("content") String content,
	                       @RequestParam("caption") String caption,
	                       RedirectAttributes redirectAttributes) {
	    try {
	        slidesService.addSlide(img, content, caption);
	        redirectAttributes.addFlashAttribute("message", "Slide đã được thêm thành công!");
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi thêm slide.");
	    }
	    return "redirect:/admin/manager-slide";
	}


	// Xóa slide
	@RequestMapping(value = "/manager-slide/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteSlide(@PathVariable("id") int id,
			@RequestHeader(value = "Referer", required = false) String referer) {
		slidesService.deleteSlide(id);


		if (referer != null) {
			_mvAdmin.setViewName("redirect:" + referer);
		} else {
			_mvAdmin.setViewName("redirect:/admin/manager-slide");
		}
		return _mvAdmin;
	}
	
	//sửa sldie
	@RequestMapping(value = "/manager-slide/edit/slide", method = RequestMethod.POST)
	public String updateSlide(@RequestParam("id") int id,
	                          @RequestParam("img") MultipartFile img,
	                          @RequestParam("content") String content,
	                          @RequestParam("caption") String caption,
	                          RedirectAttributes redirectAttributes) throws IOException {

	    try {
	        // Gọi service để xử lý sửa slide
	        slidesService.editSlide(img, content, caption, id);
	        redirectAttributes.addFlashAttribute("message", "Slide đã được cập nhật thành công!");
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi cập nhật slide.");
	    }
	    
	    return "redirect:/admin/manager-slide"; 
	}

	
	@GetMapping("/manager-slide")
	public ModelAndView listSlide(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "8") int size) {

	    // Tính toán trang bắt đầu và số lượng mục mỗi trang
	    int pageNumber = page <= 0 ? 1 : page;
	    int pageSize = size <= 0 ? 8 : size;

	    // Lấy dữ liệu phân trang từ DAO
	    List<Slides> slide = slideDao.getSlideWithPagination(pageNumber, pageSize);

	    // Đếm tổng số slide để hiển thị số trang và điều hướng phân trang
	    int totalSlide = slideDao.getTotalSlideCount();
	    int totalPages = (int) Math.ceil((double) totalSlide / pageSize);

	    // Tạo ModelAndView và thêm dữ liệu vào mô hình
	    _mvAdmin.setViewName("admin/slides/list");
	    _mvAdmin.addObject("slides", slide);
	    _mvAdmin.addObject("currentPage", pageNumber);
	    _mvAdmin.addObject("totalPages", totalPages);
	    _mvAdmin.addObject("pageSize", pageSize);

	    return _mvAdmin;
	}

	

}
