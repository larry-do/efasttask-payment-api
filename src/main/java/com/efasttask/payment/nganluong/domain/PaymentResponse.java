package com.efasttask.payment.nganluong.domain;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String _error_code;
    private String _checkout_url;
    private String _Token;
    private String _description;

    public String getError_code() {
        return _error_code;
    }

    public void setError_code(String _error_code) {
        this._error_code = _error_code;
    }

    public String getCheckout_url() {
        return _checkout_url;
    }

    public void setCheckout_url(String _checkout_url) {
        this._checkout_url = _checkout_url;
    }

    public String getToken() {
        return _Token;
    }

    public void setToken(String _Token) {
        this._Token = _Token;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }
}
