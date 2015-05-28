package net.vl0w.targetshooting.csvparser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategoryAnalyzerTest {
	@Test
	public void testShotCount_OnlyElimination() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 K 20");
		assertEquals(20, analyzer.getShotCount());

		analyzer = new CategoryAnalyzer("A30 S 30");
		assertEquals(30, analyzer.getShotCount());

		analyzer = new CategoryAnalyzer("A10 1");
		assertEquals(1, analyzer.getShotCount());
	}

	@Test
	public void testShotCount_FinalResult() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 S Final 10");
		assertEquals(10, analyzer.getShotCount());

		analyzer = new CategoryAnalyzer("A10 Final 1");
		assertEquals(1, analyzer.getShotCount());
	}

	@Test
	public void testShotCount_CombinedEliminationAndFinal() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 K 30 Final");
		assertEquals(40, analyzer.getShotCount());

		analyzer = new CategoryAnalyzer("A10 40 Final");
		assertEquals(50, analyzer.getShotCount());
	}

	@Test
	public void testShotCount_Match() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 Match");
		assertEquals(60, analyzer.getShotCount());
	}

	@Test
	public void testShotCount_MatchFinal() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 Match Final");
		assertEquals(70, analyzer.getShotCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCategoryIdentifier_InvalidCategory() {
		new CategoryAnalyzer("BLABLER").getCategoryIdentifier();
	}

	@Test
	public void testCategoryIdentifier_DifferentCategories() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer("A30 K 30");
		assertEquals("A30", analyzer.getCategoryIdentifier());

		analyzer = new CategoryAnalyzer("A10 40");
		assertEquals("A10", analyzer.getCategoryIdentifier());

		analyzer = new CategoryAnalyzer("LG 60");
		assertEquals("LG", analyzer.getCategoryIdentifier());
	}
}
