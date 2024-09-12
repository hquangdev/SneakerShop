package SneakerShopDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import AdminDTO.ProductDTO;
import SneakerShop.DTO.CartDTO;

@Repository
public class CartDAO extends BaseDAO {
	
	@Autowired
	ProductsDAO productsDAO = new ProductsDAO();
	
	
	public HashMap<Long, CartDTO> AddCart(long id, HashMap<Long, CartDTO> cart, String size, int quantity) {
	    CartDTO itemCart = new CartDTO();
	    
	    ProductDTO product = productsDAO.FindProductByID(id);
	    if (product != null) {
	        if (cart.containsKey(id)) {
	            itemCart = cart.get(id);
	            itemCart.setQuanty(quantity); // Cập nhật số lượng từ form
	            itemCart.setSize(size); // Cập nhật kích thước từ form
	            itemCart.setTotalPrice(itemCart.getQuanty() * product.getPrice()); // Cập nhật giá
	        } else {
	            itemCart.setProduct(product);
	            itemCart.setQuanty(quantity); // Sử dụng số lượng từ form
	            itemCart.setSize(size); // Lưu kích thước từ form
	            itemCart.setTotalPrice(product.getPrice() * quantity); // Tính tổng giá
	        }
	        cart.put(id, itemCart);
	    }
	    return cart;
	}

	
	// Cập nhật số lượng kho cho sản phẩm theo kích cỡ
    public void updateStock(long productSizeId, int quantitySold) {
        String sql = "UPDATE product_size SET quantity = quantity - ? WHERE id = ?";
        _jdbcTemplate.update(sql, quantitySold, productSizeId);
    }

    // Tìm ID của kích cỡ sản phẩm dựa vào productId và size
    @SuppressWarnings("deprecation")
	public long findProductSizeId(long productId, String size) {
        String sql = "SELECT id FROM product_size WHERE id_productsize = ? AND sizes = ?";
        return _jdbcTemplate.queryForObject(sql, new Object[]{productId, size}, Long.class);
    }
	
	
   //Chức--- năng--- Sửa--- giỏ--- hàng
	public HashMap<Long, CartDTO> EditCart(long id, int Quanty, HashMap<Long, CartDTO> cart) {
		if(cart == null) {
			return cart;
		}
		
		CartDTO itemCart = new CartDTO();
		if(cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuanty(Quanty);
		    double totalPrice = Quanty * itemCart.getProduct().getPrice();
		    itemCart.setTotalPrice(totalPrice);
		}
        cart.put(id, itemCart);
		return cart;
	}
	
    //Chức--- năng--- Xóa--- giỏ--- hàng
		public HashMap<Long, CartDTO> DeleteCart(long id, HashMap<Long, CartDTO> cart) {
			if(cart == null) {
				return cart;
			}
			
			if(cart.containsKey(id)) {
				cart.remove(id);	
			}
			return cart;
		}
		
		public int TotalQuanty(HashMap<Long, CartDTO> cart) {
			int totalQuanty = 0;
			for(Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
				totalQuanty += itemCart.getValue().getQuanty();
			}
			return totalQuanty;
		}
		
		public double TotalPrice(HashMap<Long, CartDTO> cart) {
			double totalPrice = 0;
			for(Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
				totalPrice += itemCart.getValue().getTotalPrice();
			}
			return totalPrice;
		}
}

