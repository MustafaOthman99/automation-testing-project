package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;

public class JsonDataProvider {

    private static JsonNode root;

    private static JsonNode getRoot() {
        if (root == null) {
            try (InputStream input = JsonDataProvider.class.getClassLoader()
                    .getResourceAsStream("testdata.json")) {
                if (input == null) {
                    throw new RuntimeException("testdata.json not found in src/test/resources");
                }
                root = new ObjectMapper().readTree(input);
            } catch (IOException e) {
                throw new RuntimeException("Unable to read testdata.json", e);
            }
        }
        return root;
    }

    public static String validUsername() {
        return getRoot().get("validLogin").get("username").asText();
    }

    public static String validPassword() {
        return getRoot().get("validLogin").get("password").asText();
    }

    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        JsonNode node = getRoot().get("validLogin");
        return new Object[][]{
                {node.get("username").asText(), node.get("password").asText()}
        };
    }

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
