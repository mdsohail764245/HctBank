package com.example.model.Response;

import lombok.Data;

@Data
public class TransactionResponse {
    private String message;
    private String statusCode;
    private long ref_id;

    public TransactionResponse(String message, String statusCode, long ref_id) {
        this.message = message;
        this.statusCode = statusCode;
        this.ref_id = ref_id;
    }
}
