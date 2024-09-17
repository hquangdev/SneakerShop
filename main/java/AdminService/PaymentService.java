package AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private static final String VNPAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // URL thanh toán VNPAY môi trường TEST
    private static final String VNP_TMN_CODE = "458MV29D"; // Mã Website / Terminal ID của bạn
    private static final String VNP_HASH_SECRET = "BQV0Z2MFORWNAGCLGMZDKXBZS6KF1151"; // Chuỗi bí mật tạo checksum

    public String createPaymentUrl(String orderId, String amount, String orderInfo) throws UnsupportedEncodingException {
        SortedMap<String, String> params = new TreeMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String createDate = formatter.format(new Date());

        // Cấu hình các tham số cần thiết
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", VNP_TMN_CODE);

        // Nhân amount với 100 để gửi sang VNPAY
        long amountInVND = Long.parseLong(amount) * 100; // Chuyển đổi số tiền từ đơn vị VNĐ sang VNPAY định dạng
        params.put("vnp_Amount", String.valueOf(amountInVND));

        params.put("vnp_CurrCode", "VND");
        params.put("vnp_TxnRef", orderId);
        params.put("vnp_OrderInfo", orderInfo);
        params.put("vnp_Locale", "vn");
        params.put("vnp_ReturnUrl", "http://localhost:8080/SneakerShop/payment-result");
        params.put("vnp_CreateDate", createDate);

        // Log các tham số trước khi tạo URL
        logger.info("Tham số trước khi tạo URL: {}", params);

        // Tạo chuỗi dữ liệu để mã hóa
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String encodedKey = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString());
            String encodedValue = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString());
            query.append(encodedKey).append('=').append(encodedValue).append('&');
        }

        // Loại bỏ ký tự '&' cuối cùng
        String queryUrl = query.substring(0, query.length() - 1);

        // Log chuỗi query URL trước khi tạo mã bảo mật
        logger.info("Chuỗi Query URL: {}", queryUrl);

        // Tạo mã bảo mật chữ ký
        String vnp_SecureHash = hmacSHA512(VNP_HASH_SECRET, queryUrl);
        logger.info("Chữ ký bảo mật (Secure Hash): {}", vnp_SecureHash);

        // Tạo URL thanh toán hoàn chỉnh
        String paymentUrl = VNPAY_URL + "?" + queryUrl + "&vnp_SecureHash=" + vnp_SecureHash;
        logger.info("URL thanh toán VNPAY: {}", paymentUrl);

        return paymentUrl;
    }

    private String hmacSHA512(String key, String data) {
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmacSHA512.init(secretKey);
            byte[] hashBytes = hmacSHA512.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString().toUpperCase(); // Đảm bảo chữ ký bảo mật được viết hoa
        } catch (Exception e) {
            logger.error("Không thể tạo chữ ký bảo mật", e);
            throw new RuntimeException("Không thể tạo chữ ký bảo mật", e);
        }
    }


    // Xác minh tính hợp lệ của giao dịch từ VNPAY
    public boolean verifyPayment(Map<String, String> fields, String secureHash) {
        // Tạo chuỗi để kiểm tra chữ ký bảo mật từ VNPAY
        StringBuilder data = new StringBuilder();
        fields.remove("vnp_SecureHash"); // Xóa tham số chữ ký trước khi xác minh
        SortedMap<String, String> sortedFields = new TreeMap<>(fields);
        for (Map.Entry<String, String> entry : sortedFields.entrySet()) {
            data.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        String rawData = data.substring(0, data.length() - 1); // Xóa ký tự & cuối cùng
        String generatedSecureHash = hmacSHA512(VNP_HASH_SECRET, rawData);

        // So sánh chữ ký nhận được từ VNPAY với chữ ký được tạo ra
        return generatedSecureHash.equalsIgnoreCase(secureHash);
    }

}
