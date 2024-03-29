package API_Trials;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

public class apiTest {

	public void basicApiCall() throws InterruptedException {
		// given().when().then();

		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RestAssured.basePath = "/Mumbai";
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();
		String responseBody = response.getBody().prettyPeek().asString();
		System.out.println("Response Body is => /n" + responseBody);
	}
	
	public void get_climate_proc() throws InterruptedException {
		// given().when().then();

		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		//RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Mumbai");
		//Response response = httpRequest.request(Method.GET, "/employees");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		//extracting the list
		String responseCity = response.jsonPath().getString("City");
		System.out.println(responseCity);
		
		//***Test NG Trial
		//Assert.assertEquals(responseCity, "Mumbai");
		
		//ArrayList id = (ArrayList) response.jsonPath().getList("City");
		//System.err.println(id);
		
		//extracting specific array
		jsonRowExtractor(responseCity);
		
		System.out.println(response.getBody().prettyPeek());
		
	}
	
	public void jsonRowExtractor(String subarry) throws InterruptedException
	{
		
		//JSONObject job = new JSONObject(subarry);
		//JSONArray JSONResponseBody = new   JSONArray(job.getJSONArray("data").toString());
		JSONArray JSONResponseBody = new   JSONArray(subarry);
		//JSONObject job2 = new JSONObject(subarry); 
		JSONObject job1= null, job2=null;
		System.out.println("The size of json array is: "+JSONResponseBody);
		Thread.sleep(10);
		String ar =null, obj=null;
		for(int i =0; i< JSONResponseBody.length();i++)
		{
			job1 = JSONResponseBody.getJSONObject(i);
			ar = job1.getString("employee_name");
			if(ar.contains("Ashton"))
			{
				obj = job1.toString();
				System.out.println("The employeename is "+ar);
				System.out.println("Changing the employeename to Katappa");
				job1.put("employee_name", "Katappa");
				break;
			}
		}
		System.out.println("The Desired json array is: "+job1.toString(2));
		System.out.println("The original json array is: "+JSONResponseBody.toString());
		job2 = new JSONObject(obj);
		System.out.println("The Desired json array is: "+job2.toString(2));
	}

	public void get_climate_BDD() {
		given().baseUri("https://demoqa.com/utilities/weather/city")
		//authentication
		//.auth().preemptive().basic("", "")
				// When
				.when().get("/Banglore")
				// Then
				.then().statusCode(200).statusLine("HTTP/1.1 200 OK").assertThat().body("City", equalTo("Mumbai"));
		/*
		 * // To verify booking count .body("bookingid", Matchers.hasSize(10)) // To
		 * verify booking id at index 3 .body("bookingid[3]", Matchers.equalTo(1));
		 */

	}

	public void post_req_proc() {
		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification request = RestAssured.given();
		HashMap queryParams = new HashMap();
		queryParams.put("name", "Jamal kubda");
		queryParams.put("job", "chori");
		System.out.println("Name is :" + queryParams.get("name"));

		

		request.header("Content-Type", "application/json");
		
		// for below step we need 'Gson' library
		// request.body(queryParams);
		
		// json object
		JSONObject crunchifyObject = new JSONObject(queryParams);
		request.body(crunchifyObject);
		Response response = request.post("/users");

		// check response
		int statusCode = response.getStatusCode();
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("Status Code is :" + statusCode);
		System.out.println(response.asString());
		System.out.println("The id generated is: " + response.jsonPath().get("id"));
	}

	public void post_req_BDD() {
		HashMap queryParams = new HashMap();
		queryParams.put("name", "Jamal kubda");
		queryParams.put("job", "chori");
		
		//json object trial
		JSONObject crunchifyObject = new JSONObject(queryParams);
		//given().baseUri("https://reqres.in/api").basePath("/users").contentType("application/json").body(crunchifyObject) //with json object body
		given().baseUri("https://reqres.in/api").basePath("/users").contentType("application/json").body(queryParams) //with hashmap body
				.when().post()
				// Then
				.then().statusCode(201).and().assertThat()
				.body("name", equalTo("Jamal kubda"));

	}
	
	public void put_req_BDD() {
		HashMap queryParams = new HashMap();
		queryParams.put("title", "Mr benit has updated the string for test purpose");
		queryParams.put("body", "Body is modified for test purpose");

		Response o = (Response) given().baseUri("https://jsonplaceholder.typicode.com").basePath("/posts/1").contentType("application/json").body(queryParams)
				.when().put()
				// Then
				.then().statusCode(200)
				.and().assertThat()
				.body("body", equalTo("Body is modified for test purpose"))
				.and().extract().response();
		
		System.out.println(o.jsonPath().getString("id"));
		//System.out.println(o.jsonPath().toString());
		
			}
	
	public void Auth_post_Test()
	{
		System.out.println("This method is for authentication calls");	
		String authKey = "";
	}

	public static void main(String[] args) throws InterruptedException {

		String cs = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"Ashton Cox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"Cedric Kelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"Airi Satou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"Brielle Williamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"Herrod Chandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"Rhona Davidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"Colleen Hurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"Sonya Frost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"Jena Gaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"Quinn Flynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"Charde Marshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"Haley Kennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"Tatyana Fitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"Michael Silva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"Paul Byrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"Gloria Little\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"Bradley Greer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"Dai Rios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"Jenette Caldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"Yuri Berry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"Caesar Vance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"Doris Wilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully! All records has been fetched.\"}";
		String ss = "[{\"id\":1,\"employee_name\":\"Tiger Nixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"Garrett Winters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"Ashton Cox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"Cedric Kelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"Airi Satou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"Brielle Williamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"Herrod Chandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"Rhona Davidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"Colleen Hurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"Sonya Frost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"Jena Gaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"Quinn Flynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"Charde Marshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"Haley Kennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"Tatyana Fitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"Michael Silva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"Paul Byrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"Gloria Little\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"Bradley Greer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"Dai Rios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"Jenette Caldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"Yuri Berry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"Caesar Vance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"Doris Wilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}]";
		apiTest at = new apiTest();
		at.get_climate_proc();
		//at.jsonRowExtractor(ss);
		//at.get_climate_BDD();
		// at.post_req_proc();
		//at.post_req_BDD();
		//at.put_req_BDD();
		//at.basicApiCall();
	}

}
