package com.github.damivik.footballcli;

public class Standing {

	private String group;
	private String stage;
	private LeagueTable[] table;
	
	public Standing() {
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public LeagueTable[] getTable() {
		return table;
	}

	public void setTable(LeagueTable[] table) {
		this.table = table;
	}
	
}
