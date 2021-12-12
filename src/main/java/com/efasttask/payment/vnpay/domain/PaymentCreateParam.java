package com.efasttask.payment.vnpay.domain;

import com.efasttask.payment.vnpay.Bank;

public class PaymentCreateParam extends VnpayParam {
    private String vnp_Version;
    private String vnp_Command;
    protected String vnp_TmnCode;
    private String vnp_TxnRef;

    private String vnp_OrderInfo;

    private String vnp_IpAddr;

    private String vnp_CreateDate;

    private String vnp_Amount;
    private String vnp_CurrCode;
    private String vnp_BankCode;
    private String vnp_OrderType;
    private String vnp_Locale;
    private String vnp_ReturnUrl;
    private String vnp_ExpireDate;
    private String vnp_Bill_Mobile;
    private String vnp_Bill_Email;
    private String vnp_Bill_FirstName;
    private String vnp_Bill_LastName;
    private String vnp_Bill_Address;
    private String vnp_Bill_City;
    private String vnp_Bill_Country;
    private String vnp_Bill_State;
    private String vnp_Inv_Phone;
    private String vnp_Inv_Email;
    private String vnp_Inv_Customer;
    private String vnp_Inv_Address;
    private String vnp_Inv_Company;
    private String vnp_Inv_Taxcode;
    private String vnp_Inv_Type;

    public PaymentCreateParam() {
    }

    public PaymentCreateParam(String vnp_Version, String vnp_Command, String vnp_TmnCode, String vnp_HashSecret, String vnp_Amount, String vnp_CurrCode, String vnp_BankCode,
                              String vnp_TxnRef, String vnp_OrderInfo, String vnp_OrderType, String vnp_Locale, String vnp_ReturnUrl, String vnp_IpAddr,
                              String vnp_CreateDate, String vnp_ExpireDate, String vnp_Bill_Mobile, String vnp_Bill_Email, String vnp_Bill_FirstName,
                              String vnp_Bill_LastName, String vnp_Bill_Address, String vnp_Bill_City, String vnp_Bill_Country, String vnp_Bill_State,
                              String vnp_Inv_Phone, String vnp_Inv_Email, String vnp_Inv_Customer, String vnp_Inv_Address, String vnp_Inv_Company,
                              String vnp_Inv_Taxcode, String vnp_Inv_Type) {
        this.vnp_Version = vnp_Version;
        this.vnp_Command = vnp_Command;
        this.vnp_TmnCode = vnp_TmnCode;
        this.vnp_HashSecret = vnp_HashSecret;
        this.vnp_Amount = vnp_Amount;
        this.vnp_CurrCode = vnp_CurrCode;
        this.vnp_BankCode = vnp_BankCode;
        this.vnp_TxnRef = vnp_TxnRef;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_OrderType = vnp_OrderType;
        this.vnp_Locale = vnp_Locale;
        this.vnp_ReturnUrl = vnp_ReturnUrl;
        this.vnp_IpAddr = vnp_IpAddr;
        this.vnp_CreateDate = vnp_CreateDate;
        this.vnp_ExpireDate = vnp_ExpireDate;
        this.vnp_Bill_Mobile = vnp_Bill_Mobile;
        this.vnp_Bill_Email = vnp_Bill_Email;
        this.vnp_Bill_FirstName = vnp_Bill_FirstName;
        this.vnp_Bill_LastName = vnp_Bill_LastName;
        this.vnp_Bill_Address = vnp_Bill_Address;
        this.vnp_Bill_City = vnp_Bill_City;
        this.vnp_Bill_Country = vnp_Bill_Country;
        this.vnp_Bill_State = vnp_Bill_State;
        this.vnp_Inv_Phone = vnp_Inv_Phone;
        this.vnp_Inv_Email = vnp_Inv_Email;
        this.vnp_Inv_Customer = vnp_Inv_Customer;
        this.vnp_Inv_Address = vnp_Inv_Address;
        this.vnp_Inv_Company = vnp_Inv_Company;
        this.vnp_Inv_Taxcode = vnp_Inv_Taxcode;
        this.vnp_Inv_Type = vnp_Inv_Type;
    }

    public PaymentCreateParam(String vnp_TmnCode, String vnp_HashSecret, String vnp_Amount, String vnp_CurrCode, String vnp_BankCode, String vnp_TxnRef,
                              String vnp_OrderInfo, String vnp_OrderType, String vnp_Locale, String vnp_ReturnUrl, String vnp_IpAddr,
                              String vnp_CreateDate, String vnp_ExpireDate, String vnp_Bill_Mobile, String vnp_Bill_Email,
                              String vnp_Bill_FirstName, String vnp_Bill_LastName, String vnp_Bill_Address, String vnp_Bill_City,
                              String vnp_Bill_Country, String vnp_Bill_State, String vnp_Inv_Phone, String vnp_Inv_Email, String vnp_Inv_Customer,
                              String vnp_Inv_Address, String vnp_Inv_Company, String vnp_Inv_Taxcode, String vnp_Inv_Type) {
        this.vnp_Version = "2.1.0";
        this.vnp_Command = "pay";
        this.vnp_TmnCode = vnp_TmnCode;
        this.vnp_HashSecret = vnp_HashSecret;
        this.vnp_Amount = vnp_Amount;
        this.vnp_CurrCode = vnp_CurrCode;
        this.vnp_BankCode = vnp_BankCode;
        this.vnp_TxnRef = vnp_TxnRef;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_OrderType = vnp_OrderType;
        this.vnp_Locale = vnp_Locale;
        this.vnp_ReturnUrl = vnp_ReturnUrl;
        this.vnp_IpAddr = vnp_IpAddr;
        this.vnp_CreateDate = vnp_CreateDate;
        this.vnp_ExpireDate = vnp_ExpireDate;
        this.vnp_Bill_Mobile = vnp_Bill_Mobile;
        this.vnp_Bill_Email = vnp_Bill_Email;
        this.vnp_Bill_FirstName = vnp_Bill_FirstName;
        this.vnp_Bill_LastName = vnp_Bill_LastName;
        this.vnp_Bill_Address = vnp_Bill_Address;
        this.vnp_Bill_City = vnp_Bill_City;
        this.vnp_Bill_Country = vnp_Bill_Country;
        this.vnp_Bill_State = vnp_Bill_State;
        this.vnp_Inv_Phone = vnp_Inv_Phone;
        this.vnp_Inv_Email = vnp_Inv_Email;
        this.vnp_Inv_Customer = vnp_Inv_Customer;
        this.vnp_Inv_Address = vnp_Inv_Address;
        this.vnp_Inv_Company = vnp_Inv_Company;
        this.vnp_Inv_Taxcode = vnp_Inv_Taxcode;
        this.vnp_Inv_Type = vnp_Inv_Type;
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

    public String getVnp_CurrCode() {
        return vnp_CurrCode;
    }

    public void setVnp_CurrCode(String vnp_CurrCode) {
        this.vnp_CurrCode = vnp_CurrCode;
    }

    public String getVnp_BankCode() {
        return vnp_BankCode;
    }

    public void setVnp_BankCode(String vnp_BankCode) {
        this.vnp_BankCode = vnp_BankCode;
    }

    public void setVnp_BankCode(Bank bank) {
        this.vnp_BankCode = bank.toString();
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

    public String getVnp_OrderType() {
        return vnp_OrderType;
    }

    public void setVnp_OrderType(String vnp_OrderType) {
        this.vnp_OrderType = vnp_OrderType;
    }

    public String getVnp_Locale() {
        return vnp_Locale;
    }

    public void setVnp_Locale(String vnp_Locale) {
        this.vnp_Locale = vnp_Locale;
    }

    public String getVnp_ReturnUrl() {
        return vnp_ReturnUrl;
    }

    public void setVnp_ReturnUrl(String vnp_ReturnUrl) {
        this.vnp_ReturnUrl = vnp_ReturnUrl;
    }

    public String getVnp_IpAddr() {
        return vnp_IpAddr;
    }

    public void setVnp_IpAddr(String vnp_IpAddr) {
        this.vnp_IpAddr = vnp_IpAddr;
    }

    public String getVnp_CreateDate() {
        return vnp_CreateDate;
    }

    public void setVnp_CreateDate(String vnp_CreateDate) {
        this.vnp_CreateDate = vnp_CreateDate;
    }

    public String getVnp_ExpireDate() {
        return vnp_ExpireDate;
    }

    public void setVnp_ExpireDate(String vnp_ExpireDate) {
        this.vnp_ExpireDate = vnp_ExpireDate;
    }

    public String getVnp_Bill_Mobile() {
        return vnp_Bill_Mobile;
    }

    public void setVnp_Bill_Mobile(String vnp_Bill_Mobile) {
        this.vnp_Bill_Mobile = vnp_Bill_Mobile;
    }

    public String getVnp_Bill_Email() {
        return vnp_Bill_Email;
    }

    public void setVnp_Bill_Email(String vnp_Bill_Email) {
        this.vnp_Bill_Email = vnp_Bill_Email;
    }

    public String getVnp_Bill_FirstName() {
        return vnp_Bill_FirstName;
    }

    public void setVnp_Bill_FirstName(String vnp_Bill_FirstName) {
        this.vnp_Bill_FirstName = vnp_Bill_FirstName;
    }

    public String getVnp_Bill_LastName() {
        return vnp_Bill_LastName;
    }

    public void setVnp_Bill_LastName(String vnp_Bill_LastName) {
        this.vnp_Bill_LastName = vnp_Bill_LastName;
    }

    public String getVnp_Bill_Address() {
        return vnp_Bill_Address;
    }

    public void setVnp_Bill_Address(String vnp_Bill_Address) {
        this.vnp_Bill_Address = vnp_Bill_Address;
    }

    public String getVnp_Bill_City() {
        return vnp_Bill_City;
    }

    public void setVnp_Bill_City(String vnp_Bill_City) {
        this.vnp_Bill_City = vnp_Bill_City;
    }

    public String getVnp_Bill_Country() {
        return vnp_Bill_Country;
    }

    public void setVnp_Bill_Country(String vnp_Bill_Country) {
        this.vnp_Bill_Country = vnp_Bill_Country;
    }

    public String getVnp_Bill_State() {
        return vnp_Bill_State;
    }

    public void setVnp_Bill_State(String vnp_Bill_State) {
        this.vnp_Bill_State = vnp_Bill_State;
    }

    public String getVnp_Inv_Phone() {
        return vnp_Inv_Phone;
    }

    public void setVnp_Inv_Phone(String vnp_Inv_Phone) {
        this.vnp_Inv_Phone = vnp_Inv_Phone;
    }

    public String getVnp_Inv_Email() {
        return vnp_Inv_Email;
    }

    public void setVnp_Inv_Email(String vnp_Inv_Email) {
        this.vnp_Inv_Email = vnp_Inv_Email;
    }

    public String getVnp_Inv_Customer() {
        return vnp_Inv_Customer;
    }

    public void setVnp_Inv_Customer(String vnp_Inv_Customer) {
        this.vnp_Inv_Customer = vnp_Inv_Customer;
    }

    public String getVnp_Inv_Address() {
        return vnp_Inv_Address;
    }

    public void setVnp_Inv_Address(String vnp_Inv_Address) {
        this.vnp_Inv_Address = vnp_Inv_Address;
    }

    public String getVnp_Inv_Company() {
        return vnp_Inv_Company;
    }

    public void setVnp_Inv_Company(String vnp_Inv_Company) {
        this.vnp_Inv_Company = vnp_Inv_Company;
    }

    public String getVnp_Inv_Taxcode() {
        return vnp_Inv_Taxcode;
    }

    public void setVnp_Inv_Taxcode(String vnp_Inv_Taxcode) {
        this.vnp_Inv_Taxcode = vnp_Inv_Taxcode;
    }

    public String getVnp_Inv_Type() {
        return vnp_Inv_Type;
    }

    public void setVnp_Inv_Type(String vnp_Inv_Type) {
        this.vnp_Inv_Type = vnp_Inv_Type;
    }

    public String getVnp_TmnCode() {
        return vnp_TmnCode;
    }

    public void setVnp_TmnCode(String vnp_TmnCode) {
        this.vnp_TmnCode = vnp_TmnCode;
    }
}
