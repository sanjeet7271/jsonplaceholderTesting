package com.freenow.userspojoforpost;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class Geo {

	private String lat;
	private String lng;

	/**
	 * 
	 * @param lat
	 * @param lng
	 * @Constructor to initilaize default value
	 * 
	 */
	public Geo(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}
