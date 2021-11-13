package com.github.damivik.footballcli.command;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.Football;
import com.github.damivik.footballcli.output.TableRenderer;
import com.github.damivik.footballcli.service.CompetitionService;

import picocli.CommandLine;

@Tag("it")
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CompetitionCommandTest {

	@Test
	void call() throws Exception {
		Football app = new Football();
		CommandLine cmd = new CommandLine(app);
		cmd.addSubcommand(new CompetitionCommand(new CompetitionService(new APIRequest(), new TableRenderer())));
		StringWriter sw = new StringWriter();
		cmd.setOut(new PrintWriter(sw));

		int exitCode = cmd.execute("competition");
		
		assertEquals(0, exitCode);
		assertTrue(sw.toString().contains("Name"));
		assertTrue(sw.toString().contains("Code"));
		assertTrue(sw.toString().contains("Area"));
	}

}
