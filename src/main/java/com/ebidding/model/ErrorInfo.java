package com.ebidding.model;

import java.io.Serializable;

public class ErrorInfo implements Serializable {

    private boolean success;
    private String message ;

    public ErrorInfo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ErrorInfo() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
