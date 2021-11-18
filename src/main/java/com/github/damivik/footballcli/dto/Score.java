package com.github.damivik.footballcli.dto;

public class Score {
	private String duration;
	Period haltTime;
	Period fullTime;
	Period extraTime;
	Period penalties;

	public Score() {
	}
	
	public Score(Period fullTime) {
		this.fullTime = fullTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Period getHaltTime() {
		return haltTime;
	}

	public void setHaltTime(Period haltTime) {
		this.haltTime = haltTime;
	}

	public Period getFullTime() {
		return fullTime;
	}

	public void setFullTime(Period fullTime) {
		this.fullTime = fullTime;
	}

	public Period getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Period extraTime) {
		this.extraTime = extraTime;
	}

	public Period getPenalties() {
		return penalties;
	}

	public void setPenalties(Period penalties) {
		this.penalties = penalties;
	}

	public class Period {
		private int homeTeam;
		private int awayTeam;
		
		public Period() {
		}
		
		public Period(int homeTeam, int awayTeam) {
			this.homeTeam = homeTeam;
			this.awayTeam = awayTeam;
		}

		public int getHomeTeam() {
			return homeTeam;
		}

		public void setHomeTeam(int homeTeam) {
			this.homeTeam = homeTeam;
		}

		public int getAwayTeam() {
			return awayTeam;
		}

		public void setAwayTeam(int awayTeam) {
			this.awayTeam = awayTeam;
		}
	}
}
