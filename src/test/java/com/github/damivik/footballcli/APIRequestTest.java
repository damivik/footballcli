package com.github.damivik.footballcli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("it")
class APIRequestTest {

	@Test
	void getCompetitions() {
		APIRequest request = new APIRequest();
		
		assertAll(() -> request.getCompetitions());
	}
	
	@Test
	void getMatches_withOnlyDaysFromTodayArgumentSupplied() {
		APIRequest request = new APIRequest();
		int daysFromToday = 1;
		
		assertAll(() -> request.getMatches(daysFromToday));
	}
	
	@Test
	void getMatches_withDaysFromTodayAndCompetitionCodeArgumentsSupplied() {
		APIRequest request = new APIRequest();
		int daysFromToday = 1;
		String competitionCode = "PL";
		
		assertAll(() -> request.getMatches(daysFromToday, competitionCode));
	}

	@Test
	void getStandings() {
		APIRequest request = new APIRequest();
		String competitionCode = "PL";
		
		assertAll(() -> request.getStandings(competitionCode));
	}
	
	@Test
	void getScorers() {
		APIRequest request = new APIRequest();
		String competitionCode = "PL";
		
		assertAll(() -> request.getScorers(competitionCode));
	}

}
