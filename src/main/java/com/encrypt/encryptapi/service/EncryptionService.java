package com.encrypt.encryptapi.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface EncryptionService {
    public String encrypt(String valueToEncrypt, String envType) throws IOException, InterruptedException;
}
