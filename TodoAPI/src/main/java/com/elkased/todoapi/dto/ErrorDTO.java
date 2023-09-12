package com.elkased.todoapi.dto;

import java.time.LocalDateTime;

public class ErrorDTO {

    private String message;

    private String path;
    private LocalDateTime timestamp;

    public ErrorDTO() {
    }

    public ErrorDTO(String message, String path) {
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
