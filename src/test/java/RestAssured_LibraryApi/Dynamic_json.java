package RestAssured_LibraryApi;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamic_json {
	
	@Test(dataProvider="Booksdata")
	public void postmethod(String isbn, String aisle ) {
		RestAssured.baseURI="http://216.10.245.166";
	String response= given().header("Content-Type","application/json").body(Payload.addbook(isbn,aisle)).
	  when().post("Library/Addbook.php").
	  then().log().all().statusCode(200).extract().asString();
	  System.out.println(response);
	  JsonPath js =new JsonPath(response);
		 String ID=js.get("ID");
		 System.out.println(ID);	
	}
	//using the Dataprovider to send the multiple set of data
	@DataProvider(name="Booksdata")
	public Object[][] getdata() {
		return new Object[][] {{"zxcb","2380"},{"poii","7695"},{"lkooh","8001"}};
	}
	
}
