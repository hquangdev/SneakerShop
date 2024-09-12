package AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapperProduct implements RowMapper<products>{

	@Override
	public products mapRow(ResultSet rs, int rowNum) throws SQLException {
		products product = new products();
		
		 product.setId(rs.getInt("id"));
		 product.setId_category(rs.getInt("id_category"));
		 product.setName(rs.getString("name"));
		 product.setPrice(rs.getDouble("price"));
		 product.setSale(rs.getInt("sale"));
		 product.setTitle(rs.getString("title"));
		 product.setHighlight(rs.getInt("highlight"));
		 product.setNew_product(rs.getInt("new_product"));
		 product.setDetails(rs.getString("details"));
		 product.setCreated_at(rs.getDate("created_at"));
		 product.setUpdated_at(rs.getDate("updated_at"));
		 product.setImg(rs.getString("img"));
	        return product;
	}

}
