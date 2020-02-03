package com.freenow.userspojoforpost;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class Company {

	private String name;
	private String catchPhrase;
	private String bs;

	/**
	 * 
	 * @param name
	 * @param catchPhrase
	 * @param bs
	 * @Constructor to initilaize default value
	 */
	public Company(String name, String catchPhrase, String bs) {
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

}
