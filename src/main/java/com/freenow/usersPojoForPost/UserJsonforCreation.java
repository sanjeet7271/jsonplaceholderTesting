package com.freenow.userspojoforpost;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * 
 * @author sanjeetpandit
 *
 */
public class UserJsonforCreation {
	/**
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param email
	 * @param street
	 * @param suite
	 * @param city
	 * @param zipcode
	 * @param lat
	 * @param lng
	 * @param phone
	 * @param website
	 * @param Cname
	 * @param catchPhrase
	 * @param bs
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @dataProvider to read test data from excel sheat
	 */
	public String userJsonData(int id, String name, String username, String email, String street, String suite,
			String city, String zipcode, String lat, String lng, String phone, String website, String Cname,
			String catchPhrase, String bs) throws JsonGenerationException, JsonMappingException, IOException {
		Geo geo = new Geo(lat, lng);
		Address addr = new Address(street,suite, city, zipcode, geo);
		Company company = new Company(Cname, catchPhrase, bs);
		UsersJson p = new UsersJson(id, name, username, email, addr, phone, website, company);
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		
		return mydata;
	}
}
