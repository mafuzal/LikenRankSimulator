
/**
 * Write a description of class Document here.
 * Mafuzal Hoque-----100908620
 * 2015-11-15
 * SYSC 2004 ASSIGNMENT 3- FALL 2015
 * User Class : A user has an id, a taste, and keeps track of the documents it "likes". 
 * Furthermore, a user is asked by the simulation to "act",  which means for now to search
 * for documents and to evaluate them: if the document's tag matches the user's taste the user will "like" it, 
 * i.e., adds it to its list of liked documents. New in assign3: the search is performed using a given strategy. 
 */

import java.util.*;

public abstract class User {
	private static int ID_COUNT = 0;
	private int id;
	protected String taste;
	protected Simulation sim;

	private HashSet<Document> likes;
	protected int payoff;

	/**
	 * create a peer with the supplied simulation, taste and strategy.
	 */
	public User(Simulation sim, String taste) {
		// create a peer with the supplied simulation and taste.
		// Set a unique identifier for this document by using and incrementing
		// the ID_COUNT.
		// An empty list of "liked" documents are created here
		this.sim = sim;
		this.taste = taste;
		id = ID_COUNT++;
		likes = new HashSet<Document>();
		payoff = 0;
	}

	public int getId() {
		// return this user's id
		return id;

	}

	protected int getpayOff() {
		return payoff;
	}

	public String getTaste() {
		// return this user's taste
		return taste;

	}

	/**
	 * to "like" a document means to add it to the list of liked documents
	 * modified in assign3 to also add this user as a "liker" of the document
	 */
	public void like(Document d) {
		d.likedBy(this);
		likes.add(d);

	}

	/**
	 * true if this user likes the supplied document, false otherwise
	 */

	public boolean likes(Document d) {
		// return true if this user likes the supplied document
		for (Document dd : likes) {

			if (dd.getTag() == d.getTag()) {

				return true;

			}
		}
		return false;
	}

	/**
	 * The user's action during one simulation step, consists of: 1.ranking the
	 * documents in the simulation using its strategy 2.evaluation the
	 * documents: for now, "like" the document if its tag matches the taste
	 */
	abstract public void act();

	// this method saves all documents from simulation
	// so other classes do not have to call simulation
	public ArrayList<Document> docViewer() {
		ArrayList<Document> list = (ArrayList<Document>) sim.getDocuments();
		return list;
	}

	public HashSet<Document> getlikes() {
		return likes;
	}

	public String toString() {
		String s = "";

		for (Document d : likes) {
			s += d + ",";

		}
		if (s.isEmpty()) {
			s += "<0 LIKES>";
		} else {
			s = s.substring(0, s.length() - 1);
		}

		return "<" + this.getId() + ">  is into : <" + this.getTaste() + "> and currently likes : " + s;
	}

	public void incrementPayoff() {
		payoff++;
	}

	public void decrementPayoff() {
		payoff--;
	}

	public void setPayoff(boolean arbiter) {
		if (arbiter) {
			payoff++;
		} else {
			payoff--;
		}
	}

	public ArrayList<User> getUsers() {
		return (ArrayList<User>) sim.getUsers();

	}
}
