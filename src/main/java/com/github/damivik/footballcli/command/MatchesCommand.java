package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.MatchService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "matches", mixinStandardHelpOptions = true, description = "List matches")
public class MatchesCommand implements Callable<Integer> {

	@Option(names = { "-d", "--days" }, paramLabel = "DAYS", description = "Number of days from today")
	private int days;

	@Option(
			names = { "-c", "--competition" },
			paramLabel = "COMPETITION",
			description = "Competition code. You can get a list of available competitions code with the 'competition' command")
	private String competitionOption = null;

	@Override
	public Integer call() throws Exception {
		APIRequest request = new APIRequest();
		MatchService matchService;
		matchService = competitionOption == null ? new MatchService(request, days)
				: new MatchService(request, days, competitionOption);

		Output output = matchService.matches();

		System.out.print(matchService.matches().getMessage());

		return output.getExitCode().getCode();
	}
}
