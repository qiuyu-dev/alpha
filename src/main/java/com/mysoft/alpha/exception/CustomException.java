package com.mysoft.alpha.exception;

/**
 * 
 * 自定义异常类
 */
public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Integer code;

    private String msg;

    public CustomException(Integer code, String msg){
    	super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
