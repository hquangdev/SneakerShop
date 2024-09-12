package SneakerShopDAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import AdminDTO.MapperProductDTO;
import AdminDTO.ProductDTO;
import AdminEntity.MapperProduct;
import AdminEntity.ProductSize;
import AdminEntity.products;

@Repository
public class ProductsDAO extends BaseDAO {

    private final boolean YES = true;
    private final boolean NO = false;	

    private StringBuffer SqlString() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("p.id AS id_product, ");
        sql.append("p.id_category AS id_category, ");
        sql.append("p.name AS name, ");
        sql.append("p.price AS price, ");
        sql.append("p.sale AS sale, ");
        sql.append("p.title AS title, ");
        sql.append("p.highlight AS highlight, ");
        sql.append("p.new_product AS new_product, ");
        sql.append("p.details AS details, ");
        sql.append("p.created_at AS created_at, ");
        sql.append("p.updated_at AS updated_at, ");
        sql.append("p.img AS img, ");
        sql.append("ps.id AS id, ");
        sql.append("ps.sizes AS sizes, ");
        sql.append("ps.quantity AS quantity ");
        sql.append("FROM products AS p ");
        sql.append("INNER JOIN product_size AS ps ON p.id = ps.id_productsize ");
    
        return sql;
    }


    // lấy theo sản phẩm mới 
    public List<ProductDTO> GetDataNewProduct() {
        String sql = SqlProducts(YES, NO);
        List<ProductDTO> listProducts = _jdbcTemplate.query(sql, new MapperProductDTO());
        return listProducts;
    }
    
    //Lấy theo sẩn phẩm nổi bật
    public List<ProductDTO> GetDataHighlightProduct(){
    	String sql = SqlProducts(NO, YES);
    	List<ProductDTO> listProduct = _jdbcTemplate.query(sql, new MapperProductDTO());
		return listProduct;
    }
    
    //Lấy theo sẩn phẩm Thường
    public List<ProductDTO> GetDataProduct(){
    	String sql = SqlProducts(NO, NO);
    	List<ProductDTO> listProduct = _jdbcTemplate.query(sql, new MapperProductDTO());
		return listProduct;
    }

    public String SqlProducts(boolean newProduct, boolean highlight) {
        StringBuffer sql = SqlString();
        sql.append("WHERE 1 = 1 ");
        
        if (highlight) {
            sql.append("AND p.highlight  = 1 ");
        }
        
        if (newProduct) {
            sql.append("AND p.new_product = 0 ");
        }
        
        if (!highlight && !newProduct) {
            sql.append("AND p.new_product = 0 ");
            sql.append("AND p.highlight = 0 ");// Sản phẩm không phải mới
        }
        
        sql.append("GROUP BY p.id ");
        sql.append("ORDER BY RAND() ");
        
        if (highlight) {
            sql.append("LIMIT 5 ");
        } else if (newProduct) {
            sql.append("LIMIT 8 ");
        }else {
            // Giới hạn số lượng sản phẩm thường nếu cả highlight và newProduct đều là false
            sql.append("LIMIT 12 "); }

        String finalSql = sql.toString();
        return finalSql;
    }
    
    
    private StringBuffer SqlProductsByID(int id) {
        StringBuffer sql = SqlString();
        sql.append("WHERE 1 = 1 ");
        sql.append("AND p.id_category = " + id + " ");
        return sql;
    }


    // phần phân trang
    private String SqlProductsPaginate(int id, int start, int totalPage) {
    	StringBuffer sql = SqlProductsByID(id);
    	sql.append("LIMIT " + start + ","+ totalPage);
    	return sql.toString();
   
    }

    public List<ProductDTO> GetAllProductsByID(int id) {
        String sql = SqlProductsByID(id).toString();
        List<ProductDTO> listProducts = _jdbcTemplate.query(sql, new MapperProductDTO());
        return listProducts;
    }
    
    public List<ProductDTO> GetDataProductsPaginate(int id, int start, int totalPage) {
        List<ProductDTO> listProducts = new ArrayList<>();
        StringBuffer sqlGetDataByID = new StringBuffer(SqlProductsByID(id));
        
        List<ProductDTO> listProductsByID = _jdbcTemplate.query(sqlGetDataByID.toString(), new MapperProductDTO());
        
        if (!listProductsByID.isEmpty()) {
            String sql = SqlProductsPaginate(id, start, totalPage);
            listProducts = _jdbcTemplate.query(sql, new MapperProductDTO());
            
            // Sử dụng Map để loại bỏ các sản phẩm trùng lặp
            Map<Long, ProductDTO> uniqueProducts = new LinkedHashMap<>();
            for (ProductDTO product : listProducts) {
                uniqueProducts.put(product.getId_product(), product);
            }
            
            // Chuyển Map trở lại List
            listProducts = new ArrayList<>(uniqueProducts.values());
        }

        return listProducts;
    }

    
    private String SqlProductByID(long id) {
        StringBuffer sql = SqlString();
        sql.append("WHERE 1 = 1 ");
        sql.append("AND p.id = " + id + " ");
        sql.append("LIMIT 1 ");
        return sql.toString();
    }

    //lấy dữ liệu theo id
	public List<ProductDTO> GetProductsByID(long id) {
		String sql = SqlProductByID(id);
		List<ProductDTO> listProduct = _jdbcTemplate.query(sql, new MapperProductDTO());
		return listProduct;
	}
	
	public ProductDTO FindProductByID(long id) {
		String sql = SqlProductByID(id);
		ProductDTO Product = _jdbcTemplate.queryForObject(sql, new MapperProductDTO());
		return Product;
	}
	

}
