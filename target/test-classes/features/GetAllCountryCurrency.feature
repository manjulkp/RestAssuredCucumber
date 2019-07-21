Feature: GETAllCurrencyRates
  Verify different GET operations using REST-assured

  Scenario: Verify get currency rates for base EUR
    Given I perform GET operation for "/api/latest" with body
    | access_key              | format  |
    | f5aee28dd044b8093ead9f1b32074632 | 1 |
    Then the response status is 200
    And I should see the base  is  "EUR"
    Then I should see the response carrying a number of common world currencies
    
  Scenario: Verify get 10 random Currencies and store the key and value into a CSV file   
    Given I perform GET operation for "/api/latest?access_key=f5aee28dd044b8093ead9f1b32074632&format=1&symbols=GBP,JPY,EUR,AOA,ARS,AUD,AWG,BAM,USD,CHF"
    Then the response status has current date in the response
    And I should see the response carrying "10" world currencies
    And the response is stored in CSV
    
    
  Scenario: Verify get 10 random Currencies by paramterizing 
    Given I perform GET operation for "/api/latest" with with countries
    | access_key              | format  | symbols|
    | f5aee28dd044b8093ead9f1b32074632 | 1 | GBP,JPY,EUR,AOA,ARS,AUD,AWG,BAM,USD,CHF,CNY |
    Then the response status has current date in the response
    And I should see the response carrying "10" world currencies
    And the response is stored in CSV
   