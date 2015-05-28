package net.vl0w.targetshooting.csvparser.publisher;

import java.util.Collection;

import net.vl0w.targetshooting.csvparser.Event;

import com.google.gson.Gson;

public class ConsolePublisher implements Publisher {

	@Override
	public void publish(Collection<Event> events) {
		Gson gson = new Gson();
		System.out.println(gson.toJson(events));
	}

}
