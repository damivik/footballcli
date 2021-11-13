package com.github.damivik.footballcli.output;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TableComputation2Test {
	private List<List<String>> rows = Arrays.asList(
			Arrays.asList("1", "Chelsea", "11", "23", "26"),
			Arrays.asList("2", "Man City", "11", "16", "23"),
			Arrays.asList("2", "West Ham", "11", "10", "23"),
			Arrays.asList("3", "Liverpool", "11", "20", "22"));
	
	@Test
	void columnsWidth_tableWithColumnHeaders() {
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "P", "GD", "Pts");
		TableComputation2 computation = new TableComputation2();
		List<Integer> expectedColumnWidths = Arrays.asList(3, 9, 2, 2, 3);
		
		List<Integer> actualColumnWidths = computation.computeColumnWidths(5, columnHeaders, rows);
		
		assertEquals(expectedColumnWidths, actualColumnWidths);
	}

	@Test
	void columnsWidth_tableWithoutColumnHeaders() {
		TableComputation2 computation = new TableComputation2();
		List<Integer> expectedColumnWidths = Arrays.asList(1, 9, 2, 2, 2);
		
		List<Integer> actualColumnWidths = computation.computeColumnWidths(5, rows);
		
		assertEquals(expectedColumnWidths, actualColumnWidths);
	}
	
}
