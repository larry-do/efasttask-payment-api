package com.efasttask.payment.nganluong;

import com.efasttask.payment.nganluong.domain.PaymentRequest;
import com.efasttask.payment.nganluong.domain.PaymentResponse;
import com.efasttask.payment.nganluong.domain.CheckOrderRequest;
import com.efasttask.payment.nganluong.domain.CheckOrderResponse;
import com.efasttask.util.EftSecurity;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

public class NganLuongPaymentAPI {
    public static final String LIVE_ENDPOINT = "https://www.nganluong.vn/checkout.api.nganluong.post.php";
    public static final String SANDBOX_ENDPOINT = "https://sandbox.nganluong.vn:8088/nl35/checkout.api.nganluong.post.php";


    public static PaymentRequest createPaymentRequest(String merchant_id, String merchant_password, String merchant_email,
                                                      String order_code, String order_description,
                                                      String currency_code, String total_price, String shipping_fee, String discount_amt, String tax_amt,
                                                      String return_url, String cancel_url,
                                                      String bank_code, String buyer_full_name, String buyer_email, String buyer_mobile, String payment_method) {
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
        request.setBuyer_email(buyer_email);
        request.setBuyer_mobile(buyer_mobile);
        try {
            request.setBuyer_fullname(URLEncoder.encode(buyer_full_name, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public static CheckOrderRequest createCheckOrderRequest(String merchant_id, String merchant_password, String token) {
        CheckOrderRequest request = new CheckOrderRequest();
        request.setFuntion("GetTransactionDetail");
        request.setVersion("3.1");
        request.setMerchant_id(merchant_id);
        request.setMerchant_password(merchant_password);
        request.setToken(token);
        return request;
    }

    public static PaymentResponse sendPaymentRequest(PaymentRequest paymentRequest) throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
        return sendPaymentRequest(paymentRequest, LIVE_ENDPOINT);
    }

    public static PaymentResponse sendPaymentRequest(PaymentRequest paymentRequest, String endpoint) throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
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

    public static CheckOrderResponse getOrderInformation(CheckOrderRequest checkOrderRequest) throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
        return getOrderInformation(checkOrderRequest, LIVE_ENDPOINT);
    }

    public static CheckOrderResponse getOrderInformation(CheckOrderRequest checkOrderRequest, String endpoint) throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
        String _params = "";
        _params += "function=" + checkOrderRequest.getFuntion();
        _params += "&version=" + checkOrderRequest.getVersion();
        _params += "&merchant_id=" + checkOrderRequest.getMerchant_id();
        _params += "&merchant_password=" + EftSecurity.md5(checkOrderRequest.getMerchant_password());
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

    private static String call(String endpointUrl, String params) throws IOException {
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

    private static String getPostParameters(PaymentRequest request) throws NoSuchAlgorithmException {
        String _param = "";
        _param += "function=" + request.getFuntion();
        _param += "&cur_code=" + request.getCur_code();
        _param += "&version=" + request.getVersion();
        _param += "&merchant_id=" + request.getMerchant_id();
        _param += "&receiver_email=" + request.getReceiver_email();
        _param += "&merchant_password=" + EftSecurity.md5(request.getMerchant_password());
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

    private static String getMessage(String code) {
        if (StringUtils.isEmpty(code)) return "";
        switch (code) {
            case "00":
                return "Giao d???ch th??nh c??ng";
            case "01":
                return "L???i, ?????a ch??? IP truy c???p API c???a Ng??nL?????ng.vn b??? t??? ch???i";
            case "02":
                return "L???i, tham s??? g???i t??? merchant t???i Ng??nL?????ng.vn ch??a ch??nh x??c.";
            case "03":
                return "L???i, m?? merchant kh??ng t???n t???i ho???c merchant ??ang b??? kh??a k???t n???i t???i Ng??nL?????ng.vn";
            case "04":
                return "L???i, m?? checksum kh??ng ch??nh x??c";
            case "05":
                return "T??i kho???n nh???n ti???n n???p c???a merchant kh??ng t???n t???i";
            case "06":
                return "T??i kho???n nh???n ti???n n???p c???a merchant ??ang b??? kh??a ho???c b??? phong t???a, kh??ng th??? th???c hi???n ???????c giao d???ch n???p ti???n";
            case "07":
                return "Th??? ???? ???????c s??? d???ng";
            case "08":
                return "Th??? b??? kh??a";
            case "09":
                return "Th??? h???t h???n s??? d???ng";
            case "10":
                return "Th??? ch??a ???????c k??ch ho???t ho???c kh??ng t???n t???i";
            case "11":
                return "M?? th??? sai ?????nh d???ng";
            case "12":
                return "Sai s??? serial c???a th???";
            case "13":
                return "M?? th??? v?? s??? serial kh??ng kh???p";
            case "14":
                return "Th??? kh??ng t???n t???i";
            case "15":
                return "Th??? kh??ng s??? d???ng ???????c";
            case "16":
                return "S??? l???n th??? c???a th??? v?????t qu?? gi???i h???n cho ph??p";
            case "17":
                return "H??? th???ng Telco b??? l???i ho???c qu?? t???i, th??? ch??a b??? tr???";
            case "18":
                return "H??? th???ng Telco  b??? l???i ho???c qu?? t???i, th??? c?? th??? b??? tr???, c???n ph???i h???p v???i nh?? m???ng ????? ?????i so??t";
            case "19":
                return "K???t n???i Ng??nL?????ng v???i Telco b??? l???i, th??? ch??a b??? tr???.";
            case "20":
                return "K???t n???i t???i Telco th??nh c??ng, th??? b??? tr??? nh??ng ch??a c???ng ti???n tr??n Ng??nL?????ng.vn";
            case "99":
                return "L???i tuy nhi??n l???i ch??a ???????c ?????nh ngh??a ho???c ch??a x??c ?????nh ???????c nguy??n nh??n";
            default:
                return "";
        }
    }
}
