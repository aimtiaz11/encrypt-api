package com.encrypt.encryptapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Value("${encryption.algorithm}")
    private String algorithm;

    @Value("${encryption.mode}")
    private String mode;

    public String encrypt(String valueToEncrypt, String envType)
            throws IOException, InterruptedException {

        StringBuffer stringBuffer = new StringBuffer();
        Process process;
        String line;

        Optional<String> encryptionKeyProperty = System.getProperties()
                .stringPropertyNames()
                .stream()
                .filter(prop -> StringUtils.startsWithIgnoreCase(prop, "encryption.key." + envType))
                .findFirst();

        if (encryptionKeyProperty.isPresent()) {
            String encryptionKey = System.getProperty(encryptionKeyProperty.get());
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

        } else {
            throw new RuntimeException("Unable find encryption key for environment type: " + envType);
        }
    }
}
