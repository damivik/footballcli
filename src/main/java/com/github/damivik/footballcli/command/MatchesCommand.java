package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.MatchService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(
		name = "matches",
		mixinStandardHelpOptions = true,
		description = "List matches")
public class MatchesCommand implements Callable<Integer> {
	@Spec
	private CommandSpec spec;
	
	private MatchService matchService;
	
	@Option(
			names = { "-d", "--days" },
			paramLabel = "DAYS",
			description = "Number of days from today")
	private int days;

	@Option(
			names = { "-c",	"--competition" },
			paramLabel = "COMPETITION",
			description = "Competition code. You can get a list of available competitions code with the 'competition' command")
	private String competitionOption = null;

	public MatchesCommand(MatchService matchService) {
		this.matchService = matchService;
	}
	
	@Override
	public Integer call() throws Exception {
		Output output = competitionOption == null ? matchService.getMatches(days)
				: matchService.getMatches(competitionOption, days);

		System.out.print(output.getMessage());

		return output.getExitCode().getCode();
	}
}
