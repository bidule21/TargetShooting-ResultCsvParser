package net.vl0w.targetshooting.csvparser;

import java.util.ArrayList;
import java.util.List;

public class Event {

	private String date;
	private String description;
	private transient String categoryIdentifier;
	private List<ShooterResult> results;

	public Event(RawLine line) {
		date = line.getDate();
		description = line.getDescription();
		categoryIdentifier = new CategoryAnalyzer(line.getCategory())
				.getCategoryIdentifier();
	}

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Event) {
			Event other = (Event) obj;

			return this.date.equals(other.date)
					&& this.description.equals(other.description)
					&& this.categoryIdentifier.equals(other.categoryIdentifier);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 7 * date.hashCode() + 8 * description.hashCode() + 9
				* categoryIdentifier.hashCode();
	}

	@Override
	public String toString() {
		return date + " - " + description;
	}

	public void append(ShooterResult result) {
		if (results == null) {
			results = new ArrayList<ShooterResult>();
		}
		results.add(result);
	}
}
