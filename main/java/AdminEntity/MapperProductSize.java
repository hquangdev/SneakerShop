package AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;


public class MapperProductSize implements RowMapper<ProductSize>{

	@Override
	public ProductSize mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductSize productsize = new ProductSize();
		productsize.setId(rs.getInt("id"));
		productsize.setId_productsize(rs.getInt("id_productsize"));
		productsize.setSizes(rs.getInt("sizes"));
		productsize.setQuantity(rs.getInt("quantity"));
	
		return productsize;
	}

}
