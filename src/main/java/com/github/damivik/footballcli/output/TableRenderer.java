package com.github.damivik.footballcli.output;

import java.util.ArrayList;
import java.util.List;

public class TableRenderer {
	private int columnCount;
	private String title;
	private List<String> columnHeaders;
	private List<List<String>> rows = new ArrayList<>();

	public TableRenderer setColumnCount(int columnCount) {
		this.columnCount = columnCount;
		return this;
	}

	public TableRenderer setTitle(String title) {
		this.title = title;
		return this;
	}

	public TableRenderer setColumnHeaders(List<String> columnHeaders) throws TableRendererException {
		if (columnHeaders.size() != columnCount) {
			throw new TableRendererException("Number of column headers does not match the columnCount");
		}
		this.columnHeaders = columnHeaders;
		return this;
	}

	public TableRenderer setRows(List<List<String>> rows) throws TableRendererException {
		for (List<String> row : rows) {
			if (row.size() != columnCount) {
				throw new TableRendererException("Number of row elements does not match the columnCount");
			} 
		}
		this.rows = rows;
		return this;
	}

	public String render() {
		List<Integer> columnWidths;
		TableComputation computation = new TableComputation();

		columnWidths = columnHeaders == null ? computation.computeColumnWidths(columnCount, rows)
				: computation.computeColumnWidths(columnCount, columnHeaders, rows);

		TableComponentRenderer renderer = new TableComponentRenderer(columnWidths);

		StringBuilder table = new StringBuilder();

		StringBuilder border = renderer.renderBorder();
		if (title != null) {
			table.append(border).append(new StringBuilder(title + "\n"));
		}
		if (columnHeaders != null) {
			table.append(border).append(renderer.renderHeader(columnHeaders));
		}

		table.append(border);

		for (List<String> row : rows) {
			table.append(renderer.renderRow(row));
		}

		table.append(border);

		return table.toString();
	}
}
