package com.github.damivik.footballcli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TableComputationTest {

	@Test
	void columnsWidth() {
		TableComputation computation = new TableComputation();
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "PTS");
		List<List<String>> data = Arrays.asList(
				Arrays.asList("1", "Liverpool", "5", "4", "2", "0", "15", "4", "11", "14"),
				Arrays.asList("2", "Man City", "5", "4", "1", "1", "12", "1", "11", "13"),
				Arrays.asList("3", "Chelsea", "5", "4", "1", "1", "12", "2", "10" , "13"),
				Arrays.asList("4", "Man United", "5", "4", "1", "1", "13", "5", "8", "13"),
				Arrays.asList("5", "Everton", "5", "4", "1", "1", "12", "7", "5", "13")
		);
		List<Integer> expectedColumnsWidth = Arrays.asList(3, 10, 2, 1, 1, 1, 2, 2, 2, 3);
		
		List<Integer> columnsWidth = computation.columnsWidth(columnHeaders, data);
		
		assertEquals(expectedColumnsWidth, columnsWidth);
	}
}