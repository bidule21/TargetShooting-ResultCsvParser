package net.vl0w.targetshooting.csvparser.publisher;

public class PublisherFactory {

	public static Publisher createPublisher(String publisherIdentification) {
		if (publisherIdentification.equalsIgnoreCase(PublisherType.CONSOLE
				.name())) {
			return new ConsolePublisher();
		}

		throw new IllegalArgumentException(String.format(
				"Unknown publisher '%s'", publisherIdentification));
	}

}
