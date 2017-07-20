import java.util.*;

/**
 * Write a description of class Document here. Mafuzal Hoque-----100908620
 * 2015-11-15 SYSC 2004 ASSIGNMENT 3- FALL 2015
 * 
 * @version (a version number or a date)
 */

public class Document implements Comparable<Document> {
	// instance variables - replace the example below with your own
	// ID_COUNT is not a constant and therfore should not have been capitalised
	// however since it is underlined , it is kept static
	// int id and String tag instances to be used for unique ID and tag of the
	// document for likes
	private static int ID_COUNT = 0;
	private int id;
	private String tag;
	private List<User> likers;
	private User user;
	private List<User> producers = new ArrayList<>();
	private HashMap<User, Document> prod = new HashMap<>();
	

	/**
	 * Constructor for objects of class Document //tag variable provided is set
	 * to tag instance // id was set up by incrementing ID_COUNT //by
	 * incrementing ID_COUNT, an unique ID is set to Document
	 */
	public Document(String tag) {
		this.tag = tag;
		id = ID_COUNT++;
		likers = new ArrayList<User>();
		user = null;

	}

	public Document(String tag, User u) {
		this(tag);

		user = u;
		addProducers(user);
		addProd(user, this);

	}

	public void addProd(User u, Document d) {
		prod.put(u, d);
	}

	/**
	 * 
	 */
	public int getId() {
		// will return id
		return id;
	}

	/**
	 * 
	 */
	public User getUser() {
		// will return id
		return user;
	}

	/**
	 * 
	 */
	public String getTag() {
		// will return tag
		return tag;

	}

	public void addProducers(User u) {
		producers.add(user);
	}

	public void view() {
		ArrayList<User> u = user.getUsers();
		for (User uu : u) {
			if (uu instanceof Consumer) {
				ArrayList<Document> consumerDoc = ((Consumer) uu).getTopK();

				for (Document d : consumerDoc) {
					if (d.getUser() != null)
						if (prod.containsKey(d.getUser())) {
							d.getUser().setPayoff(true);

						}

				}
			}
		}
	}

	/**
	 * 
	 */
	public String toString() {
		return "<" + id + "," + tag + ">";
	}

	/**
	 * 
	 */
	public void likedBy(User u) {
		likers.add(u);
	}

	/**
	 * 
	 */
	public int popularity() {

		return likers.size();
	}

	public int compareTo(Document d) {
		int comparepopularity = ((Document) d).popularity();
		return this.popularity() - comparepopularity;
	}

}
