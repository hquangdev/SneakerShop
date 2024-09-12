package AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class MapperClients implements RowMapper<Clients> {

	@Override
	public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
		Clients user = new Clients();
		
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setDisplay_name(rs.getString("display_name"));
		user.setAddress(rs.getString("address"));
		user.setCreated_at(rs.getDate("created_at"));
		user.setUpdated_at(rs.getDate("updated_at"));
		
		return user;
	}

}
