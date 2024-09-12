package AdminService;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminEntity.Clients;

@Service
public interface IClients {
	
	List<Clients> getAllUser();
	
	Clients GetClientByID(int id);
	
	int EditClients(Clients client);

}
