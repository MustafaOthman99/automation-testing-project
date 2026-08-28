package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

/**
 * Reads src/test/resources/testdata.json using Jackson and exposes it
 * to @Test methods through TestNG @DataProvider, instead of hardcoding
 * usernames/passwords/employee names inside the tests.
 */
public class JsonDataProvider {

    private static final String DATA_PATH = "src/test/resources/testdata.json";
    private static JsonNode root;

    private static JsonNode getRoot() {
        if (root == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                root = mapper.readTree(new File(DATA_PATH));
            } catch (IOException e) {
                throw new RuntimeException("Unable to read testdata.json", e);
            }
        }
        return root;
    }

    /** username/password for the valid-login test */
    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        JsonNode node = getRoot().get("validLogin");
        return new Object[][]{
                {node.get("username").asText(), node.get("password").asText()}
        };
    }

    /** one or more invalid username/password combinations */
    @DataProvider(name = "invalidLogins")
    public static Object[][] invalidLogins() {
        JsonNode arr = getRoot().get("invalidLogins");
        Object[][] data = new Object[arr.size()][2];
        for (int i = 0; i < arr.size(); i++) {
            data[i][0] = arr.get(i).get("username").asText();
            data[i][1] = arr.get(i).get("password").asText();
        }
        return data;
    }

    @DataProvider(name = "existingEmployee")
    public static Object[][] existingEmployee() {
        return new Object[][]{
                {getRoot().get("employeeSearch").get("existingEmployeeName").asText()}
        };
    }

    @DataProvider(name = "nonExistingEmployee")
    public static Object[][] nonExistingEmployee() {
        return new Object[][]{
                {getRoot().get("employeeSearch").get("nonExistingEmployeeName").asText()}
        };
    }

    @DataProvider(name = "newEmployee")
    public static Object[][] newEmployee() {
        JsonNode node = getRoot().get("newEmployee");
        return new Object[][]{
                {node.get("firstName").asText(), node.get("lastName").asText()}
        };
    }
}
