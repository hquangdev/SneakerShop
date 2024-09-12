package AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDAO.productDAO;
import AdminDTO.ProductDTO;
import SneakerShop.Entity.Categorys;

@Service
public class HomeAdminSV {
	
	@Autowired
	private productDAO productDao;
	
	public List<ProductDTO> getAllProductDto(){
		return productDao.getAllProducts();
	}


}
