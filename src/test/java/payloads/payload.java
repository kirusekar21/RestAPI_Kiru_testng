package payloads;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;
import pojo.User_address;
import utils.ExcelReader;

public class payload extends ExcelReader {

    private static User user;
    private User_address userAddress;
    private static ObjectMapper objectMapper = new ObjectMapper();
    static LinkedHashMap<String, String> userData;
    
    public  User allUser(LinkedHashMap<String, String> data) {
    	user = new User();
    	user.setendpoint(data.get("endpoint"));
        user.setstatusCode(Integer.parseInt(data.get("statuscode")));
        return user;
    }

    public User postBody(LinkedHashMap<String, String> data) {
        user = new User();
        userAddress = new User_address();
        user.setUser_first_name(data.get("user_first_name"));
		user.setUser_last_name(data.get("user_last_name"));
		user.setUser_contact_number(data.get("user_contact_number"));
		user.setUser_email_id(data.get("user_email_id"));
        user.setUserAddress(userAddress);
        userAddress.setPlotNumber(data.get("plotNumber"));
        userAddress.setStreet(data.get("street"));
        userAddress.setState(data.get("state"));
        userAddress.setCountry(data.get("country"));
        userAddress.setZipCode(data.get("zipCode"));
        return user;
    }
    
    public User postOnlyMandatoryField()  {
        user = new User();
        user.setUser_first_name("kikiki");
        user.setUser_last_name("sek");
        user.setUser_contact_number("9842455678");
        user.setUser_email_id("abghr@gmail.com");
        user.setUserAddress(new User_address());
        return user;
    }

    public User putBody(LinkedHashMap<String, String> data) {
        user = new User();
        userAddress = new User_address();
        user.setUser_first_name(data.get("user_first_name"));
		user.setUser_last_name(data.get("user_last_name"));
		user.setUser_contact_number(data.get("user_contact_number"));
		user.setUser_email_id(data.get("user_email_id"));
        user.setUserAddress(userAddress);
        userAddress.setPlotNumber(data.get("plotNumber"));
        userAddress.setStreet(data.get("street"));
        userAddress.setState(data.get("state"));
        userAddress.setCountry(data.get("country"));
        userAddress.setZipCode(data.get("zipCode"));
        return user;
    }

    public String deleteBody() {
        return "";
    }
    public String postBody(User user) throws Exception {
        // Convert the User object to a JSON string
        return objectMapper.writeValueAsString(user);
    }
    public static List<LinkedHashMap<String, String>> getUserData(String sheetName) {
        return ExcelReader.getExcelDataAsListOfMap(sheetName);
    }
}
