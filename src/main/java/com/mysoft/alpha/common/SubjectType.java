package com.mysoft.alpha.common;

public enum SubjectType {
	TYPE0(0,"不限"),
	TYPE1(1,"客户"),
	TYPE2(2,"保险企业"),
	TYPE3(3,"服务企业");
	
	private final int value;

	private final String reasonPhrase;

	SubjectType(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}
	
	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}
