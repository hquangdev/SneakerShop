package AdminService;

import java.util.List;

import AdminDTO.BillDetailDTO;
import SneakerShop.Entity.Bills;

public interface IBillService {
	public List<Bills> getAllBil();
	int deleteBill(int id);
	  BillDetailDTO getBillDetailByID(long id_bills);
	void updateStatus(long id, int status);

}
