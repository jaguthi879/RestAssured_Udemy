package jsonpractice;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Sum_validation {
	
	//Verify if Sum of all Course prices matches with Purchase Amount	
	@Test
	public void totalsum() {

		int sum=0;
		JsonPath js=new JsonPath(Payload.courseprice());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++) {
	   		int price=js.get("courses["+i+"].price");
		    int copies=js.get("courses["+i+"].copies");
		    int amount=price*copies;
		    System.out.println(amount);
		    sum=sum+amount;
	}
       System.out.println(sum);
       int purchaseamount=js.getInt("dashboard.purchaseAmount");
       Assert.assertEquals(sum, purchaseamount);
}
}