package requests;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.payload;
import pojo.User;

public class PutRequest extends BaseTest{
	 
		private Response response;
	    private RequestSpecification request;
	    private User user; 
	    private payload pl = new payload();
	    private List<LinkedHashMap<String, String>> userData;
	    
	   
	@Override
	protected String getSheetName() {
	              return "PutUpdateUser";
	    	    }
	   
	@Test(dataProvider = "userDataProvider",priority=3)
	public void Put_New_user(LinkedHashMap<String, String> userDataEntry) throws Exception{
		   user = pl.allUser(userDataEntry);
		   String userid=user.getuser_id();
		   int expStatuscode=user.getstatusCode();
		   
		   response = given()
				     .log().all()
				     .contentType("application/json")
		             .when()
		             .body(pl.putBody(userDataEntry))
		             .put(user.getendpoint()+userid);
		    
		             String jsonRes=response.getBody().asString();  
		             int actualStatusCode=response.getStatusCode();
		             if (jsonRes == null || jsonRes.isEmpty() && actualStatusCode==404 ) {
			         	   System.out.println("Schema is empty");	
			         } else
			         {
			         	response.then()
			             .assertThat()
			             .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json")); 
			         }
		             
		            response.then().assertThat()
		            .header("Content-Type", "application/json");
		            Assert.assertEquals(actualStatusCode, expStatuscode);
		                   
		      }
	}

