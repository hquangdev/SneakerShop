package AdminService;

import java.util.List;

import org.springframework.stereotype.Service;

import AdminDTO.CustomStatusDTO;
import AdminEntity.Clients;

@Service
public interface IClients {
	
	List<Clients> getAllUser();
	
	Clients GetClientByID(int id);
	
	int EditClients(Clients client);
	
	int getTotalNewCustomersForCurrentYear();
	int getRepeatCustomerCount();
	 List<CustomStatusDTO> getCustomerStats();
}
