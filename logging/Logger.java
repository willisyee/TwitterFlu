package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.Main;
import edu.upenn.cit594.processor.Processor;


public class Logger {
	protected Processor processor = Main.fluProcessor;
	public static String file = Main.logFile;
	private PrintWriter pw;
	
	
	private Logger(String file) {
		try { pw = new PrintWriter(new File(file));}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private static Logger instance = new Logger(file);
	
	public static Logger getInstance() {
		return instance;
	}
	
	public void printToLog(String text) {
		pw.println(text);
		pw.flush();
	}
	
	/**
	 * takes the method noted in the processor interface to utilize a map<String, List<String> and
	 * print out a the key and each string in the value (list<string>) to a log 
	 */
	public void resultsToLog() {
		for(String key : processor.findFluTweetStates().keySet()) {
			for(String text : processor.findFluTweetStates().get(key)) { 
					String logText = key + "	" + text;
					//System.out.println(logText);
					printToLog(logText);
			}	
		}
	}
}
