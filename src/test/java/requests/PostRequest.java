package requests;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.payload;
import pojo.User;

public class PostRequest  extends BaseTest{
	private Response response;
    private RequestSpecification request;
    private User user; 
    private payload pl = new payload();
    private List<LinkedHashMap<String, String>> userData;
    
   
@Override
protected String getSheetName() {
              return "postRequest ";
    	    }
   
@Test(dataProvider = "userDataProvider",priority=2)
public void Post_New_user(LinkedHashMap<String, String> userDataEntry) throws Exception{
	     user = pl.allUser(userDataEntry);
	     int expStatuscode=user.getstatusCode();
	     
	     response = given()
	    		   .log().all().contentType("application/json")
	               .when()
	               .body(pl.postBody(userDataEntry))
	               .post(user.getendpoint());
	     
	     int actualStatusCode=response.getStatusCode();
	     String jsonRes=response.getBody().asString();        
	            response.then().assertThat()
	            .header("Content-Type", "application/json");
	            Assert.assertEquals(actualStatusCode, expStatuscode, 
	                    "Expected status code is " + expStatuscode + 
	                    " Actual status is : " + actualStatusCode);
	            
	            if (jsonRes == null || jsonRes.isEmpty() && actualStatusCode==404 ) {
	         	   System.out.println("Schema is empty");	
	         } else
	         {
	         	response.then()
	             .assertThat()
	             .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json")); 
	         }
	            
	            
//	           User id retrieve  
	            String userid=response.jsonPath().getString("user_id");
	            String userFirstName=response.jsonPath().getString("User_first_name");
	             if (userid == null || userFirstName == null) {
	                System.out.println("Failed to retrieve user_id or user_first_name. Check API response.");
	            } else {
	                System.out.println("User ID: " + userid);
	                System.out.println("User First Name: " + userFirstName);
	            }
	            user = User.getInstance();
	            user.setuser_id(userid);
	            user.setUser_first_name(userFirstName);
	      }
}

	

