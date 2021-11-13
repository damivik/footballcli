package com.github.damivik.footballcli.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableComputation2 {
	public List<Integer> computeColumnWidths(int columnCount, List<String> columnHeaders, List<List<String>> rows) {
		List<Integer> columnWidths = new ArrayList<>();

		for (String header : columnHeaders) {
			columnWidths.add(header.length());
		}

		for (List<String> row : rows) {
			for (int i = 0; i < columnCount; i++) {
				if (row.get(i).length() > columnWidths.get(i)) {
					columnWidths.set(i, row.get(i).length());
				}
			}
		}

		return columnWidths;
	}
	
	public List<Integer> computeColumnWidths(int columnCount, List<List<String>> rows) {
		List<Integer> columnWidths = Arrays.asList(new Integer[columnCount]);

		for (List<String> row : rows) {
			for (int i = 0; i < columnCount; i++) {
				if (columnWidths.get(i) == null || row.get(i).length() > columnWidths.get(i)) {
					columnWidths.set(i, row.get(i).length());
				}
			}
		}

		return columnWidths;
	}
}
