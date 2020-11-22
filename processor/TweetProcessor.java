package edu.upenn.cit594.processor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.State;
import edu.upenn.cit594.data.Tweet;
import edu.upenn.cit594.datamanagement.*;

public class TweetProcessor implements Processor{
	
	protected Reader tweetReader;
	protected Reader stateReader;
	protected List<Tweet> tweetData;
	protected List<State> stateData;
	
	public TweetProcessor(Reader tweetReader, Reader stateReader) {
		this.tweetReader = tweetReader;
		this.stateReader = stateReader;
		tweetData = tweetReader.getData();
		stateData = stateReader.getData();
	}
	
	public boolean isfluTweet(Tweet tweet) {
		String lTweet = tweet.text.toLowerCase();
		Pattern fluTweetBluePrint = Pattern.compile("\\bflu\\b");
		Matcher m = fluTweetBluePrint.matcher(lTweet);
		if(m.find()) {
			return true; 
		}
		else {
			return false;
		}
	}

	public List<Tweet> gatherFluTweets() {
		ArrayList<Tweet> fluTweetList = new ArrayList<Tweet>();
		for(Tweet tweet : tweetData) {
			if(isfluTweet(tweet)) {
				fluTweetList.add(tweet);
			}
		}
		return fluTweetList;
	}

	public double calculateDistance(double x1, double y1, double x2, double y2) {
		double distance = Math.sqrt((Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2)));
		return distance;
	}

	public Map<String, List<String>> findFluTweetStates() {
		Map<String, List<String>> stateTweetMap = new HashMap<String, List<String>>();
		for(Tweet t: gatherFluTweets()) {
			System.out.println("                                      " + t.text);
			double minDistance = calculateDistance(t.latitude, t.longitude, stateData.get(0).latitude, stateData.get(0).longitude);
			System.out.println(minDistance + " mindistance current");
			String currentMinDistState = stateData.get(0).name;
			for(State s: stateData) {
				//System.out.println(s.name + " state");
				
				double stateDistance = calculateDistance(t.latitude, t.longitude, s.latitude, s.longitude);
				
				//System.out.println(stateDistance + " stateDistance");
				if(stateDistance < minDistance) {
					minDistance = stateDistance;
					currentMinDistState = s.name;
				}
			}
			stateTweetMap.putIfAbsent(currentMinDistState, new ArrayList<>());
			stateTweetMap.get(currentMinDistState).add(t.text);
		}
		return stateTweetMap;
	}
}
