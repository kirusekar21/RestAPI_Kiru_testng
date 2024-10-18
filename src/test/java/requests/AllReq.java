//package requests;
//
//import static io.restassured.RestAssured.given;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import payloads.payload;
//import pojo.User;
//
//public class AllReq extends BaseTest{
//	private Response response;
//    private RequestSpecification request;
//    private User user; 
//    private payload pl = new payload();
//    private List<LinkedHashMap<String, String>> userData;
//    String userid;
//    
//@Override
//protected String getSheetName() {
//      return "All requests";
//    }
//
//@Test(dataProvider = "userDataProvider", priority=1)
//public void postRequests(LinkedHashMap<String, String> userDataEntry) {
//	user = pl.allUser(userDataEntry);
//    System.out.println(user);
//	 int expStatuscode=user.getstatusCode();
//    response = given().log().all().contentType("application/json")
//           .when().body(pl.postBody(userDataEntry)).post(user.getendpoint());
//            int actualStatusCode=response.getStatusCode();
//            System.out.println("Actual Status Code: " + actualStatusCode);
//            System.out.println("Response: " + response.asString());
//           response.then().assertThat()
//           .header("Content-Type", "application/json");
//           Assert.assertEquals(actualStatusCode, expStatuscode, 
//                   "Expected status code is " + expStatuscode + 
//                   " Actual status is : " + actualStatusCode);
//           userid=response.jsonPath().getString("user_id");
//           String userFirstName=response.jsonPath().getString("User_first_name");
//           
//
//           if (userid == null || userFirstName == null) {
//               System.out.println("Failed to retrieve user_id or user_first_name. Check API response.");
//           } else {
//               System.out.println("User ID: " + userid);
//               System.out.println("User First Name: " + userFirstName);
//           }
//           
//           user = User.getInstance();
//           user.setuser_id(userid);
//           user.setUser_first_name(userFirstName);
//     }
//  
//@Test(dataProvider = "userDataProvider", priority=2)
//public void getRequests(LinkedHashMap<String, String> userDataEntry) {
// user = pl.allUser(userDataEntry);
// int expStatuscode=user.getstatusCode();
// response = given().log().all()
//        .when().get(user.getendpoint()) 
//        .then().extract().response();
//
//        int actualStatusCode=response.getStatusCode();
//        response.then().assertThat()
//        .header("Content-Type", "application/json");
//        Assert.assertEquals(actualStatusCode, expStatuscode, 
//                "Expected status code is " + expStatuscode + 
//                " Actual status is : " + actualStatusCode);
//  }
//@Test(dataProvider = "userDataProvider", priority=3)
//public void getIdRequests(LinkedHashMap<String, String> userDataEntry) {
// user = pl.allUser(userDataEntry);
// int expStatuscode=user.getstatusCode();
// response = given().log().all()
//        .when().get(user.getendpoint()+userid) 
//        .then().extract().response();
//
//        int actualStatusCode=response.getStatusCode();
//        response.then().assertThat()
//        .header("Content-Type", "application/json");
//        Assert.assertEquals(actualStatusCode, expStatuscode, 
//                "Expected status code is " + expStatuscode + 
//                " Actual status is : " + actualStatusCode);
//  }
//@Test(dataProvider = "userDataProvider", priority=4)
//public void putRequests(LinkedHashMap<String, String> userDataEntry) {
//	user = pl.allUser(userDataEntry);
//	   String userid=user.getuser_id();
//	   System.out.println(userid);
//	     System.out.println(user);
//		 int expStatuscode=user.getstatusCode();
//	     response = given().log().all().contentType("application/json")
//	            .when().body(pl.putBody(userDataEntry)).put(user.getendpoint()+"10680");
//	             int actualStatusCode=response.getStatusCode();
//	             System.out.println("Actual Status Code: " + actualStatusCode);
//	             System.out.println("Response: " + response.asString());
//	            response.then().assertThat()
//	            .header("Content-Type", "application/json");
//	            Assert.assertEquals(actualStatusCode, expStatuscode, 
//	                    "Expected status code is " + expStatuscode + 
//	                    " Actual status is : " + actualStatusCode);
//  }
//@Test(dataProvider = "userDataProvider", priority=5)
//public void deleteRequests(LinkedHashMap<String, String> userDataEntry) {
//	 user = pl.allUser(userDataEntry);
//	 int expStatuscode=user.getstatusCode();
//     response = given().log().all()
//            .when().delete(user.getendpoint()+userid) 
//            .then().extract().response();
//    
//            int actualStatusCode=response.getStatusCode();
//            response.then().assertThat()
//            .header("Content-Type", "application/json");
//            Assert.assertEquals(actualStatusCode, expStatuscode, 
//                    "Expected status code is " + expStatuscode + 
//                    " Actual status is : " + actualStatusCode);
//  }
//}
