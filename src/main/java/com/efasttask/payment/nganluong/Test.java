package com.efasttask.payment.nganluong;

import com.efasttask.payment.nganluong.domain.CheckOrderRequest;
import com.efasttask.payment.nganluong.domain.CheckOrderResponse;
import com.efasttask.payment.nganluong.domain.PaymentRequest;
import com.efasttask.payment.nganluong.domain.PaymentResponse;

public class Test {
    public static void main(String[] args) throws Exception {
        PaymentRequest request = NganLuongPaymentAPI.createPaymentRequest(
                "65867", "cd7d987316938bf5f11210c02db91d33", "annanshakai@gmail.com",
                "order_code_1", "order_description_1",
                "vnd", "12000", "1000", "2000", "200",
                "http://localhost:8080/return_url", "http://localhost:8080/cancel_url",
                Bank.MB.toString(), "buyer_name", "buyer_email", "buyer_phone", PaymentMethod.BANK_TRANSFER_ONLINE);

        PaymentResponse response = NganLuongPaymentAPI.sendPaymentRequest(request, NganLuongPaymentAPI.LIVE_ENDPOINT);
        System.out.println("END");

        CheckOrderRequest checkOrderRequest = NganLuongPaymentAPI.createCheckOrderRequest("30439", "212325", response.getToken());
        CheckOrderResponse checkOrderResponse = NganLuongPaymentAPI.getOrderInformation(checkOrderRequest, NganLuongPaymentAPI.LIVE_ENDPOINT);
        System.out.println("END");
    }
}
