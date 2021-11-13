package com.github.damivik.footballcli.output;

public class Output {
	private final ExitCode exitCode;
	private final String message;

	public ExitCode getExitCode() {
		return exitCode;
	}

	public String getMessage() {
		return message;
	}

	public Output(ExitCode exitCode, String message) {
		this.exitCode = exitCode;
		this.message = message;
	}

}
