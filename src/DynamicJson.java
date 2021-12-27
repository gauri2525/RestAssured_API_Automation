import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;

import static io.restassured.RestAssured.*;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	
	
@Test(dataProvider="Booksdata")
public void addBook(String isbn, String aisle) {
	
	RestAssured.baseURI="http://216.10.245.166";
	
	String AddBookResponse= given().header("Content-Type","application/json").
	body(Payload.AddBook(isbn, aisle)).
	when().post("Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).
	extract().response().asString();
	
	JsonPath js=ReusableMethods.rawtoJson(AddBookResponse);
	
	String BookID=js.get("ID");
	
	System.out.println("New book's ID is "+BookID);
	
	//Adding print statement directly in Giithub
	System.out.println("added directly from GitHub");
	
}

@DataProvider(name="Booksdata")
public Object[][] getData(){
	
	return new Object[][] {{"baszd", "11755"},{"bapzd", "11022"},{"baqqd", "11995"}};
	
}
{
	
}
}
