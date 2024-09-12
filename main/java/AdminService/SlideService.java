package AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import SneakerShopDAO.SlidesDAO;
import SneakerShop.Entity.Slides;

import java.util.List;
import java.io.File;
import java.io.IOException;
@Service
public class SlideService implements ISlideService {

	@Autowired
	private SlidesDAO slidesDAO;
	
	// Lấy đường dẫn tới thư mục ảnh
    String upImage =("C:/Users/Admin/eclipse-workspace/SneakerShop/src/main/webapp/assets/user/Slide-img/");

	@Override
	public List<Slides> getAllSlides() {
		return slidesDAO.getAllSlides();
	}

	@Override
	public Slides getSlideById(int id) {
		return slidesDAO.getSlideById(id);
	}
	
	//Phương thức để xử lí file ảnh
	 public String saveFile(MultipartFile file) throws IOException {
	        if (file != null && !file.isEmpty()) {
	        	
	            String filePath = upImage + File.separator + file.getOriginalFilename();
	            
	            File destinationFile = new File(filePath);
	            file.transferTo(destinationFile);
	            return file.getOriginalFilename();
	        }
	        throw new IOException("Thêm slide thất bại");
	    }
	 

	    // Phương thức xóa tệp ảnh
	    private void deleteImg(String fileName) {
	        File imageFile = new File(upImage + fileName);
	        if (imageFile.exists()) { //nếu tệp tồn tại
	            imageFile.delete(); // Xóa tệp ảnh
	        }
	    }
	 
     //chức năng Thêm slide
	    @Override
	    public void addSlide(MultipartFile img, String content, String caption) throws IOException {
	        
	        String imgName = saveFile(img);// Lưu ảnh và lấy tên tệp

	        // Tạo đối tượng slide và lưu vào cơ sở dữ liệu
	        Slides slide = new Slides();
	        slide.setImg(imgName); // Lưu tên tệp vào DB
	        slide.setContent(content);
	        slide.setCaption(caption);
	        slidesDAO.saveSlide(slide); 
	    }

	 
	 // Xóa slide và ảnh liên quan
	    @Override
	    public void deleteSlide(int id) {      
	        Slides slide = getSlideById(id); // Lấy thông tin slide từ cơ sở dữ liệu
	        if (slide != null) {           
	        	deleteImg(slide.getImg()); // Xóa tệp ảnh liên quan
	           
	            slidesDAO.deleteSlide(id); // Xóa slide khỏi cơ sở dữ liệu
	        }
	    }


        //chức năng sửa slide
	    @Override
	    public void editSlide(MultipartFile newImg, String content, String caption, int id) throws IOException {
	        Slides editSlide = getSlideById(id); // Lấy thông tin slide từ cơ sở dữ liệu

	        if (editSlide != null) {

	            deleteImg(editSlide.getImg());// Xóa ảnh cũ 
	            	        
	            if (newImg != null && !newImg.isEmpty()) {// Cập nhật ảnh mới nếu có
	                String newImgName = saveFile(newImg); // Lưu ảnh mới và lấy tên tệp
	                editSlide.setImg(newImgName); // Cập nhật tên ảnh mới
	            } else {
	                
	            	editSlide.setImg(editSlide.getImg());// Nếu không có ảnh mới, giữ nguyên ảnh cũ
	            }
	                
	            editSlide.setContent(content); 
	            editSlide.setCaption(caption);
	           
	            slidesDAO.editSlide(editSlide); // Lưu thông tin slide cập nhật vào cơ sở dữ liệu
	        }
	    }
	    
	    //phân trang
	    public List<Slides> getSlideWithPagination(int pageNumber, int pageSize) {
	        return slidesDAO.getSlideWithPagination(pageNumber, pageSize);
	    }
}
