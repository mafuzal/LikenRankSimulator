import java.util.ArrayList;

/**
 * Write a description of class Document here. Mafuzal Hoque-----100908620
 * 2015-11-15 SYSC 2004 ASSIGNMENT 3- FALL 2015 Consumer Class:A consumer is a
 * kind of user that "likes" documents that match its taste.
 */
public class Consumer extends User {

	private RankingStrategy strategy;
	private ArrayList<Document> list;

	public Consumer(Simulation sim, String taste, RankingStrategy strategy) {
		// constructor inherits the user constructor and passes on these sim and
		// taste
		super(sim, taste);
		this.strategy = strategy;
		list = new ArrayList<>();

	}

	public void act() {
		// the list array saves all the documents in Simulation class to itself

		// ArrayList<Document> l = getTopK();

		for (Document d : getTopK()) {
			if (this.getTaste().equals(d.getTag())) {
				like(d);
				setPayoff(true);
			} else {
				setPayoff(false);
			}

		}

	}

	public ArrayList<Document> getTopK() {
		list = (ArrayList<Document>) strategy.rank(sim);
		return list;
	}

	@Override

	public String toString() {
		// the String type adds consumer with toString() from super class user
		// ArrayList<Document> list = (ArrayList<Document>) strategy.rank(sim);
		return "consumer : " + super.toString() + " with PayOff:" + getpayOff()+"\n";

	}
}
