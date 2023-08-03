package com.dragonfly.p2p.handler.ex;



public class ApiReqException extends IllegalArgumentException {

    public ApiReqException(String message) {
        super(message);
    }

    public ApiReqException(String message, Throwable cause) {
        super(message, cause);
    }
}


