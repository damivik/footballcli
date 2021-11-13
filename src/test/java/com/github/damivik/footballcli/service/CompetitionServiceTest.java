package com.github.damivik.footballcli.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.github.damivik.footballcli.APIRequest;
import com.github.damivik.footballcli.dto.Area;
import com.github.damivik.footballcli.dto.Competition;
import com.github.damivik.footballcli.dto.CompetitionResponse;
import com.github.damivik.footballcli.output.ExitCode;
import com.github.damivik.footballcli.output.Output;
import com.github.damivik.footballcli.output.TableRenderer;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CompetitionServiceTest {

	@Mock
	private APIRequest request;
	
	
	@Test
	void getAvailableCompetitions() throws Exception {
		CompetitionService service = new CompetitionService(request, new TableRenderer());
		Mockito.when(request.getCompetitions()).thenReturn(getCompetitionResponse());
		String expectedOutputMessage = 
				  "-----------------------------------------\n"
				+ "Name                    Code   Area      \n"
				+ "-----------------------------------------\n"      
				+ "Premier League          PL     England   \n"
				+ "UEFA Champions League   CL     Europe    \n"     
				+ "-----------------------------------------\n";
		
		Output output = service.getAvailableCompetitions();
		
		assertEquals(ExitCode.SUCCESS, output.getExitCode());
		assertEquals(expectedOutputMessage, output.getMessage());
	}
	
	private CompetitionResponse getCompetitionResponse() {
		Competition competition1 = new Competition(new Area("England"), "PL", "Premier League");
		Competition competition2 = new Competition(new Area("Europe"), "CL", "UEFA Champions League");
		
		Competition[] competitions = {competition1, competition2};
		
		CompetitionResponse response = new CompetitionResponse();
		response.setCompetitions(competitions);
		
		return response;
	}
}
