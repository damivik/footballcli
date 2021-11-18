package com.github.damivik.footballcli.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.dto.Match;
import com.github.damivik.footballcli.dto.MatchStatus;
import com.github.damivik.footballcli.dto.MatchesResponse;
import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.output.TableRenderer;

public class MatchService {
	private APIRequest apiRequest;
	private TableRenderer tableRenderer;
	
	public MatchService(APIRequest apiRequest, TableRenderer tableRenderer) {
		this.apiRequest = apiRequest;
		this.tableRenderer = tableRenderer;
	}

	public Output getMatches(String competitionCode, int daysFromToday) {
		try {
			StringBuilder message = new StringBuilder();

			MatchesResponse response = apiRequest.getMatches(daysFromToday, competitionCode);

			TreeMap<String, ArrayList<Match>> matchesMap = new TreeMap<>();

			for (Match match : response.getMatches()) {
				String competition = match.getCompetition().getArea().getName() + ": "
						+ match.getCompetition().getName();
				if (matchesMap.containsKey(competition)) {
					matchesMap.get(competition).add(match);
				} else {
					ArrayList<Match> matches = new ArrayList<>();
					matches.add(match);
					matchesMap.put(competition, matches);
				}

			}

			for (String competition : matchesMap.keySet()) {
				List<List<String>> matchRows = new ArrayList<>();
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

					matchRows.add(Arrays.asList(date, time, match.getHomeTeam().getName(), homeTeamScore, "-",
							awayTeamScore, match.getAwayTeam().getName()));
				}
				message.append(tableRenderer
						.setColumnCount(7)
						.setTitle(competition)
						.setRows(matchRows)
						.render());
			}
			return new Output(ExitCode.SUCCESS, message.toString());
		} catch (Exception e) {
			return new Output(ExitCode.ERROR, e.getMessage());
		}
	}
	
	public Output getMatches(int daysFromToday) {
		try {
			StringBuilder message = new StringBuilder();

			MatchesResponse response = apiRequest.getMatches(daysFromToday);

			TreeMap<String, ArrayList<Match>> matchesMap = new TreeMap<>();

			for (Match match : response.getMatches()) {
				String competition = match.getCompetition().getArea().getName() + ": "
						+ match.getCompetition().getName();
				if (matchesMap.containsKey(competition)) {
					matchesMap.get(competition).add(match);
				} else {
					ArrayList<Match> matches = new ArrayList<>();
					matches.add(match);
					matchesMap.put(competition, matches);
				}

			}

			for (String competition : matchesMap.keySet()) {
				List<List<String>> matchRows = new ArrayList<>();
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

					matchRows.add(Arrays.asList(date, time, match.getHomeTeam().getName(), homeTeamScore, "-",
							awayTeamScore, match.getAwayTeam().getName()));
				}
				message.append(tableRenderer
						.setColumnCount(7)
						.setTitle(competition)
						.setRows(matchRows)
						.render());
			}
			return new Output(ExitCode.SUCCESS, message.toString());
		} catch (Exception e) {
			return new Output(ExitCode.ERROR, e.getMessage());
		}
	}
}