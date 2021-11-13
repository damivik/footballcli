package com.github.damivik.footballcli.dto;

public class Standing {

	private String group;
	private String stage;
	private Table[] table;
	
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

	public Table[] getTable() {
		return table;
	}

	public void setTable(Table[] table) {
		this.table = table;
	}
	
}
