package com.github.damivik.footballcli;

import java.time.LocalDateTime;

public class Match {
	private int id;
	private Team homeTeam;
	private Team awayTeam;
	private Competition competition;
	private Score score;
	private MatchStatus status;
	private LocalDateTime utcDate;

	public Match() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public MatchStatus getStatus() {
		return status;
	}

	public LocalDateTime getUtcDate() {
		return utcDate;
	}

	public void setUtcDate(LocalDateTime utcDate) {
		this.utcDate = utcDate;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}
}
