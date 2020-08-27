package com.lntinfotech.automation.stepdefs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lti.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NgSample {

	Action action;

	@Given("^searchpage is open in chrome$")
	public void searchpage_is_open_in_chrome() throws Exception {
		action = new Action("tc_sample", 10);
		action.perform(Action.OpenURL, "filterUrl");
	}

	@When("^\"([^\"]*)\" is passed to search box$")
	public void is_passed_to_search_box(String searchText) throws Exception {
		action.perform(Action.SendKeys,"filterBox","filterText");
	}

	@SuppressWarnings("unchecked")
	@Then("^Print table details$")
	public void print_table_details() throws Exception {
		List<WebElement> rows = (List<WebElement>) action.get(Action.GetElements, "ngTableRow");
	
		 System.out.println("Company Details : ");	
		 for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			System.out.println("Name : "+cols.get(0).getText());
			System.out.println("Empl : "+cols.get(1).getText());
			System.out.println("Head : "+cols.get(2).getText());
			System.out.println();
		}
		 action.perform(Action.CloseBrowser);  
	}
	
	
	@Given("^website is open and tables are shown$")
	public void website_is_open_and_tables_are_shown() throws Exception {
		
		action = new Action("tc_itime", 10);
		action.perform(Action.OpenURL,"url");
		action.perform(Action.CaptureScreenshot);
	}

	@When("^new records are added to table$")
	public void new_records_are_added_to_table() throws Exception {
		WebElement heading = (WebElement) action.get(Action.GetElement, "formHeading");
		
		System.out.println(heading.getText());
		
		
		action.perform(Action.SendKeys, "ngName", "name");
		
		action.perform(Action.SendKeys, "ngEmployees", "employees");
		
		action.perform(Action.SendKeys, "ngHeadoffice", "headoffice");
		
		action.perform(Action.Click, "submitButton");

	}

	@Then("^fetch all details from table$")
	public void fetch_all_details_from_table() throws Exception {
		@SuppressWarnings("unchecked")
		List<WebElement> rows = (List<WebElement>) action.get(Action.GetElements, "ngTableRow");
		
		 for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				System.out.print("---"+col.getText());
			} 
			System.out.println();
		}
		 
		action.perform(Action.CloseBrowser);
		
	}
}
