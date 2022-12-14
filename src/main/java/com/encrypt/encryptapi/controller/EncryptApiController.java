package com.encrypt.encryptapi.controller;

import com.encrypt.encryptapi.model.RequestModel;
import com.encrypt.encryptapi.model.ResponseModel;
import com.encrypt.encryptapi.service.EncryptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/encryption-api/api/v1")
public class EncryptApiController {

    private final EncryptionService encryptionService;

    @Autowired
    public EncryptApiController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostMapping(
            value = "/encrypt",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseModel greeting(@Valid @RequestBody RequestModel request) throws IOException, InterruptedException {
        String encryptedValue = null;
        String envType = request.getEnvironment();
        if (!("PROD".equals(envType) || "NONPROD".equals(envType))) {
            throw new IllegalArgumentException("Invalid environment type selected: " + envType);
        }

        encryptedValue = encryptionService.encrypt(request.getValue(), envType);

        return new ResponseModel(encryptedValue);
    }
}
