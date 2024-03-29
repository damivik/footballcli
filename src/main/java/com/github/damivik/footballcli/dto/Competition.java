package com.github.damivik.footballcli.dto;

public class Competition {
	private Area area;
	private String code;
	private String name;
	
	public Competition() {
	}

	public Competition(Area area, String code, String name) {
		this.area = area;
		this.code = code;
		this.name = name;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
