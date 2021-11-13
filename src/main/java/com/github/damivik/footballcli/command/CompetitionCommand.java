package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.CompetitionService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "competition", mixinStandardHelpOptions = true, description = "List available competitions")
public class CompetitionCommand implements Callable<Integer> {
	
	@Spec
	private CommandSpec spec;
	private CompetitionService competitionService;

	public CompetitionCommand(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	
	@Override
	public Integer call() throws Exception {
		Output output = competitionService.getAvailableCompetitions();

		if (output.getExitCode() == ExitCode.SUCCESS) {
			spec.commandLine().getOut().println(output.getMessage());
		} else {
			spec.commandLine().getErr().println(output.getMessage());
		}

		return output.getExitCode().getCode();
	}

}
