
package requests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import payloads.payload;
import payloads.EnumClass.SheetName;
import utils.ExcelReader;
import utils.configReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public abstract class BaseTest {

    protected RequestSpecification request;
    private Properties prop;
    protected List<LinkedHashMap<String, String>> userData; 
    private final String Path = "\\src\\test\resources\\TestData\\ExcelRequests.xlsx";

    @BeforeClass
    public void setup() {
        prop = configReader.initprop();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String user = prop.getProperty("username");
        String pass = prop.getProperty("password");
        RestAssured.authentication = RestAssured.basic(user, pass);
        userData = loadUserData(getSheetName());
    } 
    protected abstract String getSheetName();
    private List<LinkedHashMap<String, String>> loadUserData(String sheetName) {
      return payload.getUserData(sheetName);
  }
    

   

   @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        List<LinkedHashMap<String, String>> filteredData = userData.stream()
            .filter(data -> data.get("endpoint") != null && !data.get("endpoint").isEmpty())
            .collect(Collectors.toList());

        Object[][] data = new Object[filteredData.size()][1];
        for (int i = 0; i < filteredData.size(); i++) {
            data[i][0] = filteredData.get(i);
        }
        return data;
    }
}

