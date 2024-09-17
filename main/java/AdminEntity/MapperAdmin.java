package AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperAdmin implements RowMapper<Admins> { 

	@Override
	public Admins mapRow(ResultSet rs, int rowNum) throws SQLException {

		Admins admin = new Admins(); 

		admin.setId(rs.getInt("id"));
		admin.setEmail(rs.getString("email"));
		admin.setUsername(rs.getString("username"));
		admin.setPassword(rs.getString("password"));
		admin.setAddress(rs.getString("address"));
		admin.setPhone(rs.getString("phone"));
		admin.setRole(rs.getInt("role"));

		return admin;
	}

}
