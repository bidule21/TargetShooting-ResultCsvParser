package net.vl0w.targetshooting.csvparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryAnalyzer {

	private String category;

	public CategoryAnalyzer(String category) {
		this.category = category;
	}

	public int getShotCount() {
		final Matcher REGEX_ELIMINATION = Pattern.compile(
				"[A-Z0-9 ]* ([0-9]{1,2})").matcher(category);
		final Matcher REGEX_FINAL = Pattern.compile(
				"[A-Za-z0-9 ]* Final ([0-9]{1,2})").matcher(category);
		final Matcher REGEX_ELIMINATION_FINAL = Pattern.compile(
				"[A-Za-z0-9 ]* ([0-9]{1,2}) Final").matcher(category);

		if (REGEX_ELIMINATION.matches()) {
			return Integer.valueOf(REGEX_ELIMINATION.group(1));
		}

		if (REGEX_FINAL.matches()) {
			return Integer.valueOf(REGEX_FINAL.group(1));
		}

		if (REGEX_ELIMINATION_FINAL.matches()) {
			return Integer.valueOf(REGEX_ELIMINATION_FINAL.group(1)) + 10;
		}

		if (category.endsWith("Match")) {
			return 60;
		}

		if (category.endsWith("Match Final")) {
			return 70;
		}

		return -1;
	}

	public String getCategoryIdentifier() {
		final Matcher IDENTIFIER_PATTERN = Pattern
				.compile("([A-Z0-9]{2,3}) .*").matcher(category);

		if (!IDENTIFIER_PATTERN.matches()) {
			throw new IllegalArgumentException("Category " + category
					+ " doesn't have an identifier! (A-Z,a-z,0-9), length 2-3");
		} else {
			return IDENTIFIER_PATTERN.group(1);
		}

	}
}
