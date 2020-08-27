package com.lntinfotech.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.lti.browser.Browser;
import com.lti.database.DBrowTO;
import com.lti.database.SQLDriver;
import com.lti.element.Element;
import com.lti.keywords.CaptureScreenshot;
import com.lti.keywords.CheckBrokenLinks;
import com.lti.report.Report;

public class Action {

	final static Logger logger = Logger.getLogger(Action.class);
	
	String TCName;
	String browserName;
	String driverName;
	Browser browser;
	WebDriver driver;
	Element element;
	static Properties prop = ConfigureLibrary.getProp();
	List<DBrowTO> data;
	int timeout;
	Report report;
	HashMap<String, Object> params;
	
	
	//C
	public static final String CaptureScreenshot  = "capturescreenshot";
	public static final String CheckBrokenLinks   = "checkbrokenlinks";
	public static final String CheckPagetitle	  = "checkpagetitle";
	public static final String Click			  = "click";
	public static final String ClickRadiobutton	  = "clickradiobutton";
	public static final String CloseBrowser  	  = "closebrowser";
	
	//D
	public static final String Driver			  = "driver";	
	
	//F
	public static final String FullPageScreenshot = "fullpagescreenshot";
	public static final String FromOutparam 	  = "fromoutparam";
	
	//G
	public static final String GetElement		  = "getelement";
	public static final String GetElements		  = "getelements";
	public static final String GetPagetitle		  = "getpagetitle";
	
	//I
	public static final String ImplicitWait		  = "ImplicitWait";
	
	//O
	public static final String OpenURL			  = "openurl";
	
	//S
	public static final String SaveInDB			  = "saveindb";
	public static final String ScrollToBottom	  = "scrolltobottom";
	public static final String ScrollByPixel	  = "scrollbypixel";
	public static final String ScrollToElement	  = "scrolltoelement";
	public static final String SendKeys			  =	"sendkeys";
	public static final String SwitchTab		  = "SwitchTab";
	public static final String SwitchFrameByName  = "switchframebyname";
	public static final String SwitchFrameByNumber= "switchframebynumber";

		

	/**
	 *  
	 * @param TCName Name of DB table from where to get all data corresponding to given script.
	 * @param timeout threshold time for WebElement related operation to complete in secs.
	 */
	public Action(String TCName, int timeout) {
		this.TCName			=	TCName;
		this.browserName	= 	prop.getProperty("browser");
		this.driverName 	= 	prop.getProperty("browserDriver");
		this.browser	    = 	new Browser(browserName, driverName);
		this.driver 		= 	browser.getDriver();
		this.element 		= 	new Element(driver);
		this.data 			= 	SQLDriver.getData(TCName);
		this.timeout 		= 	timeout;
		this.report			=	new Report();
		this.params			=	new HashMap<String, Object>();
		
		logger.info("Initialization of Action class object");
	}
	
	
	/**
	 * When there are no other parameters to the operation 
	 * @param operation name of action to be performed.
	 * 
	 */
	public void perform(String operation) {
		
		String value = null;
		String valueType = null;
		String data = null;
		String status = "fail";
		
		switch (operation) {
			
			case CaptureScreenshot
										:	params.put("TCName", TCName);
											params.put("folderpath", prop.getProperty("outputFolder")+"/Images");
											CaptureScreenshot cs = new CaptureScreenshot();
											if(cs.execute(params)){
												status = "pass";
												value = cs.getFilepath();
											}											
											break;
											
			case CheckBrokenLinks		:   try {
												Thread.sleep(5000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											params.put("driver", driver);
											CheckBrokenLinks cbl = new CheckBrokenLinks();
											if(cbl.execute(params)){
												status = "pass";
											}
											break;
		
			case CloseBrowser			:	driver.close();
											browser.close(driverName);
											status = "pass";
											break;
											
			case ImplicitWait			:	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
											status = "pass";
											break;
											
			case FullPageScreenshot 	:	String path = prop.getProperty("outputFolder")+"/Images";
											Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME,  true).withName(this.TCName+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())).save(path);;
											status = "pass";
											break;								
											
			case ScrollToBottom			:	JavascriptExecutor js = (JavascriptExecutor) driver;
											js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
											status = "pass";
											break;
											
			default						:	logger.error("Operation \""+operation+"\" is not defined!");	
											break;
		}
		
		report.print(operation, value, valueType, data, status);		
		params.clear();
	}
	
	
	/**
	 * Performs operation with 1 parameter as input 
	 * @param operation name of action to be performed.
	 * @param dataKey data to be passed as parameter. Either directly pass data or add "_fromDB" to the end of data to fetch it from DB.
	 * 
	 */
	public void perform(String operation, String dbKey) {
		
		String value = getValue(dbKey);
		String valueType = getValueType(dbKey);
		String data = null;
		String status = "fail";
		JavascriptExecutor js;

		switch (operation) {
			case CheckPagetitle
										:	if(driver.getTitle().equalsIgnoreCase(value)){
												status = "pass";
											}
											break;
											
			case Click   				:	element.getElement(valueType, value, this.timeout).click();
											status = "pass";
											break;
											
			case ClickRadiobutton
										:	List<WebElement> radioList = element.getElements("cssselector", "input[type=\"radio\"]", this.timeout);
										    for (WebElement radio : radioList) {
												if(radio.getAttribute("value").equalsIgnoreCase(value)){
													radio.click();
													status = "pass";
													break;
												}
											}
										    break;
											
			case OpenURL				:	driver.get(value);
											status = "pass";
											break;
											
			case ScrollByPixel
										:	js = (JavascriptExecutor) driver;
											js.executeScript("window.scrollBy(0,"+value+")");
											status = "pass";
											break;
			
			case ScrollToElement
										:	js = (JavascriptExecutor) driver;
											WebElement e = element.getElement(valueType, value, this.timeout);
											js.executeScript("arguments[0].scrollIntoView();", e);
											status = "pass";
											break;
			
			case SwitchFrameByName	
										:	driver.switchTo().frame(value);
											status = "pass";
											break;
			
			case SwitchFrameByNumber
										:	driver.switchTo().frame(Integer.parseInt(value));
											status = "pass";
											break;
											
			default						:	logger.error("Operation \""+operation+"\" is not defined!");
											break;
		}
		
		report.print(operation, value, valueType, data, status);
		params.clear();
		
	}


	public void perform(String operation, String byKey, String dataKey) {
		
		String byValue = getValue(byKey);
		String byValueType = getValueType(byKey);
		String dataValue = getValue(dataKey);
		String status = "fail";
		
		switch (operation) {
			case SendKeys  					:	WebElement e = element.getElement(byValueType, byValue, this.timeout);
												e.clear();
												e.sendKeys(dataValue);
												status = "pass";
												break;
												
			case SaveInDB					:	if(SQLDriver.saveToDB(byKey, dataKey)){
													status = "pass";
												}
												break;
												
			default							:	logger.error("Operation \""+operation+"\" is not defined!");
												break;
		}
		
		report.print(operation, byValue, byValueType, dataValue, status);
		params.clear();
	}

	
	public Object get(String operation) {
		String status = "fail";
		Object value = null;
		
		switch (operation.toLowerCase()) {
			case Driver				:	value = driver;
												break;
												
			case GetPagetitle		:	value = driver.getTitle();
												break;
												
			default							:	if(getValue(operation) != null){
													value = getValue(operation);
												}else{
													logger.error("Operation \""+operation+"\" is not defined!");
												}
												
												break;
		}
		
		if(value != null){
			status = "pass";
		}
		
		report.print("GET "+operation, null, null, null, status);
		params.clear();
		return value;
	}
	
	public Object get(String operation, String byKey) {
		String status = "fail";
		Object value = null;
		String byValueType = getValueType(byKey);
		String byValue = getValue(byKey);
		
		switch (operation.toLowerCase()) {
			case GetElement			:	value = element.getElement(byValueType, byValue, this.timeout);
										break;
												
			case GetElements		:	value = element.getElements(byValueType, byValue, this.timeout);
										break;
			
			case FromOutparam		:	value = SQLDriver.getFromOutparam(byKey);
										break;	
												
			default					:	logger.error("Operation \""+operation+"\" is not defined!");
										break;
		}
		
		if(value != null){
			status = "pass";
		}
		
		report.print("GET "+operation, null, null, null, status);
		params.clear();
		return value;
	}
	
	
	private String getValue(String dbKey){
		logger.info("checking for value of "+dbKey+" in database.");
		for (DBrowTO temp : data) {
			if(temp.getKey().equalsIgnoreCase(dbKey)){
				logger.info("Returning value of "+dbKey+" as "+temp.getValue());
				return temp.getValue(); 
			}
		}
		logger.info("Returning value of "+dbKey+" as "+dbKey);
		return dbKey;			
	}
	
	
	
	private String getValueType(String dataKey){
		logger.info("checking for valueType of "+dataKey+" in database.");
		for (DBrowTO temp : data) {
			if(temp.getKey().equalsIgnoreCase(dataKey)){
				logger.info("Returning valueType of "+dataKey+" as "+temp.getValueType());
				return temp.getValueType();
			}
		}
		logger.info("Returning value of "+dataKey+" as 'user defined'");
		return "user defined";
	}

}
