package SneakerShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapperUser implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException { //đại diện cho 1 hàng ketqua truy vấn csdl
    	
    	Users users = new Users();  
    	
    	users.setEmail(rs.getString("email"));  
    	users.setDisplay_name(rs.getString("display_name"));
    	users.setPassword(rs.getString("password"));
    	users.setAddress(rs.getString("address"));
    	users.setId(rs.getLong("id"));
        return users;
    }
}