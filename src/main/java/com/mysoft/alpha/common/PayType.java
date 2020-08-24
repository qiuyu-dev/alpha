package com.mysoft.alpha.common;

public enum PayType {
	TYPE1(1,"按客户付费"),
	TYPE2(2,"按客户产品");
	
	private final int value;

	private final String reasonPhrase;

	PayType(int value, String reasonPhrase) {
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
