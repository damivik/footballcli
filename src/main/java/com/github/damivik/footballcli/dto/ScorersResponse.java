package com.github.damivik.footballcli.dto;

public class ScorersResponse {

	private Competition competition;
	private Scorer[] scorers;
	
	public ScorersResponse() {
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Scorer[] getScorers() {
		return scorers;
	}

	public void setScorers(Scorer[] scorers) {
		this.scorers = scorers;
	}
	
}
