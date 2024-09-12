package Service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;

import SneakerShop.Entity.Categorys;
import SneakerShop.Entity.Menus;
import SneakerShop.Entity.Slides;
@Service
public interface IHomeService {
	
	@Autowired
	public List<Slides> GetDataSlide();
	public List<Categorys> GetDataCategorys();
	public List<Menus> GetDataMenus();
	public List<ProductDTO> GetNewProducts();
}
