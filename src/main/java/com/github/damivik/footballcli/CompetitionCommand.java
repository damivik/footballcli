package com.github.damivik.footballcli;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;

@Command(
		name = "competitions",
		mixinStandardHelpOptions = true,
		description = "List available competitions")
public class CompetitionCommand implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		CompetitionsResponse response = new APIRequest().getCompetitions();
		
		List<String> columnHeaders = Arrays.asList("Name", "Code", "Area");
		Table table = new Table();
		table.setColumnHeaders(columnHeaders);
		
		for(Competition competition: response.getCompetitions()) {
			List<String> row = Arrays.asList(competition.getName(), competition.getCode(), competition.getArea().getName());
			table.addRow(row);
		}
		
		System.out.println(table.build());
		
		return 0;
	}

}
