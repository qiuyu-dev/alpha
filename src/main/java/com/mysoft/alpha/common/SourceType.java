package com.mysoft.alpha.common;

public enum SourceType {
	TYPE1(1,"付费企业excel"),
	TYPE2(2,"系统维护"),
	TYPE3(3,"API上传");
	
	private final int value;

	private final String reasonPhrase;

	SourceType(int value, String reasonPhrase) {
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
