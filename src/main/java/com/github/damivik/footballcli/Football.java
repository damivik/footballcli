package com.github.damivik.footballcli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
	name = "fball",
	mixinStandardHelpOptions = true,
	version = "0.0.1-SNAPSHOT",
	description = "Football cli",
	subcommands = {
		CompetitionCommand.class,
		MatchesCommand.class,
		StandingsCommand.class,
		ScorersCommand.class
	}
)
public class Football {
	
	public static void main(String[] args) {
		
		int exitCode = new CommandLine(new Football()).execute(args);
		
        System.exit(exitCode);
	}
	
}
