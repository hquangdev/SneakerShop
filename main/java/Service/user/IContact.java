package Service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import SneakerShop.Entity.Contact;

@Service
public interface IContact {
	
	List<Contact> getAllContact();

	Contact GetContactByID(int id);
	

	void editContact(Contact contact);

}
