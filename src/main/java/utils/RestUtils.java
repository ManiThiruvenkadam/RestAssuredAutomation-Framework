package utils;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	public static Response taPost(String baseUri, HashMap<String, String> headers, HashMap<String, String> payload) {
		RestAssured.baseURI = baseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).post();
		return res;
	}

	public static Response taPost(String sBaseUri, HashMap<String, String> header, String payload) {

		RestAssured.baseURI = sBaseUri;
		/*if (body == null) {
            // If no body is provided, don't include the body in the request
            return RestAssured
                    .given()
                    .headers(header)
                    .when()
                    .post();
        } else {
            // If a body is provided, include it in the request
            return RestAssured
                    .given()
                    .headers(header)
                    .body(body)
                    .when()
                    .post();
        }*/
		System.out.println("taPost: URI :" + sBaseUri);
		//RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(header).when().body(payload).post();
		return res;
	}

	public static Response taGet(String sBaseUri, HashMap<String, String> header) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().get();
		return res;
	}

	public static Response taPut(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().body(payload).put();
		return res;
	}
	
	public static Response taDelete(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().body(payload).delete();
		return res;
	}
}
