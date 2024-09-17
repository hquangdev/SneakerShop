package StripeConfig;


import org.springframework.context.annotation.Configuration;

@Configuration
public class VNPayConfig {
    public static final String TMN_CODE = "458MV29D";
    public static final String HASH_SECRET = "BQV0Z2MFORWNAGCLGMZDKXBZS6KF1";
    public static final String PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String RETURN_URL = "http://localhost:8080/payment-result";
}
