package com.github.damivik.footballcli;

public class StandingsResponse {
	
	private Competition competition;
	private Standing[] standings;
	
	public StandingsResponse() {
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Standing[] getStanding() {
		return standings;
	}

	public void setStanding(Standing[] standings) {
		this.standings = standings;
	}
	
}
