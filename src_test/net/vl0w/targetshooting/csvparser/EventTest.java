package net.vl0w.targetshooting.csvparser;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EventTest {
	@Test
	public void testEquality() {
		Event e1 = new Event(
				RawLine.fromLine("2015-05-28;DESC;10;LG 10;Training"));
		Event e2 = new Event(
				RawLine.fromLine("2015-05-28;DESC;10;A10 10;Training"));
		assertNotEquals(e1, e2);
		assertNotEquals(e1.hashCode(), e2.hashCode());
	}
}
