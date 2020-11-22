package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.State;
import edu.upenn.cit594.data.Tweet;

public interface Processor {
	
	double calculateDistance(double x1, double y1, double x2, double y2);
	
	Map<String, List<String>> findFluTweetStates();
}
