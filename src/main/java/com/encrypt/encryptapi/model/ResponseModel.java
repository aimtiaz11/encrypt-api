package com.encrypt.encryptapi.model;


public class ResponseModel {

    private String encryptedValue;

    public ResponseModel(String content) {
        this.encryptedValue = content;
    }

    public String getContent() {
        return encryptedValue;
    }

    public void setContent(String content) {
        this.encryptedValue = content;
    }
}
