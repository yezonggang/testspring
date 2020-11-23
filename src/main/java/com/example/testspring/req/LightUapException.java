package com.example.testspring.req;


import lombok.Getter;

public class LightUapException extends LightException {

    @Getter
    private long code;

    @Getter
    private String msg;

    public LightUapException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public LightUapException(long code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public LightUapException(String msg) {
        super(msg);
        this.code = LightErrorCode.INTERNAL_ERROR.getCode();
        this.msg = msg;
    }

    @Override
    public ErrorCode getErrorCode() {
        return LightErrorCode.fromCode(this.code);
    }

}
