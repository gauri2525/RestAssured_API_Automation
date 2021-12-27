import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	@Test
	public void sumOfCourese() {
		int sum = 0;
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.get("courses.size()");
		for (int i=0 ; i<count; i++) {
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int Amount =price*copies;
			System.out.println(Amount);
			sum = sum+Amount;
			
		}
		
		System.out.println(sum);
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmt);

		
	}

}
