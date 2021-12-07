package com.efasttask.payment.nganluong;

public enum Bank {
    VCB("Vietcombank", "Ngân Hàng TMCP Ngoại Thương Việt Nam"), TCB("Techcombank", "Ngân Hàng TMCP Kỹ Thương"),
    MB("MB", "Ngân Hàng TMCP Quân Đội");

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
