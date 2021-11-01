package com.github.damivik.footballcli;

import java.util.ArrayList;
import java.util.List;

public class TableComputation {
	
	public List<Integer> columnsWidth(List<String> columnHeaders, List<List<String>> data) {

		List<Integer> columnsWidth = new ArrayList<>();
		
		int columnCount = columnHeaders.size();

		for (int column = 0; column < columnCount; column++) {

			int maxRowLength = columnHeaders.get(column).length();

			int rowCount = data.size();

			for (int row = 0; row < rowCount; row++) {
				if (data.get(row).get(column).length() > maxRowLength) {
					maxRowLength = data.get(row).get(column).length();
				}
			}
			columnsWidth.add(maxRowLength);
		}
		return columnsWidth;
	}
	
	public List<Integer> columnsWidth(List<List<String>> data) {

		List<Integer> columnsWidth = new ArrayList<>();
		
		int columnCount = data.get(0).size();

		for (int column = 0; column < columnCount; column++) {

			int maxRowLength = 0;

			int rowCount = data.size();

			for (int row = 0; row < rowCount; row++) {
				if (data.get(row).get(column).length() > maxRowLength) {
					maxRowLength = data.get(row).get(column).length();
				}
			}
			columnsWidth.add(maxRowLength);
		}
		return columnsWidth;
	}
}
