package edu.upenn.cit594.datamanagement;

public class formatString {
	
	/**
	 * turns two numbers in the string format into doubles 
	 * @param coordinate - String formated in "[####, ####]"
	 * @return numCoordinates which turns the numbers in string into a double[]
	 */
	public double[] formatLongLadi(String coordinate) {
		String noBrac = coordinate.substring(1, coordinate.length()-1);
		String[] stringArray = noBrac.split(",");
		double[] numCoordinates = new double[2];
		numCoordinates[0] = Double.parseDouble(stringArray[0]);
		numCoordinates[1] = Double.parseDouble(stringArray[1]);
		return numCoordinates;
	}
}
