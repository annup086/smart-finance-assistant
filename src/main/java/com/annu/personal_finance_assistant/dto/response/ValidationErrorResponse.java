package com.annu.personal_finance_assistant.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ValidationErrorResponse(String message, int status, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
