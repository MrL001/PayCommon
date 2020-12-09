package com.hntyy.common;

import lombok.Data;

@Data
public class Result<T> {

    private T data;
    private String message;
    private Integer code;

    public Result(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
