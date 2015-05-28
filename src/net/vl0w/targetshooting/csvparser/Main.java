package net.vl0w.targetshooting.csvparser;

import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) throws Exception {
		Options options = new Options();
		options.addOption(Option.builder("path") //
				.required() //
				.desc("Path to the CSV file") //
				.hasArg().argName("path") //
				.build());

		options.addOption(Option.builder("shooter") //
				.required() //
				.desc("ID of the shooter") //
				.hasArg().argName("shooter") //
				.build());

		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);
		String csvPath = line.getOptionValue("path");
		String shooterId = line.getOptionValue("shooter");

		CsvParser csvParser = new CsvParser(csvPath, shooterId);
		Collection<Event> events = csvParser.parse();

		Gson gson = new Gson();
		System.out.println(gson.toJson(events));
	}
}
