package AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDAO.BillsDao;
import AdminDTO.BillDetailDTO;
import SneakerShop.Entity.Bills;

@Service
public class BillService implements IBillService {
	
	@Autowired
	private BillsDao billsDao;

	@Override
	public List<Bills> getAllBil() {
		return billsDao.getAllBill();
	}

	  @Override
	    public List<BillDetailDTO> getBillDetailByID(long id_bills) {
	        return billsDao.getBillDetailByID(id_bills); // Trả về danh sách từ DAO
	    }
	  
	  
	@Override
	public int deleteBill(int id) {
		billsDao.deleteBillDetail(id);
		return billsDao.deleteBill(id);
	}
	
	@Override
	public void updateStatus(long id, int status) {
		billsDao.updateOrderStatus(id, status);
	}
	

}
