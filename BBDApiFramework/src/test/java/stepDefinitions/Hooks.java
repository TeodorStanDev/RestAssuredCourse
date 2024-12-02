package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeleteApiScenario() throws IOException {

        StepDefinition stepDefinition = new StepDefinition();
        if (StepDefinition.place_id == null) {
            stepDefinition.addPlacePayloadWith("Thomas", "Spanish", "Uverturii");
            stepDefinition.userSendsHTTPRequestTo("POST", "AddPlaceAPI");
            stepDefinition.http_response_status_code_is(200);
        }
    }
}
