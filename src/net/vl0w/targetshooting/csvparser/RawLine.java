package net.vl0w.targetshooting.csvparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RawLine {
	private String date;
	private String description;
	private float result;
	private String category;

	private RawLine(String date, String description, float result,
			String category) {
		this.date = date;
		this.description = description;
		this.result = result;
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public float getResult() {
		return result;
	}

	public String getCategory() {
		return category;
	}

	public boolean belongsToEvent(Event otherEvent) {
		Event lineAsEvent = new Event(this);
		return lineAsEvent.equals(otherEvent);
	}

	public static RawLine fromLine(String line) {
		String regex = "(\\d{4}-\\d{2}-\\d{2});([a-zA-Z0-9üäö .-]*);([0-9.]{1,5});([a-zA-Z0-9 ]*);(\\w*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
					"Input doesn't match with pattern " + regex);
		}

		return new RawLine(matcher.group(1), matcher.group(2),
				Float.valueOf(matcher.group(3)), matcher.group(4));
	}
}
