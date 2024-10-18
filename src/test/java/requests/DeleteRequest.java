package requests;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.payload;
import pojo.User;

public class DeleteRequest {
	public class GetRequests extends BaseTest{
	    private Response response;
//	    private RequestSpecification request;
	    private User user; 
	    private payload pl = new payload();
//	    private List<LinkedHashMap<String, String>> userData;
	    
@Override
protected String getSheetName() {
          return "DeleteUser";
	    }

@Test(dataProvider = "userDataProvider", priority=4)
public void DeleteByIdAndUser(LinkedHashMap<String, String> userDataEntry) {
	 user = pl.allUser(userDataEntry);
	 int expStatuscode=user.getstatusCode();
     response = given()
    		   .log().all()
               .when()
               .delete(user.getendpoint()) 
               .then()
               .extract().response();
    
            int actualStatusCode=response.getStatusCode();
            response.then().assertThat()
            .header("Content-Type", "application/json");
            Assert.assertEquals(actualStatusCode, expStatuscode, 
                    "Expected status code is " + expStatuscode + 
                    " Actual status is : " + actualStatusCode);
      }
	}
}
