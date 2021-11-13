package com.github.damivik.footballcli.output;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TableRendererTest {
	private List<List<String>> rows = Arrays.asList(
			Arrays.asList("1", "Chelsea", "11", "23", "26"),
			Arrays.asList("2", "Man City", "11", "16", "23"),
			Arrays.asList("2", "West Ham", "11", "10", "23"),
			Arrays.asList("3", "Liverpool", "11", "20", "22"));
	
	@Test
	void render_titleHeaderAndRows() throws Exception {
		TableRenderer renderer = new TableRenderer();
		String title = "Table_title";
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "P", "GD", "Pts");
		
		String expectedRenderedTable = 
				  "----------------------------------\n"
				+ "Table_title\n"
				+ "----------------------------------\n"
				+ "Pos   Team        P    GD   Pts   \n"
				+ "----------------------------------\n"
				+ "1     Chelsea     11   23   26    \n"
				+ "2     Man City    11   16   23    \n"
				+ "2     West Ham    11   10   23    \n"
				+ "3     Liverpool   11   20   22    \n"
				+ "----------------------------------\n";
		
		String actualRenderedTable = renderer
				.setColumnCount(5)
				.setTitle(title)
				.setColumnHeaders(columnHeaders)
				.setRows(rows)
				.render();
		
		assertEquals(expectedRenderedTable, actualRenderedTable);
	}
	
	@Test
	void render_headerAndRows() throws Exception {
		TableRenderer renderer = new TableRenderer();
		List<String> columnHeaders = Arrays.asList("Pos", "Team", "P", "GD", "Pts");
		
		String expectedRenderedTable = 
				  "----------------------------------\n"
				+ "Pos   Team        P    GD   Pts   \n"
				+ "----------------------------------\n"
				+ "1     Chelsea     11   23   26    \n"
				+ "2     Man City    11   16   23    \n"
				+ "2     West Ham    11   10   23    \n"
				+ "3     Liverpool   11   20   22    \n"
				+ "----------------------------------\n";
		
		String actualRenderedTable = renderer
				.setColumnCount(5)
				.setColumnHeaders(columnHeaders)
				.setRows(rows)
				.render();
		
		assertEquals(expectedRenderedTable, actualRenderedTable);
	}
	
	@Test
	void render_onlyRows() throws Exception {
		TableRenderer renderer = new TableRenderer();
		
		String expectedRenderedTable = 
				  "-------------------------------\n"
				+ "1   Chelsea     11   23   26   \n"
				+ "2   Man City    11   16   23   \n"
				+ "2   West Ham    11   10   23   \n"
				+ "3   Liverpool   11   20   22   \n"
				+ "-------------------------------\n";
		
		String actualRenderedTable = renderer
				.setColumnCount(5)
				.setRows(rows)
				.render();
		
		assertEquals(expectedRenderedTable, actualRenderedTable);
	}
}
