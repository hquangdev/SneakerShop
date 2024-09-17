package AdminDTO;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MapperCustomStatusDTO implements RowMapper<CustomStatusDTO> {

	@Override
	public CustomStatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomStatusDTO customerStats = new CustomStatusDTO();
		customerStats.setCustomerId(rs.getLong("customer_id"));
		customerStats.setCustomerName(rs.getString("customer_name"));
		customerStats.setTotalOrders(rs.getInt("total_orders"));
		customerStats.setTotalAmount(rs.getDouble("total_amount"));
		return customerStats;
	}
}
