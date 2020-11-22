
package edu.upenn.cit594;

import java.util.List;

import edu.upenn.cit594.data.Tweet;
import edu.upenn.cit594.datamanagement.JsonReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.StateReader;
import edu.upenn.cit594.datamanagement.TextReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.processor.TweetProcessor;
import edu.upenn.cit594.ui.Presentation;


public class Main {
	public static String logFile;	
	public static Reader tweetReader;
	public static Processor fluProcessor;
	
	public static void main(String[] args) {
		
		
		if(args.length != 4) {
			System.out.println("error: 4 arguments are not present");
			System.exit(0);
		}
		
		if(!args[0].toLowerCase().contains("text") && !args[0].toLowerCase().contains("json")) {
			System.out.println("error: format specified is neither text nor json");
			System.exit(0);
		}
	
	
		String tweetFileName = args[1];
		String stateFileName = args[2];
		String logFileName = args[3];
		logFile = logFileName;
		
		if(tweetFileName.contains(".txt")) {
			tweetReader = new TextReader(tweetFileName);
			
		}
		else {
			tweetReader = new JsonReader(tweetFileName);
		}
		
		StateReader stateReader = new StateReader(stateFileName);
		
		Processor processor = new TweetProcessor(tweetReader, stateReader);
		
		Presentation presentation = new Presentation(processor);
		
		presentation.displayStateFluNum();
		
		fluProcessor = processor;
		Logger l = Logger.getInstance();
		l.resultsToLog();
		
		System.out.println("test");
		
		/*	
		ArrayList<String> tokens = new ArrayList<>();
		Boolean insideQuote = false;
		int beginningOfToken = 0;
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '\"') {
				insideQuote = !insideQuote;
			}
			if(i == text.length()-1) {
				String lastToken = text.substring(beginningOfToken);
				tokens.add(lastToken);
			}
			else if(!insideQuote && text.charAt(i) == ',') {
				String token = text.substring(beginningOfToken, i);
				tokens.add(token);
				beginningOfToken = i+1;
			}
		}
		return tokens;
	*/
	}
}
