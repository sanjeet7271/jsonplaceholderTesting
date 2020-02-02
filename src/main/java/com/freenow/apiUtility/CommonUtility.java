package com.freenow.apiUtility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.freenow.resources.SearchForUseName;
import com.freenow.usersPojoForResponse.UsersJson;

import io.restassured.response.Response;

public class CommonUtility {
	SearchForUseName username = new SearchForUseName();
	private static Logger logger = Logger.getLogger(CommonUtility.class);

	public void findUserID(Response response) {
		logger.info("Test case starts..");
		List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
		logger.info("Total " + userjson.size() + " users present int the user list");
		for (int i = 0; i < userjson.size(); i++) {
			if (userjson.get(i).getUsername().equals(username.getUserName())) {
				Assert.assertEquals(userjson.get(i).getUsername(), username.getUserName(),
						"user name not found int the list");
				logger.info("Test case starts for :" + username.getUserName());
				int id = userjson.get(i).getId();
				logger.info("User id for username " + userjson.get(i).getUsername() + " is " + id);
			}
		}
		logger.info("Test case finished..");

	}

	public void CheckDuplicate(List<Integer> ids) {
		Object[] arr = ids.toArray();
		Set<Integer> hs = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					hs.add((Integer) arr[i]);
					break;
				}
			}
		}
		Object[] B = hs.toArray();
		if (B.length > 0) {
			logger.info("duplicate ids are :"+Arrays.toString(B));
			Assert.fail("id should be unique.");
		} else {
			logger.info("duplicate id is not found");
		}
	}
	
	/*public boolean CheckIdisNull(Object obj) {
		int id = 0;
		
	    if (obj instanceof Integer) {
	        return id == ((Integer)obj).intValue();
	    }
	    return false;
	}*/
}
