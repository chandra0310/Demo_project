package Library;
import io.restassured.response.Response;

public class APIResponseHandler {

    public APIResponseHandler() {
    }

    private Response apiResponse;

    public Response getApiResponse() {
        return apiResponse;
    }
    public void setApiResponse(Response apiResponse) {
        this.apiResponse = apiResponse;
    }
}
