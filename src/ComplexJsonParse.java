import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js= new JsonPath(Payload.coursePrice());
		
		//Print no of courses returned by API.
		int count = js.getInt("courses.size()");
		System.out.println("No of courses returned by API : "+count);
		
		// Print Purchase amount 
		int Totalamt = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total PurchaseAmount is : " + Totalamt);
		
		//Print the title of the course
		String firstcourse = js.getString("courses[0].title");
		System.out.println("Title of the first course is : "+ firstcourse);
		
		//Print all course titles and their respective prices
		for (int i=0 ;i<count; i++) {
			String courseTitle = js.get("courses["+i+"].title");
		   int  CousesPrice= js.getInt("courses["+i+"].price");
		   System.out.println("Course titles with their respective prices are :" +courseTitle+":"+CousesPrice);
		   
		   
		//Print no of copies sold  by RPA.
		   for (int j=0 ;j<count; j++) {
				String courseTitle1 = js.get("courses["+j+"].title");
			if(courseTitle1.equalsIgnoreCase("RPA")) {
			int copiescount =js.getInt(("courses["+j+"].copies"));
			System.out.println("Copies sold by RPA course are : " + copiescount);
			break;
			
		// Verify if sum of all course price matches with Purchase amount 
			
			
			}
			}
		   
		}
		
		

	}

}

