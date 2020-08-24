package com.mysoft.alpha.common;

public enum ProductType {
	TYPE1(1,"普通"),
	TYPE2(2,"保险"),
	TYPE3(3,"保险配套服务");
	
	private final int value;

	private final String reasonPhrase;

	ProductType(int value, String reasonPhrase) {
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
