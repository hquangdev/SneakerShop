package AdminService;

import java.util.List;

import AdminDTO.BillDetailDTO;
import SneakerShop.Entity.Bills;

public interface IBillService {
	public List<Bills> getAllBil();

	int deleteBill(int id);



	void updateStatus(long id, int status);

	List<BillDetailDTO> getBillDetailByID(long id_bills);
}
