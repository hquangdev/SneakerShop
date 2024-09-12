package AdminService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import AdminEntity.Admins;

@Repository
public interface IAdminService {
	public Admins checkAdmin(Admins admin); // check để lấy tài khoản

	List<Admins> getAllAdmin();

	Admins getAdminId(int id);
	
	void deleteEmployee(int id);

//
//	void editEmployee(String email, String username, String password, String address, String phone, int role,
//			int id);

	void editEmployee(Admins admin);

}
