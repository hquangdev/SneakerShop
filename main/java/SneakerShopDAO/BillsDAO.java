package SneakerShopDAO;

import org.springframework.stereotype.Repository;
import SneakerShop.Entity.BillDetail;
import SneakerShop.Entity.Bills;

@Repository
public class BillsDAO extends BaseDAO {

	public int AddBills(Bills bill) {
		String sql = "INSERT INTO bills (user, phone, display_name, address, quanty, total, note, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, 1)"; // Gán giá trị cố định 1 cho status

		return _jdbcTemplate.update(sql, bill.getUser(), bill.getPhone(), bill.getDisplay_name(), bill.getAddress(),
				bill.getQuanty(), bill.getTotal(), bill.getNote());

	}

// lấy id lớn nhất của bills mới thêm vào
	public long GetIDLastBills() {
		String sql = "SELECT MAX(id) FROM bills";
		Long id = _jdbcTemplate.queryForObject(sql, Long.class);
		return id;
	}

	public int AddBillsDetail(BillDetail billdetail) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO billdetail ");
		sql.append("(");
		sql.append("   id_product, ");
		sql.append("   id_bills, ");
		sql.append("   quanty, ");
		sql.append("   total, ");
		sql.append("   selected_size ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("(");
		sql.append("  '" + billdetail.getId_product() + "', ");
		sql.append("  " + billdetail.getId_bills() + ", ");
		sql.append("  " + billdetail.getQuanty() + ", ");
		sql.append("  " + billdetail.getTotal() + ", ");
		sql.append("  " + billdetail.getSelected_size() + " ");
		sql.append("); ");

		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}

}
