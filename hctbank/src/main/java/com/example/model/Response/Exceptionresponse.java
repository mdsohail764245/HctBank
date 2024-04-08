package com.example.model.Response;

import lombok.Data;

@Data
public class Exceptionresponse {
    private String message;
    private String code;

    public Exceptionresponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public Exceptionresponse() {

    }
}
