package com.encrypt.encryptapi.model;


public class ResponseModel {

    private String encryptedValue;

    public ResponseModel(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

    public String getEncryptedValue() {
        return encryptedValue;
    }

    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

}