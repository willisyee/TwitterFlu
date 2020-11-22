package edu.upenn.cit594.datamanagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Tweet;

public class TextReader extends formatString implements Reader<Tweet>{
	
	protected String filename;
	
	public TextReader(String name) {
		filename = name;
	}
	
	/**
	 * takes a filename with a txt extension and produces a List of Tweets to use for calculation and decision making 
	 */
	public List<Tweet> getData() {
		ArrayList<Tweet> TweetList = new ArrayList<Tweet>();
		try {
			File fileF = new File(filename);
			if(!fileF.canRead()) {
				System.out.println("error: cannot read text file");
				System.exit(0);
			}
			Scanner fileReader = new Scanner(new File(filename));
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				String[] column = line.split("	");
				double[] coordinates = formatLongLadi(column[0]);
				
				Tweet twit = new Tweet(coordinates[0], coordinates[1], column[2], column[3]);
				TweetList.add(twit);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Tweet> dTweetList = TweetList;
		return dTweetList;
	}

}
