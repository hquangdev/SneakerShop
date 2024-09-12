package AdminDAO;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminEntity.MapperClients;
import SneakerShop.Entity.MaperSlides;
import SneakerShop.Entity.Slides;
import AdminEntity.Clients;

@Service
public class ClientDAO extends BaseAdmin{
	
	public List<Clients> getAllUser(){
		String sql = "SELECT * FROM users";
		return _jdbcTemplate.query(sql, new MapperClients());
	}
	
	//lấy theo id
	@SuppressWarnings("deprecation")
	public Clients GetClientByID(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		return _jdbcTemplate.queryForObject(sql, new Object [] {id}, new MapperClients());
	}

	
//	//sửa 
	public int EditClient(Clients client) {
	    String sql = "UPDATE users SET email = ?, password = ?, display_name = ?, address = ?, updated_at = NOW() WHERE id = ?";
	    return _jdbcTemplate.update(sql, client.getEmail(), client.getPassword(), client.getDisplay_name(),
	            client.getAddress(), client.getId());
	}
	
	 // Phân trang 
		@SuppressWarnings("deprecation")
		public List<Clients> getSlideWithPagination(int pageNumber, int pageSize) {
		    int offset = (pageNumber - 1) * pageSize;
		    String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
		    return _jdbcTemplate.query(sql, new Object[]{pageSize, offset}, new MapperClients());
		}

		public int getTotalSlideCount() {
		    String sql = "SELECT COUNT(*) FROM users";
		    return _jdbcTemplate.queryForObject(sql, Integer.class);
		}

		
		//phân trang
		
}
