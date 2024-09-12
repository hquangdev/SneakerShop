package Service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;
import AdminEntity.products;


@Service
public interface IProductService {
    public ProductDTO GetProductsByID(long id);
    public List<ProductDTO> GetProductByIDCategory(int id);
    
    public List<ProductDTO> getAllProductsByID(int id);
    
}
