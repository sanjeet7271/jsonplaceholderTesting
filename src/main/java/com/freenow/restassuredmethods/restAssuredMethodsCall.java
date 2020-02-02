package com.freenow.restassuredmethods;

import com.freenow.constants.HttpMethods;
import com.freenow.resources.ResourceURLs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredMethodsCall {
	ResourceURLs resource = new ResourceURLs();
	Response response = null;

	public Response restAssuredCalls(String requestType,String entityString, String request, String id, String queryParam) {

		switch (requestType) {
		case HttpMethods.GET:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().log().all().when().get(request).then().extract().response();
			break;

		case HttpMethods.GET_WITH_QUERYPARAM:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().queryParam(queryParam, id).log().all().when().get(request).then().extract()
					.response();
			break;

		case HttpMethods.POST:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().body(entityString).log().all().contentType(ContentType.JSON)
					.when().post(request).then().extract().response();
			break;
		case HttpMethods.POST_WITH_QUERYPARAM:
			RestAssured.baseURI = resource.getBaseURI();
			response = RestAssured.given().queryParam(queryParam, id).body(entityString).log().all().contentType(ContentType.JSON)
					.when().post(request).then().extract().response();
			break;
		case HttpMethods.PUT:
			// TO DO
			break;

		case HttpMethods.PATCH:
			// TO DO
			break;

		case HttpMethods.DEFAULT:
			// TO DO
			break;

		}

		return response;
	}
}
