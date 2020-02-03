package com.freenow.userspojoforresponse;

/**
 * 
 * @author sanjeetpandit
 *@utility to De-Serialize response recieved from get Request
 *
 */
public class Geo {

	private String lat;
	private String lng;

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
