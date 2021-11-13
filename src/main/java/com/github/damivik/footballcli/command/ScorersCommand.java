package com.github.damivik.footballcli.command;

import java.util.concurrent.Callable;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.service.ScorerService;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "scorers", mixinStandardHelpOptions = true, description = "View top scorers for a particular league")
public class ScorersCommand implements Callable<Integer> {

	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;

	@Override
	public Integer call() throws Exception {
		ScorerService service = new ScorerService(new APIRequest(), competitionCode);
		Output output = service.scorers();
		System.out.println(output.getMessage());
		return output.getExitCode().getCode();
	}

}
