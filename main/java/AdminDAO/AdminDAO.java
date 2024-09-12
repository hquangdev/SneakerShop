package AdminDAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import AdminEntity.Admins;
import AdminEntity.MapperAdmin;

@Service
public class AdminDAO extends BaseAdmin{
	public Admins getAdminAccount(Admins admin) {//	
		String sql = "SELECT * FROM tbl_admin WHERE email=?";
		
		List<Admins> result = _jdbcTemplate.query(sql, new MapperAdmin(), admin.getEmail());// thực hiện truy vấn, lấy dl của email và gán vào result
		
		if(result.isEmpty()) { // ktra xem có email nào ko
			return null; 
		}
		return result.get(0);

	}
	
	@SuppressWarnings("deprecation")
	public Admins getEmployeeID(int id) {
		String sql = "SELECT * FROM tbl_admin WHERE id = ?";
	        return _jdbcTemplate.queryForObject(sql, new Object[] { id }, new MapperAdmin());
	}
	
	//trang danh sách nhân viên
	public List<Admins> getAllEmployee(){
		String sql = "SELECT * FROM tbl_admin";	
		return _jdbcTemplate.query(sql, new MapperAdmin());
	}
	
	//chức năng thêm nhân viên
	public int addEmployee(Admins admin) {
		String sql = "INSERT INTO tbl_admin (email, username, password, address, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
		return _jdbcTemplate.update(sql, admin.getEmail(), admin.getUsername(), admin.getPassword(), admin.getAddress(),
				admin.getPhone(), admin.getRole());
	}

	//chức năng xóa nhân viên
	public int deleteEmployee(int id) {
		String sql = "DELETE FROM tbl_admin WHERE id = ?";
		return _jdbcTemplate.update(sql, id);
	}


	// Chức năng sửa nhân viên
	public int editEmployee(Admins admin) {
	    String sql = "UPDATE tbl_admin SET email = ?, username = ?, password = ?, address = ?, phone = ?, role = ? WHERE id = ?";
	    return _jdbcTemplate.update(sql, admin.getEmail(), admin.getUsername(), admin.getPassword(), admin.getAddress(),
	            admin.getPhone(), admin.getRole(), admin.getId());
	}


	 // Phân trang danh sách nhân viên
	public List<Admins> getEmployeesWithPagination(int pageNumber, int pageSize) {
	    int offset = (pageNumber - 1) * pageSize;
	    String sql = "SELECT * FROM tbl_admin LIMIT ? OFFSET ?";
	    return _jdbcTemplate.query(sql, new Object[]{pageSize, offset}, new MapperAdmin());
	}

	public int getTotalEmployeeCount() {
	    String sql = "SELECT COUNT(*) FROM tbl_admin";
	    return _jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
