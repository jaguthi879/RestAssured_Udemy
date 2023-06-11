package RestAssured_practice1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class Examples_api {
public static void main(String[] args) {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	//post place
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
	body(Payload.AddPlace()).
	  when().post("/maps/api/place/add/json").
	  then().statusCode(200).extract().response().asString();
	
	System.out.println(response);
	JsonPath js =new JsonPath(response);
	 String placeid=js.get("place_id");
	 System.out.println(placeid);
	 
	 //update place with address
	 String newAddress="77 Summerwalk,USA";
	 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
	 		+ "\"place_id\":\""+placeid+"\",\r\n"
	 		+ "\"address\":\""+newAddress+"\",\r\n"
	 		+ "\"key\":\"qaclick123\"\r\n"
	 		+ "}\r\n"
	 		+ "").
	 when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).
	 body("msg",equalTo("Address successfully updated"));
	 
	 //get place
	String getplaceresponse= given().log().all().queryParam("key","qaclick123").
			                  queryParam("palce_id",placeid).header("Content-Type","application/json").
	                          when().get("/maps/api/place/get/json").
	                          then().assertThat().log().all().statusCode(200).extract().asString();
				JsonPath js1=new JsonPath(getplaceresponse);
				String actualaddress=js.getString("address");
				System.out.println(actualaddress);
	//Assert.assertEquals("actualaddress", "newAddress");
		
				
	String response1=given().log().all().queryParam("key","qaclick123").
			           body(Payload.deleteplace(placeid)).
			          when().delete("/maps/api/place/delete/json").
			          then().assertThat().log().all().statusCode(200).extract().response().asString();

}
}