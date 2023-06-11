package POJO_Serialization;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class Serialtest_using_specbuilders {
	
	@Test
	public void test() {
		Add_Place p=new Add_Place();
		//using object p calling the setter methods
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		//creating the list and passing the values to the list
		List <String> list=new ArrayList <String>();
		list.add("shoe park");
		list.add("shop");
		p.setTypes(list);
		//creating location class object and passing the values using by calling the setter method
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
			
    //using RequestSpecification and ResponseSpecification for specbuilders
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
				.setContentType(ContentType.JSON).build();		
		 ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 RequestSpecification res=given().spec(req).body(p)	;
		 Response response= res.when().post("/maps/api/place/add/json")
		        .then().spec(resspec).extract().response();
		         String responsestring=response.asString();
		 	     System.out.println(responsestring);
}
}
