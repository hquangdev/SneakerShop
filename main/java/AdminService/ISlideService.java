package AdminService;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import SneakerShop.Entity.Slides;

public interface ISlideService {

	
	List<Slides> getAllSlides();

	Slides getSlideById(int id);
	
	void addSlide(MultipartFile img, String content, String caption) throws IOException;
	
	void deleteSlide(int id);

	void editSlide(MultipartFile newImg, String content, String caption, int id) throws IOException;
}
