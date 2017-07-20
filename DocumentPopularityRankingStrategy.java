import java.util.*;

/**
 * Write a description of class DocumentPopularityRankingStrategy here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class DocumentPopularityRankingStrategy implements RankingStrategy {
	// instance variables - replace the example below with your own

	/**
	 * Constructor for objects of class DocumentPopularityRankingStrategy
	 */
	public DocumentPopularityRankingStrategy() {

	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public List<Document> rank(Simulation sim) {
		ArrayList<Document> list = (ArrayList<Document>) sim.getDocuments();
		// System.out.println("BEFORE "+list);
		ArrayList<Document> top_K = new ArrayList<Document>();
		Collections.sort(list);

		// System.out.println("AFTER "+list);
		int counter = Simulation.K;
		int s = list.size() - 1;
		while (counter != 0) {

			top_K.add(list.get(s));
			s--;
			counter--;
		}
		// System.out.println("top "+top_K);

		return top_K;

	}
}
