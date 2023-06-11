package RestAssured_LibraryApi;

public class Payload {
	
	public static String addbook(String isbn, String aisle ) {
		String add_book="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		return add_book;
	}
	
}
