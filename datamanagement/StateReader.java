package edu.upenn.cit594.datamanagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.State;

public class StateReader implements Reader<State>{
	
		String filename;
		
		public StateReader(String filename) {
			this.filename = filename;
		}
		
		/**
		 * takes a filename with a csv extension and produces a List of states to use for calculation and decision making 
		 */
		public List<State> getData() {
			ArrayList<State> stateList = new ArrayList<State>();
			try {
				File fileF = new File(filename);
				if(!fileF.canRead()) {
					System.out.println("error: cannot read state file");
					System.exit(0);
				}
				Scanner fileReader = new Scanner(new File(filename));
				while(fileReader.hasNextLine()) {
					String line = fileReader.nextLine();
					String[] column = line.split(",");
					State stateCenter = new State(column[0], Double.parseDouble(column[1]), Double.parseDouble(column[2]));
					stateList.add(stateCenter);
						//System.out.println(newFlight.toString());
				}
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			List<State> dstateList = stateList;
			return dstateList;
		}
		
		public static void main(String[] args) {
			StateReader t = new StateReader("states.csv");
			System.out.println(t.getData().get(0).name);
		}
}
