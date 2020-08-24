package com.mysoft.alpha.common;

public enum ComplaintType {
	TYPE1(1,"不减扣服务费"),
	TYPE2(2,"扣服务费");
	
	private final int value;

	private final String reasonPhrase;

	ComplaintType(int value, String reasonPhrase) {
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
