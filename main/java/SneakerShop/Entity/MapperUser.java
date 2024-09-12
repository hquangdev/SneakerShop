package SneakerShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapperUser implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException { //đại diện cho 1 hàng ketqua truy vấn csdl
    	
    	Users users = new Users();  //tạo lớp mới để ánh xạ dl từ result
    	
    	users.setEmail(rs.getString("email"));  // lấy dl từ cột email và thiết lập thuộc tính email từ Users
    	users.setDisplay_name(rs.getString("display_name"));
    	users.setPassword(rs.getString("password"));
    	users.setAddress(rs.getString("address"));
        return users;
    }
}