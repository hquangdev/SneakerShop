package AdminService;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import AdminDTO.ProductDTO;
import AdminEntity.ProductSize;
import AdminEntity.products;

@Service
public interface IProductService {
	
//	//hiện thị danh sách sản phẩm
//	List<products> getAllProduct();
//	List<ProductSize> getAllProductSize();
//	//xóa sản phẩm
	int deleteProduct(int id);
	
	List<ProductDTO> getAllProductDto();


	void updateProduct(long id_product, int id_category, String name, int new_product, MultipartFile[] img,
			int highlight, double price, List<ProductSize> productSizeList, int sale, String title, String details)
			throws IOException;

	List<ProductDTO> findByNameContaining(String keyword);


}
