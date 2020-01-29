package com.freenow.restassuredmethods;

import com.freenow.constants.httpMethods;
import com.freenow.resources.resourceURLs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class restAssuredMethodsCall {
	resourceURLs resource = new resourceURLs();
	Response response = null;
	
	public Response restAssuredCalls(String requestType, String request, String id, String queryParam) {
		
		switch (requestType) {
		case httpMethods.GET:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().when().get(request).then().extract().response();
			break;
			
		case httpMethods.GET_WITH_QUERYPARAM:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().queryParam(queryParam, id).log().all().when().get(request).then().extract().response();
			break;
			
		case httpMethods.POST:
			// TO DO
			break;

		case httpMethods.PUT:
			// TO DO
			break;

		case httpMethods.PATCH:
			// TO DO
			break;

		case httpMethods.DEFAULT:
			// TO DO
			break;

		}

		return response;
	}
}
