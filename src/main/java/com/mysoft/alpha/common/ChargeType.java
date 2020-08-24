package com.mysoft.alpha.common;

public enum ChargeType {
	TYPE1(1,"收款"),
	TYPE_1(-1,"退款");
	
	private final int value;

	private final String reasonPhrase;

	ChargeType(int value, String reasonPhrase) {
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
