package com.github.damivik.footballcli.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.dto.Standing;
import com.github.damivik.footballcli.dto.StandingsResponse;
import com.github.damivik.footballcli.dto.Table;
import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.output.TableRenderer;

public class StandingService {
	private APIRequest apiRequest;
	private String competitionCode;

	public StandingService(APIRequest apiRequest, String competitionCode) {
		this.apiRequest = apiRequest;
		this.competitionCode = competitionCode;
	}

	public Output standings() {
		try {
			StandingsResponse response = apiRequest.getStandings(competitionCode);
			String message = "";
			for (Standing standing : response.getStanding()) {
				if (standing.getTable().length > 0) {

					TableRenderer renderer = new TableRenderer();
					if (standing.getGroup() != null) {
						renderer = renderer.setTitle(standing.getGroup());
					}

					List<String>columnHeader = Arrays.asList("Pos", "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "Pts");
					List<List<String>>standingRows = new ArrayList<>();
					
					for (Table leagueTable : standing.getTable()) {
						standingRows.add(Arrays.asList(leagueTable.getPosition() + "",
								leagueTable.getTeam().getName(), leagueTable.getPlayedGames() + "",
								leagueTable.getWon() + "", leagueTable.getDraw() + "", leagueTable.getLost() + "",
								leagueTable.getGoalsFor() + "", leagueTable.getGoalsAgainst() + "",
								leagueTable.getGoalDifference() + "", leagueTable.getPoints() + ""));
					}
					
					message = renderer
							.setColumnCount(10)
							.setColumnHeaders(columnHeader)
							.setRows(standingRows).render();
				}
			}
			return new Output(ExitCode.SUCCESS, message);
		} catch (Exception e) {
			return new Output(ExitCode.ERROR, e.getMessage());
		}
	}
}
