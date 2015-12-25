package knapsack;

import org.junit.BeforeClass;
import org.junit.Test;

public class KnapsackAlgorithmTest {

	private static final int KNAPSACK_WEIGHT_LIMIT = 850;// grams
	private static Item[] items;
	private static Item[] exampleItems;

	@BeforeClass
	public static void initializeItems() {
		items = new Item[] { new Item("map", 90, 150),
				new Item("compass", 130, 35), new Item("water", 1530, 200),
				new Item("sandwich", 500, 160), new Item("glucose", 150, 60),
				new Item("tin", 680, 45), new Item("banana", 270, 60),
				new Item("apple", 390, 40), new Item("cheese", 230, 30),
				new Item("beer", 520, 10), new Item("suntan cream", 110, 70),
				new Item("camera", 320, 30), new Item("T-shirt", 240, 15),
				new Item("trousers", 480, 10), new Item("umbrella", 730, 40),
				new Item("waterproof trousers", 420, 70),
				new Item("waterproof overclothes", 430, 75),
				new Item("note-case", 220, 80), new Item("sunglasses", 70, 20),
				new Item("towel", 180, 12), new Item("socks", 40, 50),
				new Item("book", 300, 10), new Item("notebook", 900, 1),
				new Item("tent", 2000, 150),

		};

		exampleItems = new Item[] { new Item("x1", 360, 7),
				new Item("x1", 83, 0), new Item("x1", 59, 30),
				new Item("x1", 130, 22), new Item("x1", 431, 80),
				new Item("x1", 67, 94), new Item("x1", 230, 11),
				new Item("x1", 52, 81), new Item("x1", 93, 70),
				new Item("x1", 125, 64), new Item("x1", 670, 59),
				new Item("x1", 892, 18), new Item("x1", 600, 0),
				new Item("x1", 38, 36), new Item("x1", 48, 3),
				new Item("x1", 147, 8), new Item("x1", 78, 15),
				new Item("x1", 256, 42), new Item("x1", 63, 9),
				new Item("x1", 17, 0), new Item("x1", 120, 42),
				new Item("x1", 164, 47), new Item("x1", 432, 52),
				new Item("x1", 35, 32), new Item("x1", 92, 26),
				new Item("x1", 110, 48), new Item("x1", 22, 55),
				new Item("x1", 42, 6), new Item("x1", 50, 29),
				new Item("x1", 323, 84), new Item("x1", 514, 2),
				new Item("x1", 28, 4), new Item("x1", 87, 18),
				new Item("x1", 73, 56), new Item("x1", 78, 7),
				new Item("x1", 15, 29), new Item("x1", 26, 93),
				new Item("x1", 78, 44), new Item("x1", 210, 71),
				new Item("x1", 36, 3), new Item("x1", 85, 86),
				new Item("x1", 189, 66), new Item("x1", 274, 31),
				new Item("x1", 43, 65), new Item("x1", 33, 0),
				new Item("x1", 10, 79), new Item("x1", 19, 20),
				new Item("x1", 389, 65), new Item("x1", 276, 52),
				new Item("x1", 312, 13) };
	}

	@Test
	public void testKnapsackProblem() throws Exception {
		KnapsackProblem knapsackProblem = new KnapsackProblem(
				KNAPSACK_WEIGHT_LIMIT, exampleItems);
		String bestChromosome = knapsackProblem.solve();
		System.out.println(bestChromosome + " -- "
				+ knapsackProblem.getFitness(bestChromosome));
		// The maximum value is 7534
		// (11011010001110111111011010011111101000110110100111)
	}
}
