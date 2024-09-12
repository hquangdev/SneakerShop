package ControllerUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import AdminDAO.productDAO;
import AdminDTO.ProductDTO;
import AdminService.ProductService;
import Service.user.BillsServiceImpl;
import Service.user.CartServiceImpl;
import SneakerShop.DTO.CartDTO;
import SneakerShop.Entity.Bills;
import SneakerShop.Entity.Users;
import SneakerShopDAO.CartDAO;

@Controller
public class CartController extends BaseController{
	private static final String Interger = null;

	@Autowired
	private CartServiceImpl cartservice = new CartServiceImpl();
	
	@Autowired
	private BillsServiceImpl billService = new BillsServiceImpl();
	
	@Autowired
	private ProductService productsv;
	
	@Autowired CartDAO cartDao;
	
	//chuyển tới trang giỏ hàng
	@RequestMapping(value = { "/cart" })
    public ModelAndView Index() {
        _mvShare.addObject("slides", _homService.GetDataSlide());
        _mvShare.addObject("categorys", _homService.GetDataCategorys());
        _mvShare.addObject("products", _homService.GetNewProducts());
        _mvShare.setViewName("user/cart");
        return _mvShare;
    }
	
	@RequestMapping(value = "/AddCart/{id}", method = RequestMethod.POST)
	public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {
	    String size = request.getParameter("size");
	    String quantityStr = request.getParameter("quantity");

	    int quantity = 1; // Giá trị mặc định
	    try {
	        quantity = Integer.parseInt(quantityStr);
	    } catch (NumberFormatException e) {
	        System.out.println("Số lượng giày: " + quantityStr);
	    }

	    // Lấy giỏ hàng từ session
	    HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");
	    if (cart == null) {
	        cart = new HashMap<>();
	    }

	    // Lấy thông tin sản phẩm từ dịch vụ
	    ProductDTO product = productsv.getProductByIDD(id);
	    double price = product.getPrice();
	    double salePercentage = product.getSale(); // Tỷ lệ giảm giá của sản phẩm
	    double salePrice = price - (price * salePercentage / 100); // Tính giá sau giảm

	    // Cập nhật giỏ hàng
	    cart = cartservice.AddCart(id, cart, size, quantity, salePrice);

	    // Tính tổng giá và tổng số lượng trong giỏ hàng
	    double totalPriceCart = cartservice.TotalPrice(cart); // Tổng giá gốc
	    double totalSalePrice = totalPriceCart * (100 - salePercentage) / 100; // Tổng giá sau giảm
	    int totalQuantityCart = cartservice.TotalQuanty(cart);

	    // Lưu thông tin vào session
	    session.setAttribute("cart", cart);
	    session.setAttribute("TotalPriceCart", totalPriceCart);
	    session.setAttribute("TotalQuantyCart", totalQuantityCart);
	    session.setAttribute("TotalSalePrice", totalSalePrice); // Lưu tỷ lệ giảm giá vào session

	    return "redirect:" + request.getHeader("referer");
	}

	
	//sửa giỏ hàng
	@RequestMapping(value = "EditCart/{id}/{quanty}")
	public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable long id, @PathVariable int quanty) {
	    @SuppressWarnings("unchecked")
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");
	    if (cart == null) {
	        cart = new HashMap<Long, CartDTO>();
	    }
	    cart = cartservice.EditCart(id, quanty, cart); // Chỉnh sửa ở đây
	    session.setAttribute("cart", cart);
	    
	    session.setAttribute("TotalPriceCart", cartservice.TotalPrice(cart));
	    session.setAttribute("TotalQuantyCart", cartservice.TotalQuanty(cart));
	    return "redirect:" + request.getHeader("referer");
	}

	
	//Xóa khỏi gió hàng
	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request ,HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDTO>();
		}
		cart = cartservice.DeleteCart(id, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("TotalPriceCart", cartservice.TotalPrice(cart));
		session.setAttribute("TotalQuantyCart", cartservice.TotalQuanty(cart));
		return "redirect:"+request.getHeader("referer");
	}
	
//----------------phần thanh toán--------------------------
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView Checkout(HttpServletRequest request, HttpSession session) {
	    // Khởi tạo ModelAndView
	    _mvShare.setViewName("user/account/checkout");
	    
	    Users loginUser = (Users) session.getAttribute("LoginInfo");// Lấy thông tin người dùng từ session

	    if (loginUser == null) {
	        _mvShare.setViewName("redirect:/dang-ky");
	        _mvShare.addObject("message", "Bạn cần đăng nhập để thực hiện thanh toán.");
	        return _mvShare;
	    }

	    // Lấy giỏ hàng từ session
	    HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("cart");

	    // Kiểm tra nếu giỏ hàng trống
	    if (cart == null || cart.isEmpty()) {
	        _mvShare.setViewName("redirect:/cart");
	        _mvShare.addObject("message", "Giỏ hàng của bạn đang trống. Vui lòng thêm sản phẩm trước khi thanh toán.");
	        return _mvShare;
	    }
	    Bills bills = new Bills();
	    bills.setAddress(loginUser.getAddress());
	    bills.setDisplay_name(loginUser.getDisplay_name());
	    bills.setUser(loginUser.getEmail());

	    _mvShare.addObject("bills", bills);

	    return _mvShare;
	}

	
	 @RequestMapping(value = "checkout", method = RequestMethod.POST)
	    public String checkoutBill(HttpSession session, @ModelAttribute("bills") Bills bill) {
		  // Gán trạng thái mặc định nếu chưa có giá trị
		    if (bill.getStatus() == 0) {
		        bill.setStatus(1); // Thiết lập trạng thái là "Đang xử lý"
		    }
		    
	        // Lấy dữ liệu từ session
	        Integer totalQuantityCart = (Integer) session.getAttribute("TotalQuantyCart");
	        Double totalPriceCart = (Double) session.getAttribute("TotalPriceCart");
	        Double salePercentage = (Double) session.getAttribute("SalePercentage");

	        // Khởi tạo biến tổng giá sau giảm
	        BigDecimal totalSalePrice = BigDecimal.ZERO;

	        // Kiểm tra và tính toán giá sau giảm nếu dữ liệu hợp lệ
	        if (totalPriceCart != null && totalPriceCart > 0) {
	            if (salePercentage == null) {
	                salePercentage = 0.0; // Nếu không có tỷ lệ giảm giá, mặc định là 0%
	            }
	            // Tính giá sau giảm
	            BigDecimal priceCart = BigDecimal.valueOf(totalPriceCart);
	            BigDecimal salePercent = BigDecimal.valueOf(salePercentage);
	            BigDecimal discountFactor = BigDecimal.valueOf(100).subtract(salePercent).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
	            totalSalePrice = priceCart.multiply(discountFactor);

	            // Làm tròn tổng giá sau giảm đến 1 chữ số thập phân
	            totalSalePrice = totalSalePrice.setScale(1, RoundingMode.HALF_UP);

	            // Chuyển đổi giá trị đã làm tròn thành số nguyên bằng cách nhân với 10
	            BigDecimal adjustedSalePrice = totalSalePrice.multiply(BigDecimal.valueOf(10));
	            int finalSalePrice = adjustedSalePrice.intValue();

	            // Gán số lượng và tổng giá đã giảm vào hóa đơn
	            bill.setQuanty(totalQuantityCart);
	            bill.setTotal(finalSalePrice);

	            // Lưu hóa đơn vào cơ sở dữ liệu
	            if (billService.AddBills(bill) > 0) {
	                @SuppressWarnings("unchecked")
	                HashMap<Long, CartDTO> carts = (HashMap<Long, CartDTO>) session.getAttribute("cart");

	                // Thêm chi tiết hóa đơn vào cơ sở dữ liệu
	                billService.AddbillsDetail(carts);

	                // Cập nhật số lượng kho
	                for (Map.Entry<Long, CartDTO> entry : carts.entrySet()) {
	                    Long productId = entry.getKey();
	                    CartDTO cartDTO = entry.getValue();
	                    String size = cartDTO.getSize(); // Lấy kích cỡ từ giỏ hàng
	                    int quantityInCart = cartDTO.getQuanty();

	                    // Tìm ID kích cỡ của sản phẩm
	                    long productSizeId = cartDao.findProductSizeId(productId, size);

	                    // Cập nhật số lượng kho cho sản phẩm theo kích cỡ
	                    cartDao.updateStock(productSizeId, quantityInCart);
	                }
	            }
	        }

	        // Xóa giỏ hàng khỏi session
	        session.removeAttribute("cart");
	        session.removeAttribute("TotalPriceCart");
	        session.removeAttribute("SalePercentage");

	        return "redirect:/cart";
	    }
	

}
