package com.github.damivik.footballcli;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import kong.unirest.Unirest;

public class APIRequest {

	private final String BASE_URL = "https://api.football-data.org/v2";

	private final String AUTH_TOKEN = getAuthToken();

	public APIRequest() {
		Unirest.config().defaultBaseUrl(BASE_URL).setDefaultHeader("Accept", "application/json")
				.setDefaultHeader("X-Auth-Token", AUTH_TOKEN);
	}

	private String getAuthToken() {
		String token = System.getenv("FOOTBALL_DATA_AUTH_TOKEN");
		if (token == null) {
			throw new NullPointerException("FOOTBALL_DATA_AUTH_TOKEN env variable is not set");
		}
		return token;
	}

	public CompetitionsResponse getCompetitions() {
		String response = Unirest.get("/competitions").queryString("plan", "TIER_ONE").asString().getBody();

		return new Gson().fromJson(response, CompetitionsResponse.class);
	}

	public MatchesResponse getMatches(int daysFromToday) {
		String date;
		
		date = LocalDateTime.now().plusDays(daysFromToday).format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
		
		String response = Unirest.get("/matches")
				.queryString("dateFrom", date)
				.queryString("dateTo", date)
				.asString().getBody();

		GsonBuilder gson = new GsonBuilder();
		
		gson.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		
		return gson.create().fromJson(response, MatchesResponse.class);
	}

	public MatchesResponse getMatches(int daysFromToday, String competition) {
		String date;
		
		date = LocalDateTime.now().plusDays(daysFromToday).format(DateTimeFormatter.ofPattern("YYY-MM-dd"));
		
		String response = Unirest.get("/matches")
				.queryString("dateFrom", date)
				.queryString("dateTo", date)
				.queryString("competitions", competition)
				.asString().getBody();

		GsonBuilder gson = new GsonBuilder();
		
		gson.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		
		return gson.create().fromJson(response, MatchesResponse.class);
	}
	
	public StandingsResponse getStandings(String competitionCode) {
		String response = Unirest.get("/competitions/" + competitionCode + "/standings").asString().getBody();
	
		return new Gson().fromJson(response, StandingsResponse.class);
	}

	public ScorersResponse getScorers(String competitionCode) {
		String response = Unirest.get("/competitions/" + competitionCode + "/scorers").asString().getBody();
		
		return new Gson().fromJson(response, ScorersResponse.class);
	}
	
	private class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
		
		@Override
		public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}
		
	}
	
}
