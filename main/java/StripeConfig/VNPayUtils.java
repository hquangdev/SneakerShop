package StripeConfig;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VNPayUtils {

    // Hàm để mã hóa chuỗi bằng thuật toán HMAC-SHA512
    public static String hmacSHA512(String key, String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hashString.append('0');
                }
                hashString.append(hex);
            }
            return hashString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC SHA-512 hash", e);
        }
    }

    // Hàm sắp xếp các trường theo thứ tự từ điển và kết hợp lại thành chuỗi
    public static String hashAllFields(Map<String, String> fields) {
        // Sử dụng TreeMap để sắp xếp các key theo thứ tự từ điển
        Map<String, String> sortedFields = new TreeMap<>(fields);
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entrySet = sortedFields.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ((value != null) && (value.length() > 0)) {
                sb.append(key);
                sb.append('=');
                sb.append(value);
                sb.append('&');
            }
        }
        // Xóa ký tự '&' cuối cùng
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
