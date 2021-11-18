package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.StandingService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "standings", mixinStandardHelpOptions = true, description = "View current standings for a particular league")
public class StandingsCommand implements Callable<Integer> {
	@Spec
	private CommandSpec spec;
	
	private StandingService service;
	
	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;
	
	public StandingsCommand(StandingService service ) {
		this.service = service;
	}

	@Override
	public Integer call() throws Exception {
		Output output = service.getStandings(competitionCode);

		if (output.getExitCode() == ExitCode.SUCCESS) {
			spec.commandLine().getOut().println(output.getMessage());
		} else {
			spec.commandLine().getErr().println(output.getMessage());
		}

		return output.getExitCode().getCode();

	}

}
