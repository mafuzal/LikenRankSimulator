
/**
 * Write a description of class Document here.
 * Mafuzal Hoque-----100908620
 * 10/06/2015
 * SYSC 2004 ASSIGNMENT 1- FALL 2015
 * @version (a version number or a date)
 */
////----------------------SIMULATION CLASS-------------------------------------/////
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.Random;

import javax.swing.JTextArea;

public class Simulation {
	// instance variables - replace the example below with your own

	public static final String[] TAGS = { "rock", "jazz", "rap" };
	public static final int K = 2;
	public static final int steps = 15;
	private List<User> users;
	private List<User> chosenOnes;
	private List<Document> documents;
	private List<SimulationListener> simulationListeners;
	private Random r;
	private int conPayOffs;
	private int proPayOffs;

	/**
	 * Constructor for objects of class Simulation
	 */
	public Simulation() {
		this(0, 0, null);

	}

	public Simulation(int nbUsers, int nbDocs, String strategy) {
		users = new ArrayList<User>();
		chosenOnes = new ArrayList<User>();
		documents = new ArrayList<Document>();
		simulationListeners = new ArrayList<>();
		r = new Random();
		conPayOffs = 0;
		proPayOffs = 0;

		for (int i = 0; i < nbUsers; i++) {
			User u = null;
			if (r.nextBoolean()) {

				if (strategy.equals("docpop")) {
					u = new Consumer(this, TAGS[r.nextInt(TAGS.length)], new DocumentPopularityRankingStrategy());
				} else if (strategy.equals("random")) {
					u = new Consumer(this, TAGS[r.nextInt(TAGS.length)], new RandomRankingStrategy());

				}
			} else {

				u = new Producer(this, TAGS[r.nextInt(TAGS.length)]);

			}

			addUser(u);
		}
		for (int i = 0; i < nbDocs; i++) {
			Document d = new Document(TAGS[r.nextInt(TAGS.length)]);
			addDocument(d);
		}

	}

	public void addSimulationListeners(SimulationListener listener) {
		simulationListeners.add(listener);
	}

	public void addUser(User u) {
		// add the supplied user to the list of users
		users.add(u);
	}

	public void addChosenUser(User u) {
		// add the supplied user to the list of users
		chosenOnes.add(u);
	}

	

	public void addDocument(Document d) {
		// add the supplied document to the list of documents
		documents.add(d);
	}

	public void run() {
		step();
		// System.out.println("The Consumer Average Payoff = " + (int)conAvg());
		// System.out.println("The Producer Average Payoff = " + (int)proAvg());
		for (SimulationListener sL : simulationListeners) {
			sL.output(new SimulationEvent(getChosenUsers()));
		}
		avgCal();

	}
	// Q1--> Creating step method

	public void step() {
		for (int i = 0; i < steps; i++) {
			User u = users.get(r.nextInt(users.size()));
			addChosenUser(u);// to calculate average
			u.act();

			// System.out.println(u);

		}

	}
	  public void avgCal(){
	        String avg = "The Consumer Average Payoff = " + (int)conAvg()+"\n";
	        avg+="The Producer Average Payoff = " + (int)proAvg()+"\n";
	        new SimulationEvent().output(avg);
	    }
	// this method will return all documents created ever
	public List<Document> getDocuments() {
		return documents;
	}

	// this method will return all users ever
	public List<User> getUsers() {
		return users;
	}

	public List<User> getChosenUsers() {
		return chosenOnes;
	}

	public int conAvg() {
		int tracker = 1;
		for (User u : chosenOnes) {
			if (u instanceof Consumer) {
				conPayOffs += u.getpayOff();
				tracker++;
			}
		}
		return conPayOffs / tracker;

	}

	public int proAvg() {
		int tracker = 1;
		for (User u : chosenOnes) {
			if (u instanceof Producer) {
				proPayOffs += u.getpayOff();
				tracker++;
			}
		}
		return proPayOffs / tracker;

	}

	public static void main(String[] args) {

		Simulation sim = new Simulation(4, 9, "random");
		SimulationHandler sH = new SimulationHandler();
		sim.addSimulationListeners(sH);
		sim.run();

	}

}

