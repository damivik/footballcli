package com.github.damivik.footballcli.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.dto.Scorer;
import com.github.damivik.footballcli.dto.ScorersResponse;
import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.output.TableRenderer;

public class ScorerService {
	private APIRequest apiRequest;
	private String competitionCode;

	public ScorerService(APIRequest apiRequest, String competitionCode) {
		this.apiRequest = apiRequest;
		this.competitionCode = competitionCode;
	}

	public Output scorers() {
		try {
			ScorersResponse response = apiRequest.getScorers(competitionCode);
			TableRenderer renderer = new TableRenderer();
			int rank = 1;
			List<List<String>>scorerRows = new ArrayList<>();
			for (Scorer scorer : response.getScorers()) {
				scorerRows.add(
						Arrays.asList(String.valueOf(rank++), scorer.getPlayer().getName(), scorer.getTeam().getName(),
								scorer.getPlayer().getNationality(), String.valueOf(scorer.getNumberOfGoals())));
			}

			String renderedTable = renderer
					.setColumnCount(5)
					.setColumnHeaders(Arrays.asList("Rank", "Player", "Team", "Nationality", "Goals"))
					.setRows(scorerRows)
					.render();
			
			return new Output(ExitCode.SUCCESS, renderedTable);
		} catch (Exception e) {
			return new Output(ExitCode.ERROR, e.getMessage());
		}
	}
}
