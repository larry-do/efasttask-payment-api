package com.efasttask.payment.nganluong;

import com.efasttask.payment.nganluong.domain.PaymentRequest;
import com.efasttask.payment.nganluong.domain.PaymentResponse;
import com.efasttask.payment.nganluong.domain.CheckOrderRequest;
import com.efasttask.payment.nganluong.domain.CheckOrderResponse;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class NganLuongPaymentAPI {
    public final String LIVE_ENDPOINT = "https://www.nganluong.vn/checkout.api.nganluong.post.php";
    public final String SANDBOX_ENDPOINT = "https://sandbox.nganluong.vn:8088/nl35/checkout.api.nganluong.post.php";


    public PaymentRequest createPaymentRequest(String merchant_id, String merchant_password, String merchant_email,
                                               String order_code, String order_description,
                                               String currency_code, String total_price, String shipping_fee, String discount_amt, String tax_amt,
                                               String return_url, String cancel_url,
                                               String bank_code, String full_name, String email, String mobile, String payment_method) {
        PaymentRequest request = new PaymentRequest();
        request.setFuntion("SetExpressCheckout");
        request.setVersion("3.1");
        request.setMerchant_id(merchant_id);
        request.setMerchant_password(merchant_password);
        request.setReceiver_email(merchant_email);
        request.setCur_code(currency_code);
        request.setPayment_method(payment_method);
        request.setBank_code(bank_code);
        request.setOrder_code(order_code);
        request.setTotal_amount(total_price);
        request.setFee_shipping(shipping_fee);
        request.setDiscount_amount(discount_amt);
        request.setTax_amount(tax_amt);
        request.setOrder_description(order_description);
        request.setReturn_url(return_url);
        request.setCancel_url(cancel_url);
        request.setBuyer_email(email);
        request.setBuyer_mobile(mobile);
        try {
            request.setBuyer_fullname(URLEncoder.encode(full_name, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public CheckOrderRequest createCheckOrderRequest(String merchant_id, String merchant_password, String token) {
        CheckOrderRequest request = new CheckOrderRequest();
        request.setFuntion("GetTransactionDetail");
        request.setVersion("3.1");
        request.setMerchant_id(merchant_id);
        request.setMerchant_password(merchant_password);
        request.setToken(token);
        return request;
    }

    public PaymentResponse sendPaymentRequest(PaymentRequest paymentRequest) throws Exception {
        return sendPaymentRequest(paymentRequest, LIVE_ENDPOINT);
    }

    public PaymentResponse sendPaymentRequest(PaymentRequest paymentRequest, String endpoint) throws Exception {
        String result = call(endpoint, getPostParameters(paymentRequest));
        result = result.replace("&", "&amp;");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(result));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("result");

        PaymentResponse response = new PaymentResponse();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList name = element.getElementsByTagName("error_code");
            Element line = (Element) name.item(0);
            response.setError_code(getCharacterDataFromElement(line));

            NodeList title = element.getElementsByTagName("token");
            line = (Element) title.item(0);
            response.setToken(getCharacterDataFromElement(line));

            NodeList description = element.getElementsByTagName("description");
            line = (Element) description.item(0);
            response.setDescription(getCharacterDataFromElement(line));

            NodeList checkout_url = element.getElementsByTagName("checkout_url");
            line = (Element) checkout_url.item(0);
            response.setCheckout_url(getCharacterDataFromElement(line));
        }
        return response;
    }

    public CheckOrderResponse getOrderInformation(CheckOrderRequest checkOrderRequest) throws Exception {
        return getOrderInformation(checkOrderRequest, LIVE_ENDPOINT);
    }

    public CheckOrderResponse getOrderInformation(CheckOrderRequest checkOrderRequest, String endpoint) throws Exception {
        String _params = "";
        _params += "function=" + checkOrderRequest.getFuntion();
        _params += "&version=" + checkOrderRequest.getVersion();
        _params += "&merchant_id=" + checkOrderRequest.getMerchant_id();
        _params += "&merchant_password=" + createMD5Hash(checkOrderRequest.getMerchant_password());
        _params += "&token=" + checkOrderRequest.getToken();
        String result = call(endpoint, _params);
        result = result.replace("&", "&amp;");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(result));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("result");

        CheckOrderResponse response = new CheckOrderResponse();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList name = element.getElementsByTagName("error_code");
            Element line = (Element) name.item(0);
            response.setError_code(getCharacterDataFromElement(line));

            NodeList title = element.getElementsByTagName("token");
            line = (Element) title.item(0);
            response.setToken(getCharacterDataFromElement(line));

            NodeList description = element.getElementsByTagName("description");
            line = (Element) description.item(0);
            response.setDescription(getCharacterDataFromElement(line));

            NodeList transaction_status = element.getElementsByTagName("transaction_status");
            line = (Element) transaction_status.item(0);
            response.setTransaction_status(getCharacterDataFromElement(line));

            NodeList receiver_email = element.getElementsByTagName("receiver_email");
            line = (Element) receiver_email.item(0);
            response.setReceiver_email(getCharacterDataFromElement(line));

            NodeList order_code = element.getElementsByTagName("order_code");
            line = (Element) order_code.item(0);
            response.setOrder_code(getCharacterDataFromElement(line));

            NodeList total_amount = element.getElementsByTagName("total_amount");
            line = (Element) total_amount.item(0);
            response.setTotal_amount(getCharacterDataFromElement(line));

            NodeList payment_method = element.getElementsByTagName("payment_method");
            line = (Element) payment_method.item(0);
            response.setPayment_method(getCharacterDataFromElement(line));

            NodeList bank_code = element.getElementsByTagName("bank_code");
            line = (Element) bank_code.item(0);
            response.setBank_code(getCharacterDataFromElement(line));

            NodeList payment_type = element.getElementsByTagName("payment_type");
            line = (Element) payment_type.item(0);
            response.setPayment_type(getCharacterDataFromElement(line));

            NodeList order_description = element.getElementsByTagName("order_description");
            line = (Element) order_description.item(0);
            response.setOrder_description(getCharacterDataFromElement(line));

            NodeList tax_amount = element.getElementsByTagName("tax_amount");
            line = (Element) tax_amount.item(0);
            response.setTax_amount(getCharacterDataFromElement(line));

            NodeList discount_amount = element.getElementsByTagName("discount_amount");
            line = (Element) discount_amount.item(0);
            response.setDiscount_amount(getCharacterDataFromElement(line));

            NodeList fee_shipping = element.getElementsByTagName("fee_shipping");
            line = (Element) fee_shipping.item(0);
            response.setFee_shipping(getCharacterDataFromElement(line));

            NodeList return_url = element.getElementsByTagName("return_url");
            line = (Element) return_url.item(0);
            response.setReturn_url(getCharacterDataFromElement(line));

            NodeList cancel_url = element.getElementsByTagName("cancel_url");
            line = (Element) cancel_url.item(0);
            response.setCancel_url(getCharacterDataFromElement(line));

            NodeList buyer_fullname = element.getElementsByTagName("buyer_fullname");
            line = (Element) buyer_fullname.item(0);
            response.setBuyer_fullname(getCharacterDataFromElement(line));

            NodeList buyer_email = element.getElementsByTagName("buyer_email");
            line = (Element) buyer_email.item(0);
            response.setBuyer_email(getCharacterDataFromElement(line));

            NodeList buyer_mobile = element.getElementsByTagName("buyer_mobile");
            line = (Element) buyer_mobile.item(0);
            response.setBuyer_mobile(getCharacterDataFromElement(line));

            NodeList buyer_address = element.getElementsByTagName("buyer_address");
            line = (Element) buyer_address.item(0);
            response.setBuyer_address(getCharacterDataFromElement(line));

            NodeList affiliate_code = element.getElementsByTagName("affiliate_code");
            line = (Element) affiliate_code.item(0);
            response.setAffiliate_code(getCharacterDataFromElement(line));

            NodeList transaction_id = element.getElementsByTagName("transaction_id");
            line = (Element) transaction_id.item(0);
            response.setTransaction_id(getCharacterDataFromElement(line));
        }
        return response;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    private static String call(String endpointUrl, String params) throws Exception {
        URL url = new URL(endpointUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setReadTimeout(180000);
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream((con.getOutputStream()))) {
            wr.writeBytes(params);
            wr.flush();
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
        }
        return response.substring(0, response.length() - 1);
    }

    private static String getPostParameters(PaymentRequest request) throws Exception {
        String _param = "";
        _param += "function=" + request.getFuntion();
        _param += "&cur_code=" + request.getCur_code();
        _param += "&version=" + request.getVersion();
        _param += "&merchant_id=" + request.getMerchant_id();
        _param += "&receiver_email=" + request.getReceiver_email();
        _param += "&merchant_password=" + createMD5Hash(request.getMerchant_password());
        _param += "&order_code=" + request.getOrder_code();
        _param += "&total_amount=" + request.getTotal_amount();
        _param += "&payment_method=" + request.getPayment_method();
        _param += "&bank_code=" + request.getBank_code();
        _param += "&payment_type=" + request.getPayment_type();
        _param += "&order_description=" + request.getOrder_description();
        _param += "&tax_amount=" + request.getTax_amount();
        _param += "&fee_shipping=" + request.getFee_shipping();
        _param += "&discount_amount=" + request.getDiscount_amount();
        _param += "&return_url=" + request.getReturn_url();
        _param += "&cancel_url=" + request.getCancel_url();
        _param += "&buyer_fullname=" + request.getBuyer_fullname();
        _param += "&buyer_email=" + request.getBuyer_email();
        _param += "&buyer_mobile=" + request.getBuyer_mobile();
        return _param;
    }

    private static String createMD5Hash(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String getErrorMessage(String errorCode) {
        if (StringUtils.isEmpty(errorCode)) return "";
        switch (errorCode) {
            case "00":
                return "Giao dịch thành công";
            case "01":
                return "Lỗi, địa chỉ IP truy cập API của NgânLượng.vn bị từ chối";
            case "02":
                return "Lỗi, tham số gửi từ merchant tới NgânLượng.vn chưa chính xác.";
            case "03":
                return "Lỗi, mã merchant không tồn tại hoặc merchant đang bị khóa kết nối tới NgânLượng.vn";
            case "04":
                return "Lỗi, mã checksum không chính xác";
            case "05":
                return "Tài khoản nhận tiền nạp của merchant không tồn tại";
            case "06":
                return "Tài khoản nhận tiền nạp của  merchant đang bị khóa hoặc bị phong tỏa, không thể thực hiện được giao dịch nạp tiền";
            case "07":
                return "Thẻ đã được sử dụng";
            case "08":
                return "Thẻ bị khóa";
            case "09":
                return "Thẻ hết hạn sử dụng";
            case "10":
                return "Thẻ chưa được kích hoạt hoặc không tồn tại";
            case "11":
                return "Mã thẻ sai định dạng";
            case "12":
                return "Sai số serial của thẻ";
            case "13":
                return "Mã thẻ và số serial không khớp";
            case "14":
                return "Thẻ không tồn tại";
            case "15":
                return "Thẻ không sử dụng được";
            case "16":
                return "Số lần thử của thẻ vượt quá giới hạn cho phép";
            case "17":
                return "Hệ thống Telco bị lỗi hoặc quá tải, thẻ chưa bị trừ";
            case "18":
                return "Hệ thống Telco  bị lỗi hoặc quá tải, thẻ có thể bị trừ, cần phối hợp với nhà mạng để đối soát";
            case "19":
                return "Kết nối NgânLượng với Telco bị lỗi, thẻ chưa bị trừ.";
            case "20":
                return "Kết nối tới Telco thành công, thẻ bị trừ nhưng chưa cộng tiền trên NgânLượng.vn";
            case "99":
                return "Lỗi tuy nhiên lỗi chưa được định nghĩa hoặc chưa xác định được nguyên nhân";
            default:
                return "";
        }
    }
}
