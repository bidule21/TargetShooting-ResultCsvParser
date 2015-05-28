package net.vl0w.targetshooting.csvparser.publisher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PublisherFactoryTest {
	@Test(expected = IllegalArgumentException.class)
	public void unknownPublisherType() {
		PublisherFactory.createPublisher("unknown");
	}

	@Test
	public void consolePublisher() {
		assertEquals(ConsolePublisher.class,
				PublisherFactory.createPublisher("console").getClass());
	}
}
