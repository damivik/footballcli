package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.StandingService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "standings", mixinStandardHelpOptions = true, description = "View current standings for a particular league")
public class StandingsCommand implements Callable<Integer> {

	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;

	@Override
	public Integer call() throws Exception {
		StandingService service = new StandingService(new APIRequest(), competitionCode);
		Output output = service.standings();
		System.out.println(output.getMessage());
		return output.getExitCode().getCode();
	}

}
