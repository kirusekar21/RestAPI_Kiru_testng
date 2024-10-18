package requests;


import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.LinkedHashMap;
import java.util.List;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.payload;
import pojo.User;


public class GetRequests extends BaseTest{
	    private Response response;
	    private RequestSpecification request;
	    private User user; 
	    private payload pl = new payload();
	    private List<LinkedHashMap<String, String>> userData;
	    
@Override
protected String getSheetName() {
          return "Get All";
	    }

@Test(dataProvider = "userDataProvider", priority=1)
public void GetAllUser(LinkedHashMap<String, String> userDataEntry) {
	 user = pl.allUser(userDataEntry);
	 int expStatuscode=user.getstatusCode();
//	 JsonSchemaValidator schema =JsonSchemaValidator.matchesJsonSchema("SchemaValidation.json");
	 
     response = given()
    		   .log().all()
               .when()
               .get(user.getendpoint()) 
               .then()
               .header("Content-Type", "application/json")
               .extract().response();
        String jsonRes=response.getBody().asString(); 
        int actualStatusCode=response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expStatuscode);
        if (jsonRes == null || jsonRes.isEmpty() && actualStatusCode==404 ) {
        	   System.out.println("Schema is empty");	
        } else
        {
        	response.then()
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json")); 
        }
        
                    
      }
}


