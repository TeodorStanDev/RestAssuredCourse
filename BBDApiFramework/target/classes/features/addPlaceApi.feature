Feature: Add Place API validation

  @AddPlace
  Scenario Outline: Place is added successfully using Add Place API
    Given Add Place Payload with: "<name>", "<language>", "<address>"
    When user sends HTTP "POST" request to "AddPlaceAPI"
    Then HTTP response status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify that place_id was created to "<name>" using "GetPlaceAPI" with HTTP method "GET"

    Examples:
      | name    | language | address            |
      | Teo     | Ro-En    | PlaceholderCity    |
#      | Andreea | Ro-Es    | PlaceholderVillage |

  @DeletePlace
  Scenario: Verify Delete Place functionality
    Given "DeletePlaceAPI" payload
    When user sends HTTP "POST" request to "DeletePlaceAPI"
    Then HTTP response status code is 200
    And "status" in response body is "OK"