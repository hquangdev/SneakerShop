package Service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;
import SneakerShop.Entity.Categorys;
import SneakerShopDAO.CategorysDAO;
import SneakerShopDAO.ProductsDAO;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	ProductsDAO productDAO;

	@Autowired
	CategorysDAO categoryDAO;

	public List<ProductDTO> GetDataProductsPaginate(int id, int start, int totalPage) {
		return productDAO.GetDataProductsPaginate(id, start, totalPage);
	}

	@Override
	public List<ProductDTO> GetAllProductsByID(int id) {
		return productDAO.GetAllProductsByID(id);
	}

	// lấy toàn bộ dl từ category
	@Override
	public List<Categorys> GetAllCategory() {
		return categoryDAO.GetDataCategorys();

	}

	// lấy theo id
	@Override
	public Categorys getCategoryID(int id) {
		return categoryDAO.getcategoryID(id);
	}

	// xóa danh mục
	@Override
	public int deleteCategory(int id) {
		return categoryDAO.deleteCategory(id);
	}

	// Thêm danh mục
	@Override
	public int addCategory(Categorys category) {
		return categoryDAO.addCategory(category);

	}

	// sửa danh mục
	@Override
	public void editCategory(String name, String desscript, int id) {
	    Categorys abc = categoryDAO.getcategoryID(id);
	    if (abc != null) {
	        abc.setName(name);
	        abc.setDescription(desscript);
	        categoryDAO.editCategory(abc);
	    }
	
	}


}
