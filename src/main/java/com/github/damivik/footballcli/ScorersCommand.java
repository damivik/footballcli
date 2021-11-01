package com.github.damivik.footballcli;

import java.util.Arrays;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "scorers", mixinStandardHelpOptions = true, description = "View top scorers for a particular league")
public class ScorersCommand implements Callable<Integer> {

	@Option(names = { "-c",
			"--competition" }, required = true, paramLabel = "COMPETITION", description = "Competition code")
	private String competitionCode;

	@Override
	public Integer call() throws Exception {
		ScorersResponse response = new APIRequest().getScorers(competitionCode);
		
		Table table = new Table();
		table.setColumnHeaders(Arrays.asList("Rank", "Player", "Team", "Nationality", "Goals"));
		int rank = 1;
		for(Scorer scorer: response.getScorers()) {
			table.addRow(Arrays.asList(
					String.valueOf(rank++),
					scorer.getPlayer().getName(),
					scorer.getTeam().getName(),
					scorer.getPlayer().getNationality(),
					String.valueOf(scorer.getNumberOfGoals())));
		}
		System.out.println(table.build());
 		/*
		 * 

		for (Standing standing : response.getStanding()) {
			if (standing.getTable().length > 0) {

				
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

		 */
		return 0;
	}

}
