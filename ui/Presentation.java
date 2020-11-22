package edu.upenn.cit594.ui;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.processor.*;

public class Presentation {
	
	protected Processor processor;
	
	public Presentation(Processor processor) {
		this.processor = processor;
	}
	
	/**
	 * utilizes the map<String, List<String> from the processor containing the states and their of flu tweets (if not zero), and prints the
	 * state and the number of tweets each one has.
	 */
	public void displayStateFluNum() {
		TreeMap<String, Integer> sortedKeys = new TreeMap<>();
		for(String key : processor.findFluTweetStates().keySet()) {
			int numberOfTweets = processor.findFluTweetStates().get(key).size();
			sortedKeys.put(key, numberOfTweets);
		}
		for(String key : sortedKeys.keySet()) {
			System.out.println(key + ": " + sortedKeys.get(key));
		}
	}
}
