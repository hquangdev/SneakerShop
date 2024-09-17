package AdminDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import AdminDTO.MapperProductDTO;
import AdminDTO.ProductDTO;
import AdminEntity.MapperProduct;
import AdminEntity.ProductSize;
import AdminEntity.products;

@Service
public class productDAO extends BaseAdmin {

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
		sql.append("ps.id_productsize AS id_productsize, ");
		sql.append("ps.sizes AS sizes, ");
		sql.append("ps.quantity AS quantity ");
		sql.append("FROM products AS p ");
		sql.append("INNER JOIN product_size AS ps ON p.id = ps.id_productsize ");

		return sql;
	}

	// tìm kiếm theo sản phẩm
	@SuppressWarnings({ "deprecation", "unlikely-arg-type" })
	public List<ProductDTO> findByNameContaining(String keyword) {
		StringBuffer sql = SqlString();
		sql.append("WHERE p.name LIKE ?"); 

		// Lấy dữ liệu từ cơ sở dữ liệu
		List<ProductDTO> rawProducts = _jdbcTemplate.query(sql.toString(), new Object[] { "%" + keyword + "%" },
				new MapperProductDTO());

		Map<Integer, ProductDTO> productMap = new HashMap<>();
		for (ProductDTO product : rawProducts) {
			if (!productMap.containsKey(product.getId_product())) {
				productMap.put((int) product.getId_product(), product);
			} else {
				// Nếu sản phẩm đã tồn tại, thêm kích thước vào danh sách
				productMap.get(product.getId_product()).getProductsize().addAll(product.getProductsize());
			}
		}

		// Chuyển map thành list
		return new ArrayList<>(productMap.values());
	}

	// lấy id dể sửa
	@SuppressWarnings("deprecation")
	public ProductDTO getDataProductsid(long id_product) {
		StringBuffer sql = SqlString();
		sql.append("WHERE p.id = ? ");

		// Truy vấn sản phẩm với ID và trả về một danh sách ProductDTO
		List<ProductDTO> productList = _jdbcTemplate.query(sql.toString(), new Object[] { id_product },
				new MapperProductDTO());

		ProductDTO productDTO = productList.get(0);

		// Bây giờ chúng ta cần xử lý để lấy các kích cỡ và số lượng
		List<ProductSize> productSizeList = new ArrayList<>();
		for (ProductDTO product : productList) {
			productSizeList.addAll(product.getProductsize());
		}
		productDTO.setProductsize(productSizeList);

		return productDTO;
	}

	// lấy dữ liệu sản phẩm
	public List<ProductDTO> getAllProducts() {
		StringBuffer sql = SqlString();
		List<ProductDTO> products = _jdbcTemplate.query(sql.toString(), new MapperProductDTO());

		Map<Long, ProductDTO> productMap = new LinkedHashMap<>();// Sử dụng Map để nhóm các sản phẩm và kích thước

		for (ProductDTO product : products) {
			if (!productMap.containsKey(product.getId_product())) {
				productMap.put(product.getId_product(), product);
			} else {
				// Nếu sản phẩm đã tồn tại, thêm thông tin kích thước
				ProductDTO existingProduct = productMap.get(product.getId_product());
				existingProduct.getProductsize().addAll(product.getProductsize());
			}
		}

		return new ArrayList<>(productMap.values());
	}

	// thêm sản phẩm
	public int addProductDto(ProductDTO productDTO) throws SQLException {
		String sql = "INSERT INTO products (id_category, name, price, sale, title, details, highlight, new_product, created_at, updated_at, img)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?,NOW(),NOW(), ?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder(); // lấy id vừa thêm

		_jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, productDTO.getId_category());
			ps.setString(2, productDTO.getName());
			ps.setDouble(3, productDTO.getPrice());
			ps.setInt(4, productDTO.getSale());
			ps.setString(5, productDTO.getTitle());
			ps.setString(6, productDTO.getDetails());
			ps.setInt(7, productDTO.getHighlight());
			ps.setInt(8, productDTO.getNew_product());
			ps.setString(9, productDTO.getImg());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue(); // Trả về ID của sản phẩm
	}

	// thêm sản phẩm size
	public void addProductSize(ProductSize productsize) {
		String sql = "INSERT INTO product_size (id_productsize, sizes, quantity) VALUES (?, ?, ?)";
		_jdbcTemplate.update(sql, productsize.getId_productsize(), productsize.getSizes(), productsize.getQuantity());
	}

	public void updateProduct(ProductDTO product) {
		// Cập nhật thông tin sản phẩm
		String updateProductSql = "UPDATE products " + "SET id_category = ?, " + "name = ?, " + "price = ?, "
				+ "sale = ?, " + "title = ?, " + "highlight = ?, " + "new_product = ?, " + "details = ?, "
				+ "updated_at = NOW(), " + "img = ? " + "WHERE id = ?";

		_jdbcTemplate.update(updateProductSql, product.getId_category(), product.getName(), product.getPrice(),
				product.getSale(), product.getTitle(), product.getHighlight(), product.getNew_product(),
				product.getDetails(), product.getImg(), // Đảm bảo bạn đã xử lý và lưu ảnh đúng cách
				product.getId_product()); // ID sản phẩm

		// Xóa kích thước cũ
		String deleteSizeSql = "DELETE FROM product_size WHERE id_productsize = ?";
		_jdbcTemplate.update(deleteSizeSql, product.getId_product());

		// Thêm hoặc cập nhật kích thước mới
		String updateSizeSql = "INSERT INTO product_size (id_productsize, sizes, quantity) " + "VALUES (?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE sizes = VALUES(sizes), quantity = VALUES(quantity)";

		for (ProductSize size : product.getProductsize()) {
			_jdbcTemplate.update(updateSizeSql, size.getId_productsize(), size.getSizes(), size.getQuantity());
		}
	}

	// lấy theo id đẻ thực hiện xóa sửa
	@SuppressWarnings("deprecation")
	public products getProductById(long productId) {
		String sql = "SELECT * FROM products WHERE id = ?";
		return _jdbcTemplate.queryForObject(sql, new Object[] { productId }, new MapperProduct());
	}

	// xóa sản phẩm
	public int deleteProduct(int id) {

		deleteBilldetails(id);
		deleteBills(id);
		deleteProductSizes(id);

		String sql = "DELETE FROM products WHERE id = ?";
		int result = _jdbcTemplate.update(sql, id);
		return result;
	}

	public int deleteProductSizes(int id) {
		String sql = "DELETE FROM product_size WHERE id = ?";
		return _jdbcTemplate.update(sql, id);
	}

	public int deleteBilldetails(int id) {
		String sql = "DELETE FROM billdetail WHERE id_product = ?";
		return _jdbcTemplate.update(sql, id);
	}

	public int deleteBills(int id) {
		String sql = "DELETE FROM bills WHERE id = ?";
		return _jdbcTemplate.update(sql, id);
	}

	
	//tổng kho giày
	 public int getTotalShoesInStock() {
	        String sql = "SELECT SUM(quantity) FROM product_size";
	        return _jdbcTemplate.queryForObject(sql, Integer.class);
	    }
	 
	 public int getTotalShoesSold() {
	        String sql = "SELECT SUM(quanty)  FROM billdetail";
	        return _jdbcTemplate.queryForObject(sql, Integer.class);
	    }
}
