package com.github.damivik.footballcli.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.APIRequestException;
import com.github.damivik.footballcli.dto.Competition;
import com.github.damivik.footballcli.dto.CompetitionResponse;
import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.output.TableRenderer;
import com.github.damivik.footballcli.output.TableRendererException;

public class CompetitionService {
	private APIRequest apiRequest;
	private TableRenderer renderer;

	public CompetitionService(APIRequest apiRequest, TableRenderer renderer) {
		this.apiRequest = apiRequest;
		this.renderer = renderer;
	}

	public Output getAvailableCompetitions() {
		try {
			List<String> competitionColumnHeaders = Arrays.asList("Name", "Code", "Area");
			List<List<String>> competitionRows = new ArrayList<>();
			
			CompetitionResponse response;

			response = apiRequest.getCompetitions();

			for (Competition competition : response.getCompetitions()) {
				List<String> row = Arrays.asList(competition.getName(), competition.getCode(),
						competition.getArea().getName());
				competitionRows.add(row);
			}
			
			String renderedTable = renderer
					.setColumnCount(3)
					.setColumnHeaders(competitionColumnHeaders)
					.setRows(competitionRows)
					.render();
			
			return new Output(ExitCode.SUCCESS, renderedTable);
			
		} catch (APIRequestException | TableRendererException e) {
			return new Output(ExitCode.ERROR, e.getMessage());
		}
	}
}
