package knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Problem: Given a set of items, each with a weight and a value, determine the
 * number of each item to include in a collection so that the total weight is
 * less than or equal to a given limit and the total value is as large as
 * possible. This class solves the problem using genetic algorithm.
 * 
 * @author petar
 *
 */
public class KnapsackProblem {

	private static final char ITEM_OUT_OF_KNAPSACK = '0';
	private static final char ITEM_IN_KNAPSACK = '1';

	/**
	 * During the algorithm we need to pick a random chromosome from the
	 * population. But for better results, we pick <b>SELECTION_SIZE</b>
	 * elements, and get the one with the highest fitness function.
	 */
	private static final int SELECTION_SIZE = 10;

	private static final int POPULATION_SIZE = 100;

	/**
	 * The TERMINATION condition is when there are GENERATIONS_LIMIT new
	 * generations created.
	 */
	private static final int GENERATIONS_LIMIT = 4000;

	/**
	 * The maximum weight of the knapsack
	 */
	private int knapsackWeightLimit;

	/**
	 * The given collection of items.
	 */
	private Item[] items;

	/**
	 * The possible solutions generated during the algorithm(a.k.a. population).
	 * Every string in the population is called chromosome. Every chromosome is
	 * made of '0' and '1'. They determine the items that will be in the
	 * knapsack. E.g. lets say we have 3 items.(e.g. - pc, map and pen). The
	 * chromosome "101" means that the first and the third item will be in the
	 * bag(in our case - pc and pen).
	 */
	private List<String> population = new ArrayList<String>();

	private Random random = new Random();

	public KnapsackProblem(int knapsackWeightLimit, Item[] items) {
		this.knapsackWeightLimit = knapsackWeightLimit;
		this.items = items;
	}

	/**
	 * Solve the knapsack problem using genetic algorithm.
	 * 
	 * @return - a String of '0' and '1', represents the solution in bits.
	 */
	public String solve() {
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population.add(randomStringOfBits());
		}
		int iterations = 0;
		while (iterations++ <= GENERATIONS_LIMIT) {
			String newGeneration = mutation(crossover(
					pickRandomChromosome(SELECTION_SIZE),
					pickRandomChromosome(SELECTION_SIZE)));
			population.add(newGeneration);
			deleteChromosomeWithLeastFitness(SELECTION_SIZE);
		}
		return Collections.max(population, fitnessComaprator);

	}

	/**
	 * Calculate the fitness function of a chromosome. In our case it is the
	 * value of all items in the knapsack.
	 * 
	 * @param chromosome
	 * @return
	 */
	public int getFitness(String chromosome) {
		int overallValue = 0;
		int overallWeight = 0;
		for (int i = 0; i < chromosome.toCharArray().length; i++) {
			if (chromosome.charAt(i) == ITEM_IN_KNAPSACK) {
				overallValue += items[i].getValue();
				overallWeight += items[i].getWeight();
			}
		}
		return overallWeight > knapsackWeightLimit ? 0 : overallValue;
	}

	/**
	 * Crossover between two chromosomes. The first half of the returned
	 * crossover chromosome is made of <b>chromosome1</b> and the second is made
	 * of <b>chromosome2</b>.
	 * 
	 * @param chromosome1
	 * @param chromosome2
	 * @return
	 */
	private String crossover(String chromosome1, String chromosome2) {
		String firstHalf = chromosome1.substring(0, chromosome1.length() / 2);
		String secondHalf = chromosome2.substring(chromosome1.length() / 2,
				chromosome2.length());
		return firstHalf + secondHalf;
	}

	/**
	 * Mutation of chromosome. Picks a random bit and change it.
	 * 
	 * @param chromosome
	 * @return
	 */
	private String mutation(String chromosome) {
		int randomIndex = random.nextInt(chromosome.length());
		StringBuilder mutation = new StringBuilder(chromosome);
		char oppositeBit;
		if (mutation.charAt(randomIndex) == ITEM_IN_KNAPSACK) {
			oppositeBit = ITEM_OUT_OF_KNAPSACK;
		} else {
			oppositeBit = ITEM_IN_KNAPSACK;
		}
		mutation.setCharAt(randomIndex, oppositeBit);
		return mutation.toString();
	}

	/**
	 * Delete one chromosome from the population. The function picks
	 * <b>SELECTION_SIZE</b> random elements and deletes the one with the lowest
	 * fitness function.
	 */
	private void deleteChromosomeWithLeastFitness(int n) {
		List<String> selectionOfChromosomes = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			int randomChromosomeIndex = random.nextInt(population.size());
			selectionOfChromosomes.add(population.get(randomChromosomeIndex));
		}
		population.remove(Collections.min(selectionOfChromosomes,
				fitnessComaprator));
	}

	/**
	 * Comparator, comparing fitness functions.
	 */
	private Comparator<String> fitnessComaprator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			return Integer.compare(getFitness(o1), getFitness(o2));
		}
	};

	/**
	 * Select <b>n</b> elements from the population and picks the best among
	 * them according to the fitness function
	 * 
	 * @return
	 */
	private String pickRandomChromosome(int n) {
		List<String> selectionOfChromosomes = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			int randomChromosomeIndex = random.nextInt(population.size());
			selectionOfChromosomes.add(population.get(randomChromosomeIndex));
		}
		return Collections.max(selectionOfChromosomes, fitnessComaprator);
	}

	/**
	 * Get a random chromosome. It's length is the number of all items.
	 * 
	 * @return
	 */
	private String randomStringOfBits() {
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < items.length; i++) {
			if (random.nextBoolean()) {
				randomString.append(ITEM_IN_KNAPSACK);
			} else {
				randomString.append(ITEM_OUT_OF_KNAPSACK);
			}

		}
		return randomString.toString();
	}
}
