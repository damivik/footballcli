package com.github.damivik.footballcli.dto;

public class MatchesResponse {
	
	private int count;
	private Match[] matches;

	public MatchesResponse() {
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Match[] getMatches() {
		return matches;
	}

	public void setMatches(Match[] matches) {
		this.matches = matches;
	}
}
