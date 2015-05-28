package net.vl0w.targetshooting.csvparser;

import java.util.Collection;

import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			throw new IllegalArgumentException(
					"You must provide a file path in the first argument and a shooter id in the second argument");
		}

		CsvParser parser = new CsvParser(args[0], args[1]);
		Collection<Event> events = parser.parse();

		Gson gson = new Gson();
		System.out.println(gson.toJson(events));
	}
}
