import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;

public class AddAPIValidate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Validate if Add Place API is working as expected
		
		// Any Rest Assured automation test works only on below three principles
		// given == all input details
		// when == submit the API (Resource and HTTP methods)
		// Then == Validate the Response
		
		// Give Base URI
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlaceRequestBody())
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(Response);
		
		JsonPath js= new JsonPath(Response); // For parsing json
		String PlaceID=js.getString("place_id");
		
		System.out.println("PlaceID is: " + PlaceID);
		
		
		//Update Place
		
		String newAddress= "65 winter walk, USA1";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		 //Get Place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id" , PlaceID)
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReusableMethods.rawtoJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		Assert.assertEquals(actualAddress, newAddress);
		
	}

	
	    
	
}
