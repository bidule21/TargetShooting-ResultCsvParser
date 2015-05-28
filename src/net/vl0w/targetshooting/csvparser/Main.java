package net.vl0w.targetshooting.csvparser;

import java.util.Collection;

import net.vl0w.targetshooting.csvparser.publisher.Publisher;
import net.vl0w.targetshooting.csvparser.publisher.PublisherFactory;
import net.vl0w.targetshooting.csvparser.publisher.PublisherType;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.Options;

public class Main {

	private static final String OPTION_PATH = "path";
	private static final String OPTION_SHOOTER = "shooter";
	private static final String OPTION_PUBLISH = "publish";
	private static final String OPTION_HELP = "help";

	public static void main(String[] args) throws Exception {
		CommandLineParser parser = new DefaultParser();

		CommandLine helpLine = parser.parse(createHelpOption(), args, true);
		if (helpLine.hasOption(OPTION_HELP)) {
			printHelp();
		} else {
			CommandLine line = parser.parse(createAppOptions(), args);

			String csvPath = line.getOptionValue(OPTION_PATH);
			String shooterId = line.getOptionValue(OPTION_SHOOTER);
			String publisherType = line.getOptionValue(OPTION_PUBLISH,
					PublisherType.CONSOLE.name());

			CsvParser csvParser = new CsvParser(csvPath, shooterId);
			Collection<Event> events = csvParser.parse();

			Publisher publisher = PublisherFactory
					.createPublisher(publisherType);
			publisher.publish(events);
		}
	}

	private static Options createHelpOption() {
		Options options = new Options();
		options.addOption("h", OPTION_HELP, false, "Prints the help");
		return options;
	}

	private static Options createAppOptions() {
		Options options = new Options();
		options.addOption(createPathOption());
		options.addOption(createShooterOption());
		options.addOption(createPublishOption());
		return options;
	}

	private static Option createPublishOption() {
		Builder builder = Option.builder("o");
		builder.longOpt(OPTION_PUBLISH);
		builder.hasArg().argName("publish type");

		StringBuilder description = new StringBuilder();
		description.append("The name of the publisher.");
		description.append(" Allowed values: ");
		for (PublisherType type : PublisherType.values()) {
			description.append(type.name()).append(" ");
		}
		description.append(" Default value: " + PublisherType.CONSOLE.name());

		builder.desc(description.toString());
		return builder.build();
	}

	private static Option createShooterOption() {
		Builder builder = Option.builder("s");
		builder.longOpt(OPTION_SHOOTER);
		builder.required();
		builder.desc("shooter id");
		builder.hasArg().argName("id");
		return builder.build();
	}

	private static Option createPathOption() {
		Builder builder = Option.builder("p");
		builder.longOpt(OPTION_PATH);
		builder.required();
		builder.desc("path to the csv file");
		builder.hasArg().argName("path");
		return builder.build();
	}

	private static void printHelp() {
		HelpFormatter helpFormatter = new HelpFormatter();

		String usage = "java -jar <JARFILE>";
		String header = "Reads TargetShooting results from a csv and parses them to valid JSON.";
		String footer = "Further information: "
				+ "https://github.com/vl0w/TargetShooting-ResultCsvParser";

		helpFormatter
				.printHelp(usage, header, createAppOptions(), footer, true);
	}
}
