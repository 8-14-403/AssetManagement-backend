package com.htjn.assetManagement.entity;

/**
 * Created by caojy on 2017/12/14.
 */
public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败");



    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
