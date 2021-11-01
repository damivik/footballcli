package com.github.damivik.footballcli;

import java.util.Arrays;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "standings", mixinStandardHelpOptions = true, description = "View current standings for a particular league")
public class StandingsCommand implements Callable<Integer> {

	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;

	@Override
	public Integer call() throws Exception {
		StandingsResponse response = new APIRequest().getStandings(competitionCode);

		for (Standing standing : response.getStanding()) {
			if (standing.getTable().length > 0) {

				Table table = new Table();
				if (standing.getGroup() != null) {
					table.setTitle(standing.getGroup());
				}

				table.setColumnHeaders(Arrays.asList("Pos", "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "Pts"));
				for (LeagueTable leagueTable : standing.getTable()) {
					table.addRow(Arrays.asList(leagueTable.getPosition() + "", leagueTable.getTeam().getName(),
							leagueTable.getPlayedGames() + "", leagueTable.getWon() + "", leagueTable.getDraw() + "",
							leagueTable.getLost() + "", leagueTable.getGoalsFor() + "",
							leagueTable.getGoalsAgainst() + "", leagueTable.getGoalDifference() + "",
							leagueTable.getPoints() + ""));
				}
				System.out.println(table.build());
			}
		}
		return 0;
	}

}
