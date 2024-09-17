package Service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;
import SneakerShopDAO.ProductsDAO;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductsDAO productsDao = new ProductsDAO();

	@Override
	public ProductDTO GetProductsByID(long id) {
		List<ProductDTO> listProducts = productsDao.GetProductsByID(id);
		return listProducts.get(0);
	}

	@Override
	public List<ProductDTO> GetProductByIDCategory(int id) {
		return productsDao.GetAllProductsByID(id);
	}

	@Override
	public List<ProductDTO> getAllProductsByID(int id) {
		return productsDao.GetAllProductsByID(id);
	}


}
