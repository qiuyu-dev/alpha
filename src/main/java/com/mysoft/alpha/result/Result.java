package com.mysoft.alpha.result;

import java.io.Serializable;

/**
 *
 *  model
 *  class 输出模型
 *  Serializable 序列化用于网络传输
 *  创建空构造函数, 和全部参数函数
 *  get，set
 *  tostring
 */
//@Data
public class Result implements Serializable {
    private int code;
    private String message;
    private Object result;

    public Result() {
    }

    public Result(int code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
