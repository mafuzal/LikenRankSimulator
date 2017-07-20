import java.util.*;

/**
 * Write a description of interface RankingStrategy here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public interface RankingStrategy {
	abstract public List<Document> rank(Simulation sim);
}
