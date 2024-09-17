package Service.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SneakerShop.DTO.CartDTO;
import SneakerShopDAO.CartDAO;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartDAO cartDAO; // Để Spring tự động inject CartDAO

    //thêm sản phẩm vòa giỏ hàng
    @Override
    public HashMap<Long, CartDTO> AddCart(long id, HashMap<Long, CartDTO> cart, String size, int quantity, double salePrice) {
        return cartDAO.AddCart(id, cart, size, quantity);
    }


    @Override
    public HashMap<Long, CartDTO> EditCart(long id, int quanty, HashMap<Long, CartDTO> cart) {
        return cartDAO.EditCart(id, quanty, cart);
    }

    @Override
    public HashMap<Long, CartDTO> DeleteCart(long id, HashMap<Long, CartDTO> cart) {
        return cartDAO.DeleteCart(id, cart);
    }

    @Override
    public int TotalQuanty(HashMap<Long, CartDTO> cart) {
        return cartDAO.TotalQuanty(cart);
    }

    @Override
    public double TotalPrice(HashMap<Long, CartDTO> cart) {
        return cartDAO.TotalPrice(cart);
    }
}
