package SneakerShopDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import SneakerShop.Entity.MapperUser;
import SneakerShop.Entity.Users;

@Service
public class UsersDAO extends BaseDAO{

	public int AddAcount(Users user) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT ");
		sql.append("INTO users ");
		sql.append("( ");
		sql.append("    email, "); 
		sql.append("    password, ");
		sql.append("    display_name, ");
		sql.append("    address ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append("  '" + user.getEmail() + "', "); 
		sql.append("  '" + user.getPassword() + "', ");
		sql.append("  '" + user.getDisplay_name() + "', ");
		sql.append("  '" + user.getAddress() + "' ");
		sql.append(") ");
        
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	//lấy email từ csdl
	public Users getUsersByAccount(Users user) {
		String sql = "SELECT * FROM users WHERE email = ?";
		List<Users> results = _jdbcTemplate.query(sql, new MapperUser(), user.getEmail());
		if (results.isEmpty()) {
		    return null; 
		}
		return results.get(0); 
	}

}
