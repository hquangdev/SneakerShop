package Service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SneakerShop.Entity.Contact;
import SneakerShopDAO.ContactDAO;

@Service
public class ContactService implements IContact {
	
	@Autowired
	 ContactDAO contactDao;

	@Override
	public List<Contact> getAllContact() {
		// TODO Auto-generated method stub
		return contactDao.getAllContact();
	}
	
	@Override
	public Contact GetContactByID(int id) {
		return contactDao.GetContactByID(id);
	}

	@Override
	public void editContact(Contact contact) {
		contactDao.EditContact(contact);
		
	}

	
}
