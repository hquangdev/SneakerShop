package AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDAO.ClientDAO;
import AdminEntity.Clients;

@Service
public class ClientService implements IClients{
	
	@Autowired
	private ClientDAO userDao;

	@Override
	public List<Clients> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public Clients GetClientByID(int id) {
		return userDao.GetClientByID(id);
	}

	@Override
	public int EditClients(Clients client) {
		// TODO Auto-generated method stub
		return userDao.EditClient(client);
	}

}
