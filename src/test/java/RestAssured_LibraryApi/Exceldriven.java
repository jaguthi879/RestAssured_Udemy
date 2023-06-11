package RestAssured_LibraryApi;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Exceldriven {
	@Test
	public void addbook() throws IOException {
		
		Datadriven d=new Datadriven();
		   ArrayList data=d.getdata("Rest Addbook");
		HashMap <String,Object> map =new HashMap();
		map.put("name",data.get(1));
		map.put("isbn",data.get(2));
		map.put("aisle",data.get(3));
		map.put("author",data.get(4));
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().header("Content-Type","application/json").body(map).
		  when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);        
}
}
