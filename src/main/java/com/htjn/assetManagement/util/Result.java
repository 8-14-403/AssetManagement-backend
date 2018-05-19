package com.htjn.assetManagement.util;

/**
 * http请求返回的最外层
 * {
 *     "code":int
 *     "message":String
 *     "data":Object
 * }
 * Created by caojy on 12/5/17.
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
