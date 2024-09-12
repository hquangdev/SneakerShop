package SneakerShopDAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import SneakerShop.Entity.Contact;
import SneakerShop.Entity.MapperContact;

@Repository
public class ContactDAO extends BaseDAO {
	
	public List<Contact> getAllContact(){
		String sql = "SELECT * FROM contacts";
		
		return _jdbcTemplate.query(sql, new MapperContact());
	}
	
	//phần quản lý liên hệ ở Admin
	 public int EditContact(Contact contact) {
	        String sql = "UPDATE contacts SET " + "content = ?, "                
	                + "office_Hotline = ?, "
	                + "customer_care_Hotline = ?, "
	                + "purchase_Hotline = ?, "
	                + "address = ?, "
	                + "email = ? "
	                + "WHERE id = ?";
	        return _jdbcTemplate.update(sql, contact.getContent(),           
	            contact.getOffice_Hotline(),
	            contact.getCustomer_care_Hotline(),
	            contact.getPurchase_Hotline(),
	            contact.getAddress(),
	            contact.getEmail(),
	            contact.getId());
	    }
	 
	 //lấy id
	 @SuppressWarnings("deprecation")
	public Contact GetContactByID(int id) {
		 String sql = "SELECT *FROM contacts WHERE id=?";
		return _jdbcTemplate.queryForObject(sql, new Object [] {id}, new MapperContact());
		 
	 }

}
