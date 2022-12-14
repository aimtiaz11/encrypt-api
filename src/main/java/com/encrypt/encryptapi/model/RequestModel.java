package com.encrypt.encryptapi.model;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import jakarta.validation.constraints.NotBlank;

public class RequestModel {
    @NotBlank(message = "Field `value` cannot be null")
    private String value;

    @NotBlank(message = "Field `environment` cannot be null")
    private String environment;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
