package SneakerShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperContact implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setContent(rs.getString("content"));	
		contact.setOffice_Hotline(rs.getString("Office_Hotline"));
		contact.setCustomer_care_Hotline(rs.getString("Customer_care_Hotline"));
		contact.setPurchase_Hotline(rs.getString("Purchase_Hotline"));
		contact.setAddress(rs.getString("address"));
		contact.setEmail(rs.getString("email"));
		
		return contact;
	}

}
