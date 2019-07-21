# RestAssuredCucumber

1.The test cases are written in BDD format
2. Happy Flow are check 
- status code of response 
- Base key is checked in response 
- Basic Verification for rates array object is done for 10 countries 
- General Utility Methods are written under RestAssuredExtension
- Used the AAA pattern with REST-assured tests
-//Arrange, //Act and //Assert
-The output response of 10 countries are stored in CSV under 
/restassured/src/test/java/resource/OutputInCSV.csv

The Report plug is not added ,need time to add .

The output on the console is like :

Feature: GETAllCurrencyRates
  Verify different GET operations using REST-assured

  Scenario: Verify get currency rates for base EUR                              # /Users/purushi1.macbook/eclipse-workspace/restassured/src/test/java/features/GetAllCountryCurrency.feature:4
    Given I perform GET operation for "/api/latest" with body                   # GETSteps.i_perform_GET_operation_for_with_body(String,DataTable)
    Then the response status is 200                                             # GETSteps.the_response_status_is(int)
    And I should see the base  is  "EUR"                                        # GETSteps.i_should_see_the_base_is(String)
    Then I should see the response carrying a number of common world currencies # GETSteps.i_should_see_the_response_carrying_a_number_of_common_world_currencies()
10

  Scenario: Verify get 10 random Currencies and store the key and value into a CSV file                                                                  # /Users/purushi1.macbook/eclipse-workspace/restassured/src/test/java/features/GetAllCountryCurrency.feature:12
    Given I perform GET operation for "/api/latest?access_key=f5aee28dd044b8093ead9f1b32074632&format=1&symbols=GBP,JPY,EUR,AOA,ARS,AUD,AWG,BAM,USD,CHF" # GETSteps.iPerformGETOperationFor(String)
    Then the response status has current date in the response                                                                                            # GETSteps.the_response_status_has_current_date_in_the_response()
    And I should see the response carrying "10" world currencies                                                                                         # GETSteps.i_should_see_the_response_carrying_world_currencies(String)
    And the response is stored in CSV                                                                                                                    # GETSteps.the_response_is_stored_in_CSV()
GBP,JPY,EUR,AOA,ARS,AUD,AWG,BAM,USD,CHF,CNY
10

  Scenario: Verify get 10 random Currencies by paramterizing            # /Users/purushi1.macbook/eclipse-workspace/restassured/src/test/java/features/GetAllCountryCurrency.feature:19
    Given I perform GET operation for "/api/latest" with with countries # POSTCountriesSteps.i_perform_GET_operation_for_with_with_countries(String,DataTable)
    Then the response status has current date in the response           # GETSteps.the_response_status_has_current_date_in_the_response()
    And I should see the response carrying "10" world currencies        # GETSteps.i_should_see_the_response_carrying_world_currencies(String)
    And the response is stored in CSV                                   # GETSteps.the_response_is_stored_in_CSV()

3 Scenarios (3 passed)
12 Steps (12 passed)
0m4.993s


