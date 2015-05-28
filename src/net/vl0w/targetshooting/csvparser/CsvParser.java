package net.vl0w.targetshooting.csvparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvParser {
	private String filePath;
	private String shooterId;

	public CsvParser(String filePath, String shooterId) {
		this.filePath = filePath;
		this.shooterId = shooterId;
	}

	public Collection<Event> parse() throws IOException {
		List<RawLine> rawLines = readRawLines(filePath);
		Set<Event> events = extractEvents(rawLines);

		// Append results to events
		for (Event event : events) {
			List<Result> resultsOfEvent = extractResults(event, rawLines);
			Result bundledResult = new ResultBundler(resultsOfEvent).bundle();
			ShooterResult result = new ShooterResult(shooterId, bundledResult);
			event.append(result);
		}

		return events;
	}

	private Set<Event> extractEvents(List<RawLine> rawLines) {
		Set<Event> events = new HashSet<Event>();
		for (RawLine line : rawLines) {
			events.add(new Event(line));
		}
		return events;
	}

	private List<Result> extractResults(Event event,
			Collection<RawLine> rawLines) {
		List<Result> results = new ArrayList<Result>();
		for (RawLine line : rawLines) {
			if (line.belongsToEvent(event)) {
				results.add(new Result(line));
			}
		}
		return results;
	}

	private List<RawLine> readRawLines(String filePath) throws IOException {
		List<RawLine> rawLines = new ArrayList<RawLine>();

		File rawFile = new File(filePath);
		if (!rawFile.exists()) {
			throw new FileNotFoundException(filePath);
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(rawFile))) {
			String line;
			reader.readLine(); // Skip header
			while ((line = reader.readLine()) != null) {
				rawLines.add(RawLine.fromLine(line));
			}
		}

		return rawLines;
	}
}
