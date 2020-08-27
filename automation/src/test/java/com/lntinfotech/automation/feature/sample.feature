Feature: Sample
 
  @ignore
  Scenario: Switch Frame
    Given website is open 
    When user should be able to switch frame
    Then get alert text and accept it
  
  @ignore
  Scenario: perform right click
  	Given website is open for right click sample
  	When right click button is visible
  	Then perform right click and select "copy"
  	
  	Given Alert box is displayed
  	When assert alert text to contain "copy"
  	Then accept alert
  
  @ignore
  Scenario: Select radio button based on given text
  	Given Website is open for radio button sample
  	Then select radio button for "female"
  
  @ignore	
  Scenario: Scroll page
  	Given Website is open for scrollTest
  	Then scroll to the bottom
  	
  @ignore	
  Scenario: Take full page length screenshot
  	Given Website is loaded completely
  	Then screenshot entire page	  
  	
  @ignore
  Scenario: Switch tab
  	Given Website is open for tabswitch
  	When user click on button and print new tab content
  	Then switch back to parent tab		 	