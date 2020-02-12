package StepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;


public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException 
	{
		//adding logger
		logger = Logger.getLogger("nopCommerseV001_cucumber");
		PropertyConfigurator.configure("Log4j.properties");
				
		
		//Reading properties file
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.propreties");
		configProp.load(configPropFile);
				
		String br = configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chrome_path"));
			driver= new ChromeDriver();
			logger.info("*****  launching Chrome Browser  *****");
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefox_path"));
			driver = new FirefoxDriver();
			logger.info("*****  launching Firefox Browser  *****");
		}
		else if(br.equals("safari"))
		{
			driver = new SafariDriver();
			logger.info("*****  launching safari Browser  *****");
		}
		
		
	}

	
	
	@Given("user launch chrome browser")
	public void user_launch_chrome_browser() {  
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		lp = new LoginPage(driver);
		
	}

	@When("user opens url {string}")
	public void user_opens_url(String url) {
		logger.info("*****  Launching URL  *****");
		driver.get(url);
	   	
	}

	@And("user enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {  
		logger.info("*****  Enter login emaiid & password  *****");
		lp.setUsername(email);
		lp.setPassword(password);
		
	}

	@Then("click on login")
	public void click_on_login() {
		logger.info("*****  Click On login button  *****");
	    lp.click_login();
	}

	@And("page Title should be {string}")
	public void page_Title_should_be(String title) {
	   
	if (driver.getPageSource().contains("Login was unsuccessfull")) {
		driver.close();
		Assert.assertTrue(false);
	} else {
		Assert.assertEquals(title,driver.getTitle());
	}
	}

	@When("user click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("*****  click on logout link  *****");
		lp.click_logout();
		
	}

	@And("close browser")
	public void close_browser() {
		logger.info("*****  Close the Browser  *****");
		driver.quit();
	    
	}	
	
	
	//Add customer feature steps
	
	@Then("user can view Dashboard")
	public void user_can_view_Dashboard() {
	   addcust = new AddCustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());
	   
	   logger.info("*****  Add new customer   *****");
	}

	@When("user can click on customers menu")
	public void user_can_click_on_customers_menu() {
		addcust.clickOnCustomersMenu();
	}

	@When("click on customer menu item")
	public void click_on_customer_menu_item() {
		addcust.clickOnCustomeritem();
	}

	@Then("user can click on Addnew button")
	public void user_can_click_on_Addnew_button() {
		addcust.clikOnAddnewBtn();	
	}

	@Then("user can view add a new customer page")
	public void user_can_view_add_a_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());
		
	}

	@Then("user enters customer info")
	public void user_enters_customer_info() throws InterruptedException {
	   String email = randomstring()+"@gmail.com";
	   
	   addcust.enterEmail(email);
	   addcust.setPassword("Test@123");
	   addcust.enterFirstName("Raj");
	   addcust.enterLastName("king");
	   addcust.setGender("Male");
	   addcust.DOB("7/29/1990");
	   addcust.enterCompany_name("IBM");
	   addcust.Is_tax_info();
	   addcust.Newsletter("your store name");
	   addcust.setCustormerRole("Administrators");
	   addcust.mangerOfVendorId();
	   
	   
	}

	@When("click on save button")
	public void click_on_save_button() {
	    
		addcust.clickOnSavebtn();
	}

	@Then("user can view conformation message {string}")
	public void user_can_view_conformation_message(String string) {
	   Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
		
	}
	
	//Steps for search customer using email & name
	
	@When("enter customer email")
	public void enter_customer_email() {
		logger.info("*****  Search customer by email id  *****");
		
		searchcust = new SearchCustomer(driver);
		searchcust.enter_email("victoria_victoria@nopCommerce.com");
	}

	@When("click on serach button")
	public void click_on_serach_button() throws InterruptedException {
		searchcust.click_searchbtn(); 
		Thread.sleep(3000);
	}

	@Then("user should found customer email in search field")
	public void user_should_found_customer_email_in_search_field() {
		boolean status = searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com"); 
		Assert.assertEquals(true, status);
	}
	//Search customer by using firstname and lastname
	
	@When("enter customer firstname")
	public void enter_customer_firstname() {
		logger.info("*****  Search customer by firstname & lastname  *****");
		
		searchcust = new SearchCustomer(driver);
		searchcust.enter_firstname("Victoria");
	}

	@When("enter customer lastname")
	public void enter_customer_lastname() {
	   searchcust.enter_lastname("Terces");
	}

	@Then("user should found customer name in search field")
	public void user_should_found_customer_name_in_search_field() {
		boolean status= searchcust.searchCustomerByname("Victoria Terces");
		Assert.assertEquals(true, status);
	}
	
}
