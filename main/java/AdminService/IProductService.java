package AdminService;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import AdminDTO.ProductDTO;
import AdminEntity.ProductSize;

@Service
public interface IProductService {

//	//xóa sản phẩm
	int deleteProduct(int id);

	List<ProductDTO> getAllProductDto();

	void updateProduct(long id_product, int id_category, String name, int new_product, MultipartFile[] img,
			int highlight, double price, List<ProductSize> productSizeList, int sale, String title, String details)
			throws IOException;

	List<ProductDTO> findByNameContaining(String keyword);

	int getTotalShoesInStock();

	int getTotalShoesSold();

}
