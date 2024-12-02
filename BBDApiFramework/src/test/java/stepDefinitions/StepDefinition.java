package stepDefinitions;

import helper.ApiResources;
import helper.TestDataBuilder;
import helper.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    RequestSpecification requestSpecification;
    ResponseSpecification respSpec;
    Response response;
    static String place_id;

    @Given("Add Place Payload with: {string}, {string}, {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        requestSpecification = given().spec(requestSpecification()).body(TestDataBuilder.getAddPlacePayload(name, language, address));
        respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user sends HTTP {string} request to {string}")
    public void userSendsHTTPRequestTo(String httpMethod , String resource) throws IOException {

        ApiResources resourceApi = ApiResources.valueOf(resource);

        if (httpMethod.equalsIgnoreCase("POST") || httpMethod.equalsIgnoreCase("DELETE")){
            System.out.println(getProperty("baseUrl") + resourceApi.getResource());
            response = requestSpecification.when().post(resourceApi.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("GET"))
            response = requestSpecification.when().get(resourceApi.getResource());
        else if (httpMethod.equalsIgnoreCase("PUT"))
            response = requestSpecification.when().put(resourceApi.getResource());
    }

    @Then("HTTP response status code is {int}")
    public void http_response_status_code_is(int int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(int1, response.getStatusCode());

        place_id = getJsonPath(response, "place_id");
        System.out.println("Place id is " + place_id);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String arg0, String arg1) {
        String status = getJsonPath(response, arg0);
        assertEquals(status, arg1);
    }

    @And("verify that place_id was created to {string} using {string} with HTTP method {string}")
    public void verifyThatPlace_idWasCreatedToUsing(String expectedName, String resource, String httpMethod) throws IOException {

        String place_id = getJsonPath(response, "place_id");
        requestSpecification = given().spec(requestSpecification()).queryParams("place_id", place_id);
        userSendsHTTPRequestTo(httpMethod, resource);
        String name  = getJsonPath(response, "name");
        assertEquals(name, expectedName);
    }

    @Given("{string} payload")
    public void deleteplaceapiPayload(String resource) throws IOException {

        requestSpecification = given().spec(requestSpecification()).body(TestDataBuilder.deletePlacePayload(place_id));
    }
}
