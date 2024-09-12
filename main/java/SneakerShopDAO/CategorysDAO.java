package SneakerShopDAO;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import AdminEntity.Admins;
import AdminEntity.MapperAdmin;
import SneakerShop.Entity.Categorys;
import SneakerShop.Entity.MaperCategorys;
import SneakerShop.Entity.MaperSlides;
import SneakerShop.Entity.Slides;

@Repository
public class CategorysDAO extends BaseDAO  {

    public List<Categorys> GetDataCategorys() {
    	List<Categorys> list = new ArrayList<Categorys>();
        String sql = "SELECT * FROM categorys";
        list = _jdbcTemplate.query(sql, new MaperCategorys());   
        return list;
    }
    
    //lấy toàn bộ form categorys
    public List<Categorys> getAllCategory(){
    	
    	String sql = "SELECT * FROM categorys";
		return _jdbcTemplate.query(sql, new MaperCategorys()); 	
    }
    
    //lấy theo id danh mục
	@SuppressWarnings("deprecation")
	public Categorys getcategoryID(int id) {
		String sql = "SELECT * FROM categorys WHERE id = ?";
	        return _jdbcTemplate.queryForObject(sql, new Object[] { id }, new MaperCategorys());
	}
    
    //xóa danh mục
    public int deleteCategory(int id) {
    	String sql = "DELETE FROM categorys WHERE id=?";
		return _jdbcTemplate.update(sql, id);
    	
    }
    
    //chức năng thêm danh mục
    public int addCategory(Categorys category) {
    	
    	String sql = "INSERT INTO categorys(name, description) VALUES(?, ?) ";
    	
    	return _jdbcTemplate.update(sql, category.getName(), category.getDescription());
    }
    
    //chức năng sửa danh mục
    public void editCategory(Categorys category) {
    	String sql = ("UPDATE categorys SET name = ?, description = ? WHERE id =? ");
    	
          _jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
    }
    
    // Phân trang category
   	@SuppressWarnings("deprecation")
   	public List<Categorys> getCategoryWithPagination(int pageNumber, int pageSize) {
   	    int offset = (pageNumber - 1) * pageSize;
   	    String sql = "SELECT * FROM categorys LIMIT ? OFFSET ?";
   	    return _jdbcTemplate.query(sql, new Object[]{pageSize, offset}, new MaperCategorys());
   	}

   	public int getTotalCategoryCount() {
   	    String sql = "SELECT COUNT(*) FROM categorys";
   	    return _jdbcTemplate.queryForObject(sql, Integer.class);
   	}
}
