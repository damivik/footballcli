package com.github.damivik.footballcli.dto;

public class Scorer {

	private Integer numberOfGoals;
	private Player player;
	private Team team;
	
	public Scorer() {
	}

	public Integer getNumberOfGoals() {
		return numberOfGoals;
	}

	public void setNumberOfGoals(Integer numberOfGoals) {
		this.numberOfGoals = numberOfGoals;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
