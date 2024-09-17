package ControllerUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stripe.model.PaymentIntent;

import AdminDAO.productDAO;
import AdminDTO.ProductDTO;
import AdminService.PaymentService;
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
	
//	@Autowired
//	private PaymentService paymentService;

    @Value("${stripe.api.key}")
    private String apiKey;
	
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
	    session.setAttribute("TotalQuantyCart", totalQuantityCart);
	    session.setAttribute("TotalSalePrice", totalSalePrice);

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
	    
	    Users loginUser = (Users) session.getAttribute("LoginInfo");

	    if (loginUser == null) {
	        _mvShare.setViewName("redirect:/register");
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
	    HashMap<Long, CartDTO> carts = (HashMap<Long, CartDTO>) session.getAttribute("cart");
	    Double totalSalePrice = 0.0;

	    // Tính tổng giá sau giảm giá của tất cả các sản phẩm trong giỏ hàng
	    if (carts != null) {
	        for (Map.Entry<Long, CartDTO> entry : carts.entrySet()) {
	            CartDTO cartDTO = entry.getValue();
	            double productPrice = cartDTO.getProduct().getPrice();
	            int productSalePercentage = cartDTO.getProduct().getSale(); // Lấy % sale của sản phẩm

	            // Tính giá sau giảm
	            double discountFactor = (100.0 - productSalePercentage) / 100.0;
	            double salePrice = productPrice * discountFactor;

	            // Tổng tiền cho sản phẩm này (số lượng * giá sau giảm)
	            double totalProductSalePrice = salePrice * cartDTO.getQuanty();

	            // Cộng dồn vào tổng tiền hóa đơn
	            totalSalePrice += totalProductSalePrice;
	        }
	    }

	    // Gán số lượng và tổng giá từ session vào hóa đơn
	    bill.setQuanty(totalQuantityCart);
	    bill.setTotal(totalSalePrice); // Lưu tổng giá sau khi giảm giá

	    // Lưu hóa đơn vào cơ sở dữ liệu
	    if (billService.AddBills(bill) > 0) {
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

	    // Xóa giỏ hàng khỏi session
	    session.removeAttribute("cart");
	    session.removeAttribute("TotalPriceCart");
	    session.removeAttribute("SalePercentage");
	    session.removeAttribute("TotalSalePrice");

	    return "redirect:/cart";
	}


	
	


}
