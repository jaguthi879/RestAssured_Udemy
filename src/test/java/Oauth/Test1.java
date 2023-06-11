package Oauth;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import POJO_Deserialisation.Api;
import POJO_Deserialisation.Get_course;
import POJO_Deserialisation.Webautomation;
import groovy.transform.stc.POJO;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Test1 {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*System.setProperty("webdriver.edge.driver", "C:\\Users\\237529\\Browserdriver\\msedgedriver.exe");
		 WebDriver driver = new EdgeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("jagruthi.karankote@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("o908");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);*/
		//String url=driver.getCurrentUrl();
		String intialCode = "https://rahulshettyacademy.com/getCourse.php?state=injioo&code=4%2F0AWtgzh6in-PhJOHt50zurrYAQfX0x0KmHbFvedNtv_xK40kvQOk97X3On0lTeUeVGkWJCQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String nextCode = intialCode.split("code=")[1];
		String code=nextCode.split("&scope")[0];
		 System.out.println(code);

		 String response = given()
				 .contentType("application/json")
				 .urlEncodingEnabled(false)
				 .queryParams("code", code)
				 .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				 .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				 .queryParams("grant_type", "authorization_code")
				 .queryParams("state", "injioo")
				 .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				 .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				 .when()
				 .log().all()
				 .post("https://www.googleapis.com/oauth2/v4/token").asString();
				 JsonPath js = new JsonPath(response);
				 String accessToken = js.get("access_token");
				 System.out.println(accessToken);		
				 Get_course gc = given()
						  .queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
						  .when()
						  .get("https://rahulshettyacademy.com/getCourse.php").as(Get_course.class);
						   System.out.println(gc.getInstructor());
						   System.out.println(gc.getLinkedIn());
						   System.out.println(gc.getInstructor());
						   System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());								
	}
	}