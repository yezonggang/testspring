package com.example.testspring.req;

public class LightException extends RuntimeException {
    private long code;
    private String msg;

    public LightException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public LightException(long code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public LightException(String msg) {
        super(msg);
        this.code = LightErrorCode.INTERNAL_ERROR.getCode();
        this.msg = msg;
    }

    public ErrorCode getErrorCode() {
        return LightErrorCode.fromCode(this.code);
    }

    public long getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}

