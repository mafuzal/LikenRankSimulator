import java.util.*;
import java.util.Collections;

/**
 * Write a description of class RandomRankingStrategy here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomRankingStrategy implements RankingStrategy {
	// instance variables - replace the example below with your own

	/**
	 * Constructor for objects of class RandomRankingStrategy
	 */
	public RandomRankingStrategy() {
		// initialise instance variables

	}

	/**
	 * Simply shuffle the list and return the top K Specified by:rank in
	 * interface RankingStrategy Returns: the top k documents according to the
	 * ranking provided by the concrete strategy
	 */
	public List<Document> rank(Simulation sim) {
		ArrayList<Document> list = (ArrayList<Document>) sim.getDocuments();
		ArrayList<Document> top_K = new ArrayList<Document>();
		Collections.shuffle(list);
		// System.out.println(" PR "+list);
		for (int i = 0; i < Simulation.K; i++) {
			top_K.add(list.get(i));
		}
		// System.out.println("LOOOOOP "+top_K);

		return top_K;

	}
}
