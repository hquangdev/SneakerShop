package AdminDAO;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminDTO.CustomStatusDTO;
import AdminEntity.MapperClients;
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

		//sửa 
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
		
		public int getTotalNewCustomersForCurrentYear() {
		    String sql = "SELECT COUNT(*) FROM users WHERE created_at >= DATE_FORMAT(NOW() ,'%Y-01-01') AND created_at < DATE_FORMAT(NOW() + INTERVAL 1 YEAR ,'%Y-01-01')";
		    return _jdbcTemplate.queryForObject(sql, Integer.class);
		}

		// khách hàng quay lại
		 public int getRepeatCustomerCount() {
		        String sql = "SELECT COUNT(DISTINCT id_client) AS repeatCustomers " +
		                     "FROM bills " +
		                     "WHERE id_client IN (" +
		                     "    SELECT id_client " +
		                     "    FROM bills " +
		                     "    GROUP BY id_client " +
		                     "    HAVING COUNT(*) > 1" +
		                     ")";
		        return _jdbcTemplate.queryForObject(sql, Integer.class);
		    }
		 
		 
		  public List<CustomStatusDTO> getNewCustomerStats() {
		        String sql = "SELECT c.id AS customerId, c.display_name AS customerName, COUNT(b.id) AS totalOrders, " +
		                     "SUM(b.total) AS totalAmount " +
		                     "FROM users c " +
		                     "JOIN bills b ON c.id = b.id_client " +
		                     "WHERE b.date >= NOW() - INTERVAL 1 MONTH " +  // Adjust this as needed to define "new"
		                     "GROUP BY c.id, c.display_name";

		        return _jdbcTemplate.query(sql, (rs, rowNum) -> {
		        	CustomStatusDTO stats = new CustomStatusDTO();
		            stats.setCustomerId(rs.getLong("customerId"));
		            stats.setCustomerName(rs.getString("customerName"));
		            stats.setTotalOrders(rs.getInt("totalOrders"));
		            stats.setTotalAmount(rs.getDouble("totalAmount"));
		            return stats;
		        });
		    }
		
}
