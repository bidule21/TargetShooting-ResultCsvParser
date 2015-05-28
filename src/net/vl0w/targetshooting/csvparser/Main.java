package net.vl0w.targetshooting.csvparser;

import java.util.Collection;

import net.vl0w.targetshooting.csvparser.publisher.Publisher;
import net.vl0w.targetshooting.csvparser.publisher.PublisherFactory;
import net.vl0w.targetshooting.csvparser.publisher.PublisherType;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {

	private static final String OPTION_PATH = "path";
	private static final String OPTION_SHOOTER = "shooter";
	private static final String OPTION_PUBLISH = "publish";

	public static void main(String[] args) throws Exception {
		Options options = createOptions();

		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);
		String csvPath = line.getOptionValue(OPTION_PATH);
		String shooterId = line.getOptionValue(OPTION_SHOOTER);
		String publisherType = line.getOptionValue(OPTION_PUBLISH,
				PublisherType.CONSOLE.name());

		CsvParser csvParser = new CsvParser(csvPath, shooterId);
		Collection<Event> events = csvParser.parse();

		Publisher publisher = PublisherFactory.createPublisher(publisherType);
		publisher.publish(events);
	}

	private static Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder(OPTION_PATH) //
				.required() //
				.desc("Path to the CSV file") //
				.hasArg().argName("path") //
				.build());

		options.addOption(Option.builder(OPTION_SHOOTER) //
				.required() //
				.desc("ID of the shooter") //
				.hasArg().argName("shooter ID") //
				.build());

		options.addOption(Option.builder(OPTION_PUBLISH)
				//
				.desc(String
						.format("The publisher name. Allowed values: %s. Default value: %s",
								PublisherType.values(), PublisherType.CONSOLE))//
				.hasArg().argName("publisher") //
				.build());

		return options;
	}
}
