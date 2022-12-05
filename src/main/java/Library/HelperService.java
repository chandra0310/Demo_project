package Library;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class HelperService {
    public static Response getItemfromAPI(String baseURI, String id) {
        RestAssured.baseURI = baseURI + "/" + id;
        RequestSpecification request = RestAssured.given();
        Response response = request.get();
        return response;
    }

    public static Response postItemfromAPI(String baseURI, String desc) {
        RestAssured.baseURI = baseURI;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        Response response = request.body("{ \"description\":\"" + desc + "\"}")
                            .post();
        return response;
    }
}
