package com.github.damivik.footballcli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class TableBuilderTest {

	@Test
	void buildHorizontalBorder() {
		TableBuilder builder = new TableBuilder();
		List<Integer> columnsWidth = Arrays.asList( 3, 10, 2, 1, 1, 1, 2, 2, 2, 3);
		StringBuilder expectedTable = new StringBuilder("-----------------------------------------------\n");

		StringBuilder border = builder.buildHorizontalBorder(columnsWidth);

		assertEquals(expectedTable.toString(), border.toString());
	}

	@Test
	void buildHeader() {
		TableBuilder builder = new TableBuilder();
		StringBuilder expectedTable = new StringBuilder(
				"Pos  Team          MP    W    D    L    GF    GA    GD    PTS\n");
		List<Integer> columnsWidth = Arrays.asList( 3, 10, 2, 1, 1, 1, 2, 2, 2, 3);
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "PTS");

		StringBuilder header = builder.buildHeader(columnsWidth, columnHeaders);

		assertEquals(expectedTable.toString(), header.toString());
	}
	
	@Test
	void buildRow() {
		TableBuilder builder = new TableBuilder();
		StringBuilder expectedTable = new StringBuilder(
				"1   Liverpool  5  4 2 0 15 4  11 14  \n");
		List<Integer> columnsWidth = Arrays.asList(3, 10, 2, 1, 1, 1, 2, 2, 2, 3);
		List<String> rowData = Arrays.asList("1", "Liverpool", "5", "4", "2", "0", "15", "4", "11", "14");

		StringBuilder row = builder.buildRow(columnsWidth, rowData);

		assertEquals(expectedTable.toString(), row.toString());
	}
}
