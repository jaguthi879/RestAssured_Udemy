package Ecommerce_APi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecom {
	
	public static void main(String[] args) {
		
	//Login call to generate the auth token
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			                .setContentType(ContentType.JSON).build();
	
	Loginrequest loginrequest=new Loginrequest();
	loginrequest.setUserEmail("riya@123gmail.com");
	loginrequest.setUserPassword("Riya@123");
	
	RequestSpecification loginreq=given().log().all().spec(req).body(loginrequest);
	LoginResponse loginres=loginreq.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
	System.out.println(loginres.getToken());
	String token=loginres.getToken();
	System.out.println(loginres.getUserId());
	String userid=loginres.getUserId();
	
	//Creating the product 
	RequestSpecification productbasereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                                        .addHeader("authorization", token).build();
	RequestSpecification addproductreq= given().log().all().spec(productbasereq).param("productName","phone").param("productAddedBy",userid)
	 .param("productCategory", "fashion").param("productSubCategory", "shirts").param("productPrice", "11500")
	 .param("productDescription","Addias Originals").param("productFor", "women")
	 .multiPart("productImage",new File("C:\\Users\\237529\\Pictures\\phone.jpg"));
	 
	String addproductres=addproductreq.when().post("/api/ecom/product/add-product")
			                .then().log().all().extract().response().asString();
	JsonPath js=new JsonPath(addproductres);
	String productid=js.get("productId");
	System.out.println(productid);
	
	//Creating the order 
	RequestSpecification orderbasereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addHeader("authorization", token).setContentType(ContentType.JSON).build();
	
	 Orderdetails od=new  Orderdetails();
	 od.setCountry("British Indian Ocean Territory");
	 od.setProductOrderedId(productid);
	 List<Orderdetails>orderdetailist=new ArrayList<Orderdetails> ();
	 orderdetailist.add(od);
	 Order order=new Order();
	 order.setOrders(orderdetailist);
	 RequestSpecification createorder= given().log().all().spec(orderbasereq).body(order);
	String responseorder= createorder.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	System.out.println(responseorder);
	
	//Delete the product
	RequestSpecification deletebasereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addHeader("authorization", token).setContentType(ContentType.JSON).build();
	 RequestSpecification deleteorder= given().log().all().spec(deletebasereq).pathParam("productId",productid);
	String deleteresponse= deleteorder.when().delete("/api/ecom/product/delete-product/{productId}")
			              .then().log().all().extract().response().asString();
	System.out.println(deleteresponse);
	JsonPath js1=new JsonPath(deleteresponse);
	String message=js.get("message");
	System.out.println(message);	
	}
}