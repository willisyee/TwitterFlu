package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Tweet;

public class JsonReader extends formatString implements Reader<Tweet>{

	protected String filename;
	
	public JsonReader(String name) {
		filename = name;
	}
	 
	/**
	 * takes a filename with a json extension and produces a List of Tweets to use for calculation and decision making 
	 */
	public List<Tweet> getData() {	
		ArrayList<Tweet> TweetList = new ArrayList<Tweet>();
		try {
			// create a parser
			JSONParser parser = new JSONParser();
			// open the file and get the array of JSON objects
			FileReader fileR = new FileReader(filename);
			if(!fileR.ready()) {
				System.out.println("error: json file cannot be read");
				System.exit(0);
			}
			JSONArray fluTweets = (JSONArray)parser.parse(new FileReader(filename));
			// use an iterator to iterate over each element of the array
			Iterator iter = fluTweets.iterator();
			// iterate while there are more objects in array
			while (iter.hasNext()) {
			// get the next JSON object
				JSONObject fluTweet = (JSONObject) iter.next();
			// use the "get" method to print the value associated with that key
				double[] coordinatesJ = formatLongLadi(fluTweet.get("location").toString());
				//System.out.println(coordinatesJ[1]);
				Tweet twit = new Tweet(coordinatesJ[0], coordinatesJ[1], fluTweet.get("time").toString(), fluTweet.get("text").toString());
				TweetList.add(twit);
			} 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (ParseException p) {
			p.printStackTrace();
		}
		catch (IOException o) {
		o.printStackTrace();
		}
		List<Tweet> dTweetList = TweetList;
		return dTweetList;
	}

}
