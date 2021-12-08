package com.efasttask.payment.vnpay;

import com.efasttask.payment.vnpay.domain.VnpayParam;
import com.efasttask.util.SecurityUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VnpayAPI {
    public final static String PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public final static String MERCHANT_API_URL = "https://sandbox.vnpayment.vn/merchant_webapi/merchant.html";

    public final static String SANDBOX_PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public final static String SANDBOX_MERCHANT_API_URL = "https://sandbox.vnpayment.vn/merchant_webapi/merchant.html";

    public static String hashParams(Map<String, String> params, String vnpayHashSecret) throws NoSuchAlgorithmException, InvalidKeyException {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(fieldValue);
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return SecurityUtils.hmacSHA512(vnpayHashSecret, sb.toString());
    }

    public static String generateCallUrl(VnpayParam param, String endpoint) {
        return endpoint + "?" + param.generateHashQuery();
    }

    public static String generatePaymentUrl(VnpayParam param, String endpoint) {
        return generateCallUrl(param, endpoint);
    }

    public static String generatePaymentUrl(VnpayParam param) {
        return generatePaymentUrl(param, PAYMENT_URL);
    }

    public static String generateApiUrl(VnpayParam param, String endpoint) {
        return generateCallUrl(param, endpoint);
    }

    public static String generateApiUrl(VnpayParam param) {
        return generateApiUrl(param, MERCHANT_API_URL);
    }
}
