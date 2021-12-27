package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static  JsonPath rawtoJson(String Response) {
		JsonPath js1= new JsonPath (Response);
		return js1;
	}

} 
