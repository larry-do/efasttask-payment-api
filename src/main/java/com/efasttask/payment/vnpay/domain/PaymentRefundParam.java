package com.efasttask.payment.vnpay.domain;

public class PaymentRefundParam extends VnpayParam {
    private String vnp_Version;
    private String vnp_Command;
    protected String vnp_TmnCode;
    private String vnp_Amount;
    private String vnp_TxnRef;
    private String vnp_OrderInfo;
    private String vnp_TransDate;
    private String vnp_IpAddr;
    private String vnp_CreateBy;
    private String vnp_TransactionType;
    private String vnp_CreateDate;

    public PaymentRefundParam() {
    }

    public PaymentRefundParam(String vnp_Version, String vnp_Command, String vnp_Amount, String vnp_TmnCode, String vnp_HashSecret, String vnp_TxnRef, String vnp_OrderInfo,
                              String vnp_TransDate, String vnp_IpAddr, String vnp_CreateBy, String vnp_TransactionType, String vnp_CreateDate) {
        this.vnp_Version = vnp_Version;
        this.vnp_Command = vnp_Command;
        this.vnp_Amount = vnp_Amount;
        this.vnp_TmnCode = vnp_TmnCode;
        this.vnp_HashSecret = vnp_HashSecret;
        this.vnp_TxnRef = vnp_TxnRef;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_TransDate = vnp_TransDate;
        this.vnp_IpAddr = vnp_IpAddr;
        this.vnp_CreateBy = vnp_CreateBy;
        this.vnp_TransactionType = vnp_TransactionType;
        this.vnp_CreateDate = vnp_CreateDate;
    }

    public PaymentRefundParam(String vnp_Amount, String vnp_TmnCode, String vnp_HashSecret, String vnp_TxnRef, String vnp_OrderInfo, String vnp_TransDate,
                              String vnp_IpAddr, String vnp_CreateBy, String vnp_TransactionType, String vnp_CreateDate) {
        this.vnp_Version = "2.1.0";
        this.vnp_Command = "refund";
        this.vnp_Amount = vnp_Amount;
        this.vnp_TmnCode = vnp_TmnCode;
        this.vnp_HashSecret = vnp_HashSecret;
        this.vnp_TxnRef = vnp_TxnRef;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_TransDate = vnp_TransDate;
        this.vnp_IpAddr = vnp_IpAddr;
        this.vnp_CreateBy = vnp_CreateBy;
        this.vnp_TransactionType = vnp_TransactionType;
        this.vnp_CreateDate = vnp_CreateDate;
    }

    public String getVnp_Version() {
        return vnp_Version;
    }

    public void setVnp_Version(String vnp_Version) {
        this.vnp_Version = vnp_Version;
    }

    public String getVnp_Command() {
        return vnp_Command;
    }

    public void setVnp_Command(String vnp_Command) {
        this.vnp_Command = vnp_Command;
    }

    public String getVnp_Amount() {
        return vnp_Amount;
    }

    public void setVnp_Amount(String vnp_Amount) {
        this.vnp_Amount = vnp_Amount;
    }

    public String getVnp_TxnRef() {
        return vnp_TxnRef;
    }

    public void setVnp_TxnRef(String vnp_TxnRef) {
        this.vnp_TxnRef = vnp_TxnRef;
    }

    public String getVnp_OrderInfo() {
        return vnp_OrderInfo;
    }

    public void setVnp_OrderInfo(String vnp_OrderInfo) {
        this.vnp_OrderInfo = vnp_OrderInfo;
    }

    public String getVnp_TransDate() {
        return vnp_TransDate;
    }

    public void setVnp_TransDate(String vnp_TransDate) {
        this.vnp_TransDate = vnp_TransDate;
    }

    public String getVnp_IpAddr() {
        return vnp_IpAddr;
    }

    public void setVnp_IpAddr(String vnp_IpAddr) {
        this.vnp_IpAddr = vnp_IpAddr;
    }

    public String getVnp_CreateBy() {
        return vnp_CreateBy;
    }

    public void setVnp_CreateBy(String vnp_CreateBy) {
        this.vnp_CreateBy = vnp_CreateBy;
    }

    public String getVnp_TransactionType() {
        return vnp_TransactionType;
    }

    public void setVnp_TransactionType(String vnp_TransactionType) {
        this.vnp_TransactionType = vnp_TransactionType;
    }

    public String getVnp_CreateDate() {
        return vnp_CreateDate;
    }

    public void setVnp_CreateDate(String vnp_CreateDate) {
        this.vnp_CreateDate = vnp_CreateDate;
    }

    public String getVnp_TmnCode() {
        return vnp_TmnCode;
    }

    public void setVnp_TmnCode(String vnp_TmnCode) {
        this.vnp_TmnCode = vnp_TmnCode;
    }
}
