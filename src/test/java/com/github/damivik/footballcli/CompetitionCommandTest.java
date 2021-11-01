package com.github.damivik.footballcli;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;

@Tag("it")
class CompetitionCommandTest {

	@Test
	void test() {
		CompetitionCommand command = new CompetitionCommand();
		CommandLine cmd = new CommandLine(command);
		StringWriter sw = new StringWriter();
		cmd.setOut(new PrintWriter(sw));

		int exitCode = cmd.execute();
		
		assertEquals(0, exitCode);
	}

}
