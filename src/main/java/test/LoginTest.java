package test;

import java.io.IOException;


import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;

import io.restassured.response.Response;

import utils.DataUtils;
import utils.RestUtils;



public class LoginTest extends BaseTest{

	//@Test
	public void login() throws IOException{
	//RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		String envUri = JsonPath.read(env, "$.prod.uri") ;
		String endpoint = JsonPath.read(env, "$.prod.endpoint.login");
		String creds = DataUtils.readJsonFileToString(FileConstants.USERACCOUNTS_TD_FILE_PATH);
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("username", JsonPath.read(creds, "$.prod.valid.username"));
		credentials.put("password", JsonPath.read(creds, "$.prod.valid.password"));
		Response res = RestUtils.taPost(envUri+endpoint, headers, credentials);
		System.out.println(res.prettyPrint());
		
}
		//@Test
		public void loginWithWrongCredentials() throws IOException{
			
			HashMap<String, String>headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
			String envUri = JsonPath.read(env, "$.prod.uri") ;
			String endpoint = JsonPath.read(env, "$.prod.endpoint.login");
			String creds = DataUtils.readJsonFileToString(FileConstants.USERACCOUNTS_TD_FILE_PATH);
			HashMap<String, String> credentials = new HashMap<String, String>();
			credentials.put("username", JsonPath.read(creds, "$.prod.invalid.username"));
			credentials.put("password", JsonPath.read(creds, "$.prod.invalid.password"));
			Response res = RestUtils.taPost(envUri+endpoint, headers, credentials);
			System.out.println(res.prettyPrint());
			
		}
		
		//@Test
			public void testGetData() throws IOException {
			//base URI
			String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
			String envUri = JsonPath.read(env, "$.prod.uri");
	        String endpoint = JsonPath.read(env, "$.prod.endpoint.getdata");
	        
	        //headers
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("token", "c2xwMHR4iWBLeQWRqxI2MgXR3wriOdkTNo9wTTfqtw2iRbpBFtttB9v7Tv8FyyVx8H3dmSNhUBd0dwhXW7Fj1uaVFnQPSAEQc3nw");

			//call get reusable method from RestUtils
			Response res = RestUtils.taGet(envUri + endpoint, headers);

			System.out.println(res.prettyPrint());

			// Check status code
			int statusCode = res.getStatusCode();
			System.out.println("Status Code: " + statusCode);
		}
			/*@Test
			public void addDataPostMethod() throws IOException {
			    String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		        String envUri = JsonPath.read(env, "$.prod.uri");
		        String endpoint = JsonPath.read(env, "$.prod.endpoint.adddata");
						
		        HashMap<String, String> headers = new HashMap<>();
		        headers.put("Content-Type", "application/json");
		        headers.put("token", "c2xwMHR4iWBLeQWRqxI2MgXR3wriOdkTNo9wTTfqtw2iRbpBFtttB9v7Tv8FyyVx8H3dmSNhUBd0dwhXW7Fj1uaVFnQPSAEQc3nw");
		        		
		        String requestBodyPath = DataUtils.readJsonFileToString(FileConstants.USER_TD_FILE_PATH); // Path to users.json
		        String requestBody = JsonPath.read(requestBodyPath, "$.data"); // Read the content of the JSON file

		        Response res = RestUtils.taPost(envUri + endpoint, headers, requestBody);
						
		        System.out.println(res.prettyPrint());

		        int statusCode = res.getStatusCode();
		        Assert.assertEquals(statusCode, 200, "Correct status code not returned.");

		        // Optionally, validate the response body
		        String message = JsonPath.read(res.getBody().asString(), "$.message");
		        Assert.assertEquals(message, "Data added successfully", "Response message mismatch");
			} */
			
			

				   // @Test
				    public void addDataPostMethod() throws IOException {
				       
				        String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
				        String envUri = JsonPath.read(env, "$.prod.uri");
				        String endpoint = JsonPath.read(env, "$.prod.endpoint.adddata");

				        // Set headers
				        HashMap<String, String> headers = new HashMap<>();
				        headers.put("Content-Type", "application/json");
				        headers.put("token", "9sHSx1qy5uy41Pp29sThNsASofEMowVM2KlANdSGtqV24NBB9ssxnX8c6H82NGOOSF6k5qz7p3ycEdO6bdpsmq43jlpI2VvA8N7j");

				        String requestBodyPath = DataUtils.readJsonFileToString(FileConstants.USER_TD_FILE_PATH); // Path to users.json

				        //Extract the data from the users.json and store it as a LinkedHashMap
				        LinkedHashMap<String, Object> requestBodyMap = JsonPath.read(requestBodyPath, "$.data2");

				        // Convert LinkedHashMap to JSON String using ObjectMapper
				        ObjectMapper objectMapper = new ObjectMapper();
				        String requestBody = objectMapper.writeValueAsString(requestBodyMap);  // Convert LinkedHashMap to JSON String

				        Response res = RestUtils.taPost(envUri + endpoint, headers, requestBody);

				        // Print response in pretty format
				        System.out.println(res.prettyPrint());

				        // Validate status code
				        int statusCode = res.getStatusCode();
				        Assert.assertEquals(statusCode, 201, "Correct status code not returned.");

				       
				    }
				
				  //  @Test
				    public void updateDataPutMethod() throws IOException {
				    	String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
				        String envUri = JsonPath.read(env, "$.prod.uri");
				        String endpoint = JsonPath.read(env, "$.prod.endpoint.adddata");
				        
				        HashMap<String, String> headers = new HashMap<>();
				        headers.put("Content-Type", "application/json");
				        headers.put("token", "c2xwMHR4iWBLeQWRqxI2MgXR3wriOdkTNo9wTTfqtw2iRbpBFtttB9v7Tv8FyyVx8H3dmSNhUBd0dwhXW7Fj1uaVFnQPSAEQc3nw");

				    }
				    
				    //@Test
				    public void deleteDataDeleteMethod() throws IOException {
				    	String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
				        String envUri = JsonPath.read(env, "$.prod.uri");
				        String endpoint = JsonPath.read(env, "$.prod.endpoint.deletedata");
				        
				        HashMap<String, String> headers = new HashMap<>();
				        headers.put("Content-Type", "application/json");
				        headers.put("token", "9sHSx1qy5uy41Pp29sThNsASofEMowVM2KlANdSGtqV24NBB9ssxnX8c6H82NGOOSF6k5qz7p3ycEdO6bdpsmq43jlpI2VvA8N7j");
				        
				        String requestBodyPath = DataUtils.readJsonFileToString(FileConstants.USER_TD_FILE_PATH);

				        // Extract the data to delete from the users.json and store it as a LinkedHashMap
				        HashMap<String, String> requestBodyMap = JsonPath.read(requestBodyPath, "$.data2");
				        
				        Response res = RestUtils.taDelete(envUri + endpoint, headers, requestBodyMap);

				        System.out.println(res.prettyPrint());

				        // Validate the status code
				        int statusCode = res.getStatusCode();
				        Assert.assertEquals(statusCode, 201, "Correct status code not returned for DELETE request.");
				        
				        // to check the response
				        String message = JsonPath.read(res.getBody().asString(), "$.message");
				        Assert.assertEquals(message, "Data deleted successfully", "Response message mismatch.");
				    }
				    
				    @Test
				    public void logoutPostMethod() throws IOException {
				    	String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
				        String envUri = JsonPath.read(env, "$.prod.uri");
				        String endpoint = JsonPath.read(env, "$.prod.endpoint.logout");
				        
				        HashMap<String, String> headers = new HashMap<>();
				        headers.put("Content-Type", "application/json");
				        headers.put("token", "9sHSx1qy5uy41Pp29sThNsASofEMowVM2KlANdSGtqV24NBB9ssxnX8c6H82NGOOSF6k5qz7p3ycEdO6bdpsmq43jlpI2VvA8N7j");
				        
				        Response res = RestUtils.taPost(envUri + endpoint, headers, null);  // Assuming no request body for logout

				        // Print the response
				        System.out.println(res.prettyPrint());

				        int statusCode = res.getStatusCode();
				        Assert.assertEquals(statusCode, 201, "Logout failed! Expected status code 200.");

				        String message = JsonPath.read(res.getBody().asString(), "$.message");  // Assuming the response has a "message" field
				        Assert.assertEquals(message, "Logout successful", "Logout message mismatch.");
				        
				    }
				    

	    }

