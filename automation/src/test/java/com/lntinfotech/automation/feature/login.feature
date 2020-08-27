Feature: Login

  Scenario: Login to plato 
    Given Plato is open in chrome browser
    When username and password is given
    Then user should see dashboard
