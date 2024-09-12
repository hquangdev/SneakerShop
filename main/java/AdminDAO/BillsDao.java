package AdminDAO;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.stereotype.Service;

import AdminDTO.BillDetailDTO;
import AdminDTO.MapperBillDetailDTO;
import AdminEntity.Clients;
import AdminEntity.MapperAdmin;
import AdminEntity.MapperClients;
import SneakerShop.Entity.Bills;
import SneakerShop.Entity.MapperBills;

@Service
public class BillsDao extends BaseAdmin {
	
	public List<Bills> getAllBill(){
		String sql = "SELECT * FROM bills";
		return _jdbcTemplate.query(sql, new MapperBills()) ;

	}
	
	//xóa bill
	public int deleteBill(int id) {
		
		deleteBillDetail(id);
		
		String sql = "DELETE FROM bills WHERE id = ?";
		return _jdbcTemplate.update(sql, id);
	}
	
	public int deleteBillDetail(int id) {
		String sql = "DELETE FROM billdetail WHERE id_bills = ?";
		return _jdbcTemplate.update(sql, id);
	}
	
	
	@SuppressWarnings("deprecation")
	public BillDetailDTO getBillDetailByID(long id_bills) {
		
		 String sql = "SELECT b.user AS user, b.phone AS phone, b.display_name AS display_name, b.address AS address, " +
                 "b.quanty AS quanty, b.status AS status, b.note AS note, bd.total AS total, bd.selected_size AS selected_size,"
                 + "p.price AS price, p.name AS productName, bd.id_bills AS id_bills " +
                 "FROM bills AS b " +
                 "JOIN billdetail AS bd ON b.id = bd.id_bills  " + 
                 "JOIN products AS p ON bd.id_product = p.id " + 
                 "WHERE b.id = ?"; 
		 
		 return _jdbcTemplate.queryForObject(sql, new Object[] { id_bills }, new MapperBillDetailDTO());
		
		
	}
	
	//sửa trạng thía giao hàng
	public int updateOrderStatus(long id, int newStatus) {
        String sql = "UPDATE bills SET status = ? WHERE id = ?";
        return _jdbcTemplate.update(sql, newStatus, id);
    }

	
	// Phân trang 
			@SuppressWarnings("deprecation")
			public List<Bills> getSlideWithPagination(int pageNumber, int pageSize) {
			    int offset = (pageNumber - 1) * pageSize;
			    String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
			    return _jdbcTemplate.query(sql, new Object[]{pageSize, offset}, new MapperBills());
			}

			public int getTotalSlideCount() {
			    String sql = "SELECT COUNT(*) FROM users";
			    return _jdbcTemplate.queryForObject(sql, Integer.class);
			}
}
