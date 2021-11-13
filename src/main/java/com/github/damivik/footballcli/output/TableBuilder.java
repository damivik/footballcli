package com.github.damivik.footballcli.output;

import java.util.List;

public class TableBuilder {

	private final int PADDING = 3;

	public StringBuilder buildHorizontalBorder(List<Integer> columnsWidth) {

		StringBuilder border = new StringBuilder();

		for (Integer columnWidth : columnsWidth) {
			border.append("-".repeat(columnWidth + (PADDING)));
		}

		border.append("\n");

		return border;
	}

	public StringBuilder buildTitle(String title) {
		return new StringBuilder(title + "\n");
	}

	public StringBuilder buildHeader(List<Integer> columnsWidth, List<String> columnHeaders) {

		StringBuilder header = new StringBuilder();

		for (int i = 0; i < columnsWidth.size(); i++) {
			header.append(String.format("%-" + columnsWidth.get(i) + "s", columnHeaders.get(i)) + " ".repeat(PADDING));
		}

		header.append("\n");

		return header;
	}

	public StringBuilder buildRow(List<Integer> columnsWidth, List<String> rowData) {

		StringBuilder row = new StringBuilder();

		for (int i = 0; i < rowData.size(); i++) {
			row.append(String.format("%-" + columnsWidth.get(i) + "s", rowData.get(i)) + " ".repeat(PADDING));
		}
		row.append("\n");

		return row;
	}
}