package edu.upenn.cit594.data;

public class Tweet {
	public double longitude;
	public double latitude;
	public String time;
	public String text;
	
	public Tweet(double latitude, double longitude, String time, String text) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.text = text;
	}
}
