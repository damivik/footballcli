package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.ScorerService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "scorers", mixinStandardHelpOptions = true, description = "View top scorers for a particular league")
public class ScorersCommand implements Callable<Integer> {
	private ScorerService service;
	
	@Spec
	private CommandSpec spec;
	
	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;
	
	public ScorersCommand(ScorerService service) {
		this.service = service;
	}

	@Override
	public Integer call() throws Exception {
		Output output = service.getScorers(competitionCode);

		if (output.getExitCode() == ExitCode.SUCCESS) {
			spec.commandLine().getOut().println(output.getMessage());
		} else {
			spec.commandLine().getErr().println(output.getMessage());
		}

		return output.getExitCode().getCode();

	}

}
