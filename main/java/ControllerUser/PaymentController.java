package ControllerUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import AdminService.PaymentService;
import ControllerAdmin.BaseAdminController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class PaymentController extends BaseAdminController {
    
    @Autowired
    private PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/create-payment")
    public String createPayment(@RequestParam long amount, Model model) throws UnsupportedEncodingException {
        Logger logger = LoggerFactory.getLogger(PaymentController.class);

        String orderId = UUID.randomUUID().toString();

        // Thông tin thanh toán hiển thị trên trang VNPAY
        String orderInfo = "Thanh toán đơn hàng " + orderId;

        // Log thông tin nhận được từ view
        logger.info("Số tiền nhận được từ view: {}", amount);
        logger.info("ID đơn hàng: {}", orderId);
        logger.info("Thông tin đơn hàng: {}", orderInfo);

        // Tạo URL thanh toán qua PaymentService
        String paymentUrl = paymentService.createPaymentUrl(orderId, String.valueOf(amount), orderInfo);

        // Kiểm tra URL thanh toán trước khi redirect
        if (paymentUrl != null && !paymentUrl.isEmpty()) {
            logger.info("URL thanh toán được tạo: {}", paymentUrl);
            return "redirect:" + paymentUrl;
        } else {
            logger.error("Không thể tạo URL thanh toán.");
            model.addAttribute("error", "Không thể tạo URL thanh toán. Vui lòng thử lại.");
            return "errorPage"; // Trả về trang báo lỗi nếu không tạo được URL
        }
    }

    @GetMapping("/payment-result")
    public String paymentResult(HttpServletRequest request, Model model) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            fields.put(fieldName, fieldValue);
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        boolean isValid = paymentService.verifyPayment(fields, vnp_SecureHash);
        
        // Log kết quả xác minh với SLF4J
        logger.info("Kết quả xác minh thanh toán: {}", isValid ? "Hợp lệ" : "Không hợp lệ");

        if (isValid) {
            String resultCode = request.getParameter("vnp_ResponseCode");
            if ("00".equals(resultCode)) {
                model.addAttribute("message", "Giao dịch thành công!");
            } else {
                model.addAttribute("message", "Giao dịch không thành công. Mã lỗi: " + resultCode);
            }
        } else {
            model.addAttribute("message", "Xác minh chữ ký không hợp lệ.");
        }

        return "redirect:/cart"; // Trả về view hiển thị kết quả thanh toán
    }
}
