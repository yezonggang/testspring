package com.example.testspring.req;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class R<T> {
    private long code;
    private T data;
    private String msg;
    private Map<String, Object> extra;
    private String traceId;

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public R() {
        this.traceId = MDC.get("log-trace-id");
    }

    public R(ErrorCode errorCode) {
        this.traceId = MDC.get("log-trace-id");
        errorCode = (ErrorCode)Optional.ofNullable(errorCode).orElse(LightErrorCode.INTERNAL_ERROR);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public R(long code, T data, String msg) {
        this.traceId = MDC.get("log-trace-id");
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public R(long code, String msg) {
        this(code, (T) null, msg);
    }

    public R(ErrorCode errorCode, T data) {
        this.traceId = MDC.get("log-trace-id");
        errorCode = (ErrorCode)Optional.ofNullable(errorCode).orElse(LightErrorCode.INTERNAL_ERROR);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        LightErrorCode aec = LightErrorCode.OK;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = LightErrorCode.INTERNAL_ERROR;
        }

        return new R(aec, data);
    }

    public static <T> R<T> ok() {
        return new R(LightErrorCode.OK);
    }

    public static <T> R<T> failed(String msg) {
        return new R(LightErrorCode.INTERNAL_ERROR.getCode(), msg);
    }

    public static <T> R<T> failed(ErrorCode errorCode) {
        return new R(errorCode);
    }

    public static <T> R<T> failed(ErrorCode errorCode, String msg) {
        return new R(errorCode.getCode(), msg);
    }

    public static <T> R<T> build(boolean b) {
        return b ? ok() : failed((ErrorCode)LightErrorCode.OPERATION_FAILURE);
    }

    public static <T> R<T> created(boolean b) {
        return b ? ok() : failed((ErrorCode)LightErrorCode.CREATED_FAILURE);
    }

    public static <T> R<T> updated(boolean b) {
        return b ? ok() : failed((ErrorCode)LightErrorCode.UPDATED_FAILURE);
    }

    public static <T> R<T> deleted(boolean b) {
        return b ? ok() : failed((ErrorCode)LightErrorCode.DELETED_FAILURE);
    }

    public static <T> R<T> build(T data) {
        return data != null ? ok(data) : failed((ErrorCode)LightErrorCode.SELECTED_FAILURE);
    }

    public R<T> put(String key, Object value) {
        if (!StringUtils.isEmpty(key) && value != null) {
            if (this.extra == null) {
                this.extra = new HashMap();
            }

            this.extra.put(key, value);
            return this;
        } else {
            throw new LightException(LightErrorCode.INTERNAL_ERROR);
        }
    }

    public R<T> put(Map<String, Object> extraMap) {
        if (extraMap == null) {
            throw new LightException(LightErrorCode.INTERNAL_ERROR);
        } else {
            if (this.extra == null) {
                this.extra = new HashMap();
            }

            this.extra.putAll(extraMap);
            return this;
        }
    }

    public long getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public R<T> setCode(final long code) {
        this.code = code;
        return this;
    }

    public R<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public R<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public <T> R<T> orFailed(String message) {
        if (this.isPresent()) {
            return (R<T>) this;
        } else {
            this.setCode(LightErrorCode.INTERNAL_ERROR.getCode());
            this.setMsg(message);
            return (R<T>) this;
        }
    }

    public <T> R<T> orFailed(ErrorCode errorCode) {
        if (this.isPresent()) {
            return (R<T>) this;
        } else {
            this.setErrorCode(errorCode);
            return (R<T>) this;
        }
    }

    public <T> R<T> orFailed(Predicate<T> predicate, ErrorCode errorCode) {
        if (predicate.test((T) this.data)) {
            return (R<T>) this;
        } else {
            this.setErrorCode(errorCode);
            return (R<T>) this;
        }
    }

    public <T> R<T> orFailed(Predicate<T> predicate, String message) {
        if (predicate.test((T) this.data)) {
            return (R<T>) this;
        } else {
            this.setCode(LightErrorCode.INTERNAL_ERROR.getCode());
            this.setMsg(message);
            return (R<T>) this;
        }
    }

    protected void setErrorCode(ErrorCode errorCode) {
        this.msg = errorCode.getMsg();
        this.code = errorCode.getCode();
    }

    private boolean isPresent() {
        return this.data != null;
    }

    public Map<String, Object> getExtra() {
        return this.extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }
}
