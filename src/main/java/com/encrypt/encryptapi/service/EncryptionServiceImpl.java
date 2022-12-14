package com.encrypt.encryptapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Value("${encryption.algorithm}")
    private String algorithm;

    @Value("${encryption.mode}")
    private String mode;

    @Value("${encryption.key.nonprod}")
    private String nonProdKey;
    @Value("${encryption.key.prod}")
    private String prodKey;

    public String encrypt(String valueToEncrypt, String envType)
            throws IOException, InterruptedException {

        StringBuffer stringBuffer = new StringBuffer();
        Process process;
        String line;

        // Set prod or nonprod key depending on parameter.
        String encryptionKey;
        if("PROD".equals(envType))
            encryptionKey = prodKey;
        else
            encryptionKey = nonProdKey;

        String toolString = "java -cp secure-properties-tool.jar com.mulesoft.tools.SecurePropertiesTool string encrypt " + algorithm + " " + mode + " " + encryptionKey + " " + valueToEncrypt;

        process = Runtime.getRuntime().exec(toolString);
        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        while ((line = error.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }
}
