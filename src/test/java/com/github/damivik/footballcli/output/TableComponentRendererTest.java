package com.github.damivik.footballcli.output;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TableComponentRendererTest {

	@Test
	void renderBorder() {
		TableComponentRenderer renderer = new TableComponentRenderer(Arrays.asList(1, 2, 3, 4));
		StringBuilder expectedBorder = new StringBuilder("----------------------\n");
		
		StringBuilder actualBorder = renderer.renderBorder();
		
		assertEquals(expectedBorder.toString(), actualBorder.toString());
	}
	
	@Test
	void renderHeader() {
		TableComponentRenderer renderer = new TableComponentRenderer(Arrays.asList(3, 9, 2, 2, 3));
		StringBuilder expectedHeader = new StringBuilder("Pos   Team        P    GD   Pts   \n");
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "P", "GD", "Pts");
		
		StringBuilder actualHeader = renderer.renderHeader(columnHeaders);
				
		assertEquals(expectedHeader.toString(), actualHeader.toString());
	}
	
	@Test
	void renderRow() {
		TableComponentRenderer renderer = new TableComponentRenderer(Arrays.asList(3, 9, 2, 2, 3));
		StringBuilder expectedRow = new StringBuilder("1     Chelsea     11   23   26    \n");
		List<String> row = Arrays.asList("1", "Chelsea", "11", "23", "26");
		
		StringBuilder actualRow = renderer.renderRow(row);
		
		assertEquals(expectedRow.toString(), actualRow.toString());
	}
}
