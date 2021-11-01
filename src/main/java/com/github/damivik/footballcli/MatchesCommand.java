package com.github.damivik.footballcli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "matches", mixinStandardHelpOptions = true, description = "List matches")
public class MatchesCommand implements Callable<Integer> {

	@Option(names = { "-d", "--days" }, paramLabel = "DAYS", description = "number of days from today")
	private int days;

	@Option(names = { "-c", "--competition" }, paramLabel = "COMPETITION", description = "Competition")
	private String competitionOption = null;

	@Override
	public Integer call() throws Exception {
		MatchesResponse response = competitionOption == null ? new APIRequest().getMatches(days)
				: new APIRequest().getMatches(days, competitionOption);

		TreeMap<String, ArrayList<Match>> matchesMap = new TreeMap<>();

		for (Match match : response.getMatches()) {
			String competition = match.getCompetition().getArea().getName() + ": " + match.getCompetition().getName();
			if (matchesMap.containsKey(competition)) {
				matchesMap.get(competition).add(match);
			} else {
				ArrayList<Match> matches = new ArrayList<>();
				matches.add(match);
				matchesMap.put(competition, matches);
			}

		}

		for (String competition : matchesMap.keySet()) {
			Table table = new Table();
			table.setTitle(competition);

			for (Match match : matchesMap.get(competition)) {
				LocalDateTime dateTime = match.getUtcDate();
				String date = dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
				String time;
				String homeTeamScore;
				String awayTeamScore;

				if (match.getStatus() != MatchStatus.SCHEDULED) {
					time = match.getStatus().name();
					homeTeamScore = match.getScore().getFullTime().getHomeTeam() + "";
					awayTeamScore = match.getScore().getFullTime().getAwayTeam() + "";
				} else {
					time = dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
					homeTeamScore = "?";
					awayTeamScore = "?";
				}

				List<String> row = Arrays.asList(date, time, match.getHomeTeam().getName(), homeTeamScore, "-",
						awayTeamScore, match.getAwayTeam().getName());
				table.addRow(row);
			}
			System.out.println(table.build());
		}
		return 0;
	}

}
