package com.efasttask.payment.vnpay.domain;

import com.efasttask.util.EftSecurity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VnpayParam {
    protected String vnp_HashSecret;

    public String getVnp_HashSecret() {
        return vnp_HashSecret;
    }

    public void setVnp_HashSecret(String vnp_HashSecret) {
        this.vnp_HashSecret = vnp_HashSecret;
    }

    public String generateQuery() {
        try {
            List<String> fieldNames = Arrays.stream(this.getClass().getDeclaredFields()).map(Field::getName).sorted().collect(Collectors.toList());
            StringBuilder query = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = itr.next();
                Field field = this.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if (field.get(this) == null) continue;
                String fieldValue = (String) field.get(this);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                    }
                }
                field.setAccessible(false);
            }
            return query.toString();
        } catch (UnsupportedEncodingException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateHash() {
        try {
            List<String> fieldNames = Arrays.stream(this.getClass().getDeclaredFields()).map(Field::getName).sorted().collect(Collectors.toList());
            StringBuilder hashData = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = itr.next();
                Field field = this.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if (field.get(this) == null) continue;
                String fieldValue = (String) field.get(this);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        hashData.append('&');
                    }
                }
                field.setAccessible(false);
            }
            return EftSecurity.hmacSHA512(vnp_HashSecret, hashData.toString());
        } catch (UnsupportedEncodingException | NoSuchFieldException | IllegalAccessException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateHashQuery() {
        String vnp_SecureHash = this.generateHash();
        if (vnp_SecureHash == null) return null;
        return generateQuery().concat("&vnp_SecureHash=").concat(vnp_SecureHash);
    }
}
