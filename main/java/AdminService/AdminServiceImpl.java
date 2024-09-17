package AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import AdminDAO.AdminDAO;
import AdminEntity.Admins;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	AdminDAO adminDao;

	String status = null;

	@Override
	public Admins checkAdmin(Admins admin) {
		String pass = admin.getPassword();// lấy dl từ đối tượng admin

		admin = adminDao.getAdminAccount(admin);
		if (admin != null && admin.getPassword().equals(pass)) {
			return admin;
		}

		return null;
	}

	// lấy tất cả dl từ db hiện thị ra form nhân viên
	@Override
	public List<Admins> getAllAdmin() {
		return adminDao.getAllEmployee();
	}

	// chức năng thêm nhân viên
	public String addEmployee(Admins admin) {
		if (adminDao.getAdminAccount(admin) != null) {

			return "Tên tài khoản đã tồn tại";
		} else {
			// Nếu không tồn tại, thêm nhân viên và trả về thông báo thành công
			adminDao.addEmployee(admin);
			return "Thêm nhân viên thành công";
		}
	}

	@Override
	public Admins getAdminId(int id) {
		return adminDao.getEmployeeID(id);
	}

	@Override
	public void deleteEmployee(int id) {
		adminDao.deleteEmployee(id);
	}

	@Override
	public void editEmployee(Admins admin) {
		adminDao.editEmployee(admin); 
	}

	//Phần phân trang
	public List<Admins> getEmployeesWithPagination(int pageNumber, int pageSize) {
		return adminDao.getEmployeesWithPagination(pageNumber, pageSize);
	}

}
