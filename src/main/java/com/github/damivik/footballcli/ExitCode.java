package com.github.damivik.footballcli;

public enum ExitCode {

	SUCCESS(0),
	
	ERROR(1);
	
	private int code;

	public int getCode() {
		return code;
	}

	private ExitCode(int code) {
		this.code = code;
	}
}
