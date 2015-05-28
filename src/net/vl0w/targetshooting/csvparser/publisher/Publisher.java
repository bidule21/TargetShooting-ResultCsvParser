package net.vl0w.targetshooting.csvparser.publisher;

import java.util.Collection;

import net.vl0w.targetshooting.csvparser.Event;

public interface Publisher {
	void publish(Collection<Event> events);
}
