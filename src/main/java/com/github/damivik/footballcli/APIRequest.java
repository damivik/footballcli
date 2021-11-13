package com.github.damivik.footballcli;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.damivik.footballcli.dto.CompetitionResponse;
import com.github.damivik.footballcli.dto.MatchesResponse;
import com.github.damivik.footballcli.dto.ScorersResponse;
import com.github.damivik.footballcli.dto.StandingsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import kong.unirest.HttpResponse;
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

	public CompetitionResponse getCompetitions() throws APIRequestException {
		HttpResponse<String> response = Unirest.get("/competitions").queryString("plan", "TIER_ONE").asString();

		if (response.getStatus() >= 400) {
			throw new APIRequestException(response.getBody());
		}

		return new Gson().fromJson(response.getBody(), CompetitionResponse.class);
	}

	public MatchesResponse getMatches(int daysFromToday) throws APIRequestException {
		String date = LocalDateTime.now().plusDays(daysFromToday).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

		HttpResponse<String> response = Unirest.get("/matches").queryString("dateFrom", date)
				.queryString("dateTo", date).asString();

		if (response.getStatus() >= 400) {
			throw new APIRequestException(response.getBody());
		}
		
		GsonBuilder gson = new GsonBuilder();

		gson.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

		return gson.create().fromJson(response.getBody(), MatchesResponse.class);
	}

	public MatchesResponse getMatches(int daysFromToday, String competitionCode) throws APIRequestException {
		String date = LocalDateTime.now().plusDays(daysFromToday).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

		HttpResponse<String> response = Unirest.get("/matches").queryString("dateFrom", date)
				.queryString("dateTo", date).queryString("competitions", competitionCode).asString();

		if (response.getStatus() >= 400) {
			throw new APIRequestException(response.getBody());
		}
		
		GsonBuilder gson = new GsonBuilder();

		gson.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

		return gson.create().fromJson(response.getBody(), MatchesResponse.class);
	}

	public StandingsResponse getStandings(String competitionCode) throws APIRequestException {
		HttpResponse<String> response = Unirest.get("/competitions/" + competitionCode + "/standings").asString();
		
		if (response.getStatus() >= 400) {
			throw new APIRequestException(response.getBody());
		}
		
		return new Gson().fromJson(response.getBody(), StandingsResponse.class);
	}

	public ScorersResponse getScorers(String competitionCode) throws APIRequestException {
		HttpResponse<String> response = Unirest.get("/competitions/" + competitionCode + "/scorers").asString();

		if (response.getStatus() >= 400) {
			throw new APIRequestException(response.getBody());
		}
		
		return new Gson().fromJson(response.getBody(), ScorersResponse.class);
	}

	private class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}

	}

}
