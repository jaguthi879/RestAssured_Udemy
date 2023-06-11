package jsonpractice;

import io.restassured.path.json.JsonPath;

public class Complexjsonparse {
	public static void main(String[] args) {
		
		
		JsonPath js=new JsonPath(Payload.courseprice());
		
		//Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount
		int totalamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);
		
		//print the first course
		String titleofcourse=js.getString("courses[0].title");
		System.out.println( titleofcourse);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
   		String coursetitles=js.get("courses["+i+"].title");
   		System.out.println(js.get("courses["+i+"].price").toString());	
   		System.out.println(coursetitles);
		}
   		
    	//Print no of copies sold by RPA Course 	
   		for(int i=0;i<count;i++)
   		{
   	   		String coursetitles=js.get("courses["+i+"].title");
   	   		
   	   		if( coursetitles.equalsIgnoreCase("RPA")) 
   	   	{ 			
   	   		int copies=js.get("courses["+i+"].copies");
   	   		
   	     	System.out.println("Print no of copies sold by RPA Course:" + copies);
   	   		
   	   		break;
   	   		}
   		}			
		}
		 
	}
	



