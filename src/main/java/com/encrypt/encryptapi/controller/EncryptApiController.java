package com.encrypt.encryptapi.controller;

import com.encrypt.encryptapi.model.Greeting;
import com.encrypt.encryptapi.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EncryptApiController {

    private EncryptionService encryptionService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public EncryptApiController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping("/encrypt")
    public Greeting greeting(@RequestParam(value = "value", defaultValue = "World") String value) throws IOException, InterruptedException {
        String str = encryptionService.encrypt(value);

        System.out.println(str);
        return new Greeting(counter.incrementAndGet(), String.format(template, value));
    }
}
