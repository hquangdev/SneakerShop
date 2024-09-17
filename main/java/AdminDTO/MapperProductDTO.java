package AdminDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import AdminEntity.ProductSize;

public class MapperProductDTO implements RowMapper<ProductDTO> {

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	    ProductDTO dto = new ProductDTO();

	    dto.setId_product(rs.getLong("id_product"));
	    dto.setId_category(rs.getInt("id_category"));
	    dto.setName(rs.getString("name"));
	    dto.setPrice(rs.getDouble("price"));
	    dto.setSale(rs.getInt("sale"));
	    dto.setTitle(rs.getString("title"));
	    dto.setHighlight(rs.getInt("highlight"));
	    dto.setNew_product(rs.getInt("new_product"));
	    dto.setDetails(rs.getString("details"));
	    dto.setCreated_at(rs.getDate("created_at"));
	    dto.setUpdated_at(rs.getDate("updated_at"));
	 
        String img = rs.getString("img"); // Lưu chuỗi ảnh như một chuỗi đơn
        dto.setImg(img);  

	    // Xử lý kích thước và số lượng sản phẩm
	    String sizes = rs.getString("sizes");
	    String quantities = rs.getString("quantity");
	    String id_productsize = rs.getString("id_productsize");
	    String id_tablesize = rs.getString("id");

	    List<ProductSize> productSizes = new ArrayList<>();
	    if (sizes != null && !sizes.isEmpty() && quantities != null && !quantities.isEmpty() 
	    		&& !id_tablesize.isEmpty() && id_tablesize != null) {
	        String[] sizeArray = sizes.split(",");
	        String[] quantityArray = quantities.split(",");
	        String[] id_productsizeArray = id_productsize.split(",");
	        String[] id_tablesizeArray = id_tablesize.split(",");

	     // Đảm bảo kích thước của sizeArray và quantityArray là đồng nhất
	        int length = Math.min(sizeArray.length, Math.min(quantityArray.length, id_tablesizeArray.length));

	        for (int i = 0; i < length; i++) {
	            try {
	                int sizeValue = Integer.parseInt(sizeArray[i].trim());
	                int quantityValue = Integer.parseInt(quantityArray[i].trim());
	                int id_productsizeValue = Integer.parseInt(id_productsizeArray[i].trim());
	                int id_tablesizeValue = Integer.parseInt(id_tablesizeArray[i].trim());

	                ProductSize size = new ProductSize();
	                size.setSizes(sizeValue);
	                size.setQuantity(quantityValue);
	                size.setId_productsize(id_productsizeValue);
	                size.setId(id_tablesizeValue);
	                productSizes.add(size);
	            } catch (NumberFormatException e) {
	                System.err.println("Dữ liệu không hợp lệ tại phần tử " + i + ": " + sizeArray[i] + ", " + quantityArray[i]);
	            }
	        }
	    }

	    dto.setProductsize(productSizes);

	    return dto;
	}


}
