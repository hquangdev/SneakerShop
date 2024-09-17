package Service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SneakerShop.DTO.CartDTO;
import SneakerShop.Entity.BillDetail;
import SneakerShop.Entity.Bills;
import SneakerShopDAO.BillsDAO;

@Service
public class BillsServiceImpl implements IBillsService {
	
	@Autowired
	private BillsDAO billsDAO;

	@Override
	public int AddBills(Bills bill) {
		return billsDAO.AddBills(bill);
	}
	
	@Override
	public void AddbillsDetail(HashMap<Long, CartDTO> carts) {
	    if (carts == null) {
	        throw new IllegalArgumentException("Không có sản phẩm");
	    }

	    long billId = billsDAO.GetIDLastBills();
	    
	    for (Map.Entry<Long, CartDTO> entry : carts.entrySet()) {
	        CartDTO cartDTO = entry.getValue();
	        
	        // Tạo đối tượng BillDetail cho từng sản phẩm
	        BillDetail billDetail = new BillDetail();
	        billDetail.setId_bills(billId); 
	        billDetail.setId_product(cartDTO.getProduct().getId_product());
	        billDetail.setQuanty(cartDTO.getQuanty());
	        billDetail.setTotal(cartDTO.getTotalPrice());
	        
	        // Lấy kích thước từ CartDTO và thiết lập cho BillDetail
	        billDetail.setSelected_size(cartDTO.getSize()); 

	        // Thêm chi tiết hóa đơn vào cơ sở dữ liệu
	        billsDAO.AddBillsDetail(billDetail);
	    }
	}


}