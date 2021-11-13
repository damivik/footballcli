package com.github.damivik.footballcli.output;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String title;
	
	private List<String> columnHeaders;

	private List<List<String>> rows = new ArrayList<>();

	public void setTitle(String title) {
		this.title = title;
	}
	public void setColumnHeaders(List<String> columnHeaders) {
		this.columnHeaders = columnHeaders;
	}

	public void addRow(List<String> row) {
		rows.add(row);
	}

	public String build() {
		TableBuilder builder = new TableBuilder();
		List<Integer> columnsWidth;
		StringBuilder table = new StringBuilder();
		if (columnHeaders != null) {
			columnsWidth = new TableComputation().columnsWidth(columnHeaders, rows);
		}
		else {
			columnsWidth = new TableComputation().columnsWidth(rows);
		}
		StringBuilder border = builder.buildHorizontalBorder(columnsWidth);
		if (title != null) {
			table
				.append(border)
				.append(builder.buildTitle(title));
		}
		if (columnHeaders != null) {
			
			table
				.append(border)
				.append(builder.buildHeader(columnsWidth, columnHeaders));
		}
		
		table.append(border);
		
		for(List<String> row: rows) {
			table.append(builder.buildRow(columnsWidth, row));
		}
		
		table.append(border);
		
		return table.toString();
	}
}