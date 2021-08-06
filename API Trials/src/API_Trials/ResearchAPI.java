package API_Trials;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResearchAPI {

	public void largeAPIops() {

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/photos";

		Response rsp = given().contentType("application/json").when().get().then().extract().response();

		ArrayList id = (ArrayList) rsp.jsonPath().getList("albumId");
		
		
		System.out.println(id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResearchAPI ea = new ResearchAPI();
		ea.largeAPIops();
	}

}
