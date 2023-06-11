package RestAssured_practice1;

public class Payload {
	
	public static String  AddPlace() {
	     return"{\r\n"
	     		+ "  \"location\": {\r\n"
	     		+ "    \"lat\": -38.383494,\r\n"
	     		+ "    \"lng\": 33.427362\r\n"
	     		+ "  },\r\n"
	     		+ "  \"accuracy\": 80,\r\n"
	     		+ "  \"name\": \"backline house\",\r\n"
	     		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
	     		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
	     		+ "  \"types\": [\r\n"
	     		+ "    \"shoe park\",\r\n"
	     		+ "    \"shop\"\r\n"
	     		+ "  ],\r\n"
	     		+ "  \"website\": \"http://google.com\",\r\n"
	     		+ "  \"language\": \"French-IN\"\r\n"
	     		+ "}\r\n"
	     		+ "";	
	}
	
	public static String deleteplace(String placeid) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}";
		
	}
	/*public  static String Updateplace() {
		return"{\r\n"
				+ "\"place_id\":\""+placeid+"\"\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	}*/

}
