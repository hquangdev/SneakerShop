package Service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;
import SneakerShop.Entity.Categorys;
@Service
public interface ICategoryService {
    List<ProductDTO> GetAllProductsByID(int id);
    List<ProductDTO> GetDataProductsPaginate(int start, int totalPage,int id);
    List<Categorys> GetAllCategory();
    
    Categorys getCategoryID(int id);
    
    int deleteCategory(int id);
    int addCategory(Categorys category);
    
   void editCategory(String name , String desscript, int id);
}
