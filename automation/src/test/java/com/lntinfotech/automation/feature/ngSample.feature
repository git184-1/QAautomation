Feature: NG Sample

  @ignore
  Scenario: Filter Example
  Given searchpage is open in chrome
  When "Technologies" is passed to search box
  Then Print table details
  
  @ignore	
  Scenario: Add entry to table and fetch table 
    Given website is open and tables are shown
    When new records are added to table
    Then fetch all details from table