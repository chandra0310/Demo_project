package stepDefinitions;

import Library.APIResponseHandler;
import Library.HelperService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;


public class AllinOneSteps {
	private String scenDesc;

    private final APIResponseHandler apiResponseHandler = new APIResponseHandler();
    public static Properties prop = new Properties();
    public String str = null;

    @Before
	public void before(Scenario scenario) {
		this.scenDesc = scenario.getName();
        try {
            prop.load( new FileInputStream("./src/test/properties/application.properties") );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @After
    public void after(Scenario scenario){
    	//To do - not needed for now
    }
	
	@BeforeStep
	public void beforeStep() throws InterruptedException {
		Thread.sleep(2000);
	}

   @Given("^I use the post method to add item '(.+)' to the Todo List App$")
    public void iPostItemUsingAPI(String desc) {
       apiResponseHandler.setApiResponse(HelperService.postItemfromAPI(prop.getProperty("apiUrl"),desc));
    }

    @When("^I verify the status code after posting the item$")
    public void iValidatePostMethod() {
        Integer statusCode = apiResponseHandler.getApiResponse().getStatusCode();
        if (statusCode == 201) {
            str = apiResponseHandler.getApiResponse().header("Location").toString().substring(36);
        } else if (statusCode == 409) {
            Assert.fail(scenDesc + " - A Todo item with description already exists, please ensure that the description is unique.\n");
        } else {
            Assert.fail(scenDesc + " - There was an error posting the item.\n");
        }
    }

    @Then("^I validate that item has been added to the Todo List App using get method$")
    public void iGetItemDetailsUsingAPI() {
        boolean isEmpty = str == null || str.trim().length() == 0;
        if (isEmpty) {
            Assert.fail(scenDesc + " - There was an issue posting the item to the Todo List App. This step will not be executed.\n");
        }
        else {
            apiResponseHandler.setApiResponse(HelperService.getItemfromAPI(prop.getProperty("apiUrl"), str));
            int statusCode = apiResponseHandler.getApiResponse().getStatusCode();
            if (statusCode != 200) {
                Assert.fail(scenDesc + " - There was an error using the Get request from the API.\n");
            } else {
                System.out.print(scenDesc + " - The item that was added to the Todo List App has been validated successfully.\n");
            }
        }
    }
}
