package com.efasttask.payment.vnpay;

public enum Bank {
    VIETCOMBANK("Vietcombank", "Ngân Hàng TMCP Ngoại Thương Việt Nam"), TECHCOMBANK("Techcombank", "Ngân Hàng TMCP Kỹ Thương"),
    MBBANK("MB", "Ngân Hàng TMCP Quân Đội");

    private final String shortName;
    private final String fullName;

    Bank(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
