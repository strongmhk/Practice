package com.dragonfly.p2p.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

    // 객체를 구분할 때!
    private static final long serialVersonUID = 1L;

    private Map<String,String> errorMap;

    public CustomValidationApiException(String message){
        super(message); // 부모한테 message 던지기
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap){
        super(message); // 부모한테 message 던지기
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }


}
