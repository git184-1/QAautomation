Feature: cricbuzz
  
  @ignore
  Scenario: Check live score
    Given cricbuzz live score webpage is open
    Then check for live scores displayed