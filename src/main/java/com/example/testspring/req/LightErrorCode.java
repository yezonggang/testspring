package com.example.testspring.req;

public enum LightErrorCode implements ErrorCode {
    OK(200L, "执行成功"),
    CREATED(201L, "实例已创建"),
    NOT_MODIFIED(304L, "未修改"),
    UNAUTHORIZED(401L, "未授权"),
    FORBIDDEN(403L, "禁止访问，请重新登录"),
    NOT_FOUND(404L, "资源未找到"),
    INTERNAL_ERROR(500L, "系统内部异常"),
    OPERATION_FAILURE(501L, "操作失败"),
    CREATED_FAILURE(502L, "创建失败"),
    UPDATED_FAILURE(503L, "更新失败"),
    DELETED_FAILURE(504L, "删除失败"),
    SELECTED_FAILURE(505L, "查询失败");

    private final long code;
    private final String msg;

    private LightErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static LightErrorCode fromCode(long code) {
        LightErrorCode[] ecs = values();
        LightErrorCode[] var3 = ecs;
        int var4 = ecs.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            LightErrorCode ec = var3[var5];
            if (ec.getCode() == code) {
                return ec;
            }
        }

        return OK;
    }

    public long getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", this.code, this.msg);
    }
}
