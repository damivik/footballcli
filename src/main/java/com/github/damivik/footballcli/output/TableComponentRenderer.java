package com.github.damivik.footballcli.output;

import java.util.List;

public class TableComponentRenderer {	
	private final int PADDING = 3;
	private List<Integer> columnWidths;
	
	public TableComponentRenderer(List<Integer> columnWidths) {
		this.columnWidths = columnWidths;
	}

	public StringBuilder renderBorder() {
		StringBuilder border = new StringBuilder();

		for (Integer columnWidth : columnWidths) {
			border.append("-".repeat(columnWidth + (PADDING)));
		}

		border.append("\n");

		return border;
	}

	public StringBuilder renderHeader(List<String> columnHeaders) {
		StringBuilder header = new StringBuilder();

		for (int i = 0; i < columnWidths.size(); i++) {
			header.append(String.format("%-" + columnWidths.get(i) + "s", columnHeaders.get(i))
					+ " ".repeat(PADDING));
		}

		header.append("\n");

		return header;
	}

	public StringBuilder renderRow(List<String> rowData) {
		StringBuilder row = new StringBuilder();

		for (int i = 0; i < rowData.size(); i++) {
			row.append(String.format("%-" + columnWidths.get(i) + "s", rowData.get(i))
					+ " ".repeat(PADDING));
		}
		row.append("\n");

		return row;
	}
	
}
