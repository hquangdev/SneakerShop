package Service.user;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import SneakerShop.DTO.CartDTO;
import SneakerShop.Entity.Bills;

@Service
public interface IBillsService {
	public int AddBills(Bills bill);
    public void  AddbillsDetail(HashMap<Long, CartDTO> carts);

}
