package com.github.damivik.footballcli;

import com.github.damivik.footballcli.command.CompetitionCommand;
import com.github.damivik.footballcli.command.MatchesCommand;
import com.github.damivik.footballcli.command.ScorersCommand;
import com.github.damivik.footballcli.command.StandingsCommand;
import com.github.damivik.footballcli.output.TableRenderer;
import com.github.damivik.footballcli.service.CompetitionService;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
	name = "fball",
	mixinStandardHelpOptions = true,
	version = "0.0.1-SNAPSHOT",
	description = "Football cli"
)
public class Football {
	
	public static void main(String[] args) {
		
		int exitCode = new CommandLine(new Football())
				.addSubcommand(new CompetitionCommand(new CompetitionService(new APIRequest(), new TableRenderer())))
				.addSubcommand(new MatchesCommand())
				.addSubcommand(new ScorersCommand())
				.addSubcommand(new StandingsCommand())
				.execute(args);
		
        System.exit(exitCode);
	}
	
}
