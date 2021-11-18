package com.github.damivik.footballcli.dto;

public class Team {
	private int id;
	private String name;

	public Team() {
	}
	
	public Team(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
