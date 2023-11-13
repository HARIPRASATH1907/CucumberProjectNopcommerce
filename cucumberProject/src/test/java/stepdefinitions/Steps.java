package stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	@Before                          //hook concept from cucumber ;;called before hook
	public void setup() throws IOException {
		
		configprop=new Properties();  ///Reading properties
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configprop.load(configPropfile);
		
		logger=Logger.getLogger("nopcommerce"); //Added logger
		PropertyConfigurator.configure("Log4j.properties");
		
		String br=configprop.getProperty("browser");
		
	if(br.equals("chrome")) {	
	System.setProperty("webdriver.chrome.driver",configprop.getProperty("chromepath"));//System.getProperty( "user.dir")+"//Drivers/chromedriver.exe");
	driver=new ChromeDriver();
	}
	if(br.equals("firefox")) {	
	System.setProperty("webdriver.gecko.driver",configprop.getProperty("firefoxpath"));//System.getProperty( "user.dir")+"//Drivers/chromedriver.exe");
	driver=new FirefoxDriver();
	}
	else if(br.equals("ie")) {
	System.setProperty("webdriver.ie.driver",configprop.getProperty("iepath"));//System.getProperty( "user.dir")+"//Drivers/chromedriver.exe");
	driver=new InternetExplorerDriver();
	}
		
	logger.info("********* Launching URL*********");
	}
	
	
	//Login Feature SD
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {	
	lp=new LoginPage(driver);
	}
	
	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
	logger.info("********* Opening URL*********");
	driver.get(url);
	}
	
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
	logger.info("********* Providing login details *********");
	lp.setUserName(email);
	lp.setPassword(password);
	}
	
	@When("Click on Login")
	public void Click_on_Login() {
	logger.info("********* Started Login *********");
	lp.clickLogin();
	}
	
	@Then("Page Title should be {string}")
	public void Page_Title_should_be(String title) {
	if(driver.getPageSource().contains("Login was unsuccessful.")) {
	driver.close() ;
	logger.info("********* Login Passed *********");
	Assert.assertTrue(false) ;
	} 
	else{
	logger.info("********* Login Failed *********");
	Assert.assertEquals(title, driver.getTitle());
	}	
	}
	
	@When("User click on Logout link")
	public void user_click_on_Log_out_link() throws InterruptedException {
	logger.info("********* Click on logout link *********");
	lp.clickLogout () ;
	Thread.sleep(3000);
    }

	@Then("close browser")
	public void close_browser () {
	logger.info("********* Closing Browser *********");
	driver.quit() ;
	}
	
	
	
	//Customer feature step definitions...
	 
    @Then("User can view Dashboard")
	public void user_can_view_dashboard() { 
	addCust=new AddCustomerPage(driver);
	Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}
	  
    @When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	Thread.sleep(3000);
	addCust.clickonCustomersMenu();
    }
	  
	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
	Thread.sleep(2000);
	addCust.clickonCustomersMenuItem();
	}
	  
	@And("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
	addCust.clickonAddnew();
	Thread.sleep(2000); 
	}
	  
	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
	Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
	}
	  
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	     logger.info("********* Adding New Customer *********");
		 logger.info("********* Providing Customer Details *********");
		 String email=randomstring()+"@gmail.com";
		 addCust.setEmail(email);
         addCust.setPassword("test123");
// Registered default
// The customer cannot be in both 'Guests' and 'Registered' customer roles
//Add the customer to 'Guests' or 'Registered' customer role
         addCust.setCustomerRoles("Guests");
         Thread.sleep(3000);

         addCust.setManagerofVendor("Vendor 2");
         addCust.setGender("Male");
         addCust.setFirstName("Hari");
         addCust. setLastName("Prasath");
         addCust.setDob("7/19/2000"); // Format: D/MM/YYYY
         addCust.setCompanyName("busyQA"); 
         addCust.setAdminContent("This is for testing...");

	  }
	 
   @When("click on Save button")
   public void click_on_Save_button() throws InterruptedException {
   logger.info("********* Saving Customer Data *********");
   addCust.clickonSave();
   Thread.sleep(2000);
   }
	  
   @Then("User can view confirmation message {string}")
   public void user_can_view_confirmation_message(String msg) {
   Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
   .contains("The new customer has been added successfully."));
   }
	  
	  
//Steps for searching a customer using Email Id .....
	 
   @And("Enter customer Email")
   public void enter_customer_Email() {
   logger.info("********* Searching a Customer By Email ID*********");
   searchCust=new SearchCustomerPage(driver);
   searchCust.setEmail("victoria_victoria@nopCommerce.com");
   }
	  
   @When("Click on search button")
   public void click_on_search_button() throws InterruptedException {
   searchCust.clicksearch();
   Thread.sleep(3000);
   }
	  
   @Then("User should found Email in the Search table")
   public void user_should_found_Email_in_the_Search_table() { 
   boolean status= searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
   Assert.assertEquals(true,status);
   }
	  
	  
//Steps for searching a customer using FirstName & LastName .....
	  
   @When("Enter customer FirstName")
   public void enter_customer_FirstName() {
   logger.info("********* Searching Customer By Name *********");
   searchCust=new SearchCustomerPage(driver);
   searchCust.setFirstName("Victoria");
   }
	  
   @When("Enter customer LirstName")
   public void enter_customer_LastName() {
   searchCust=new SearchCustomerPage(driver);
   searchCust.setLastName("Terces");
   }
	  
   @Then("User should found Name in the Search table")
   public void user_should_found_Name_in_the_Search_table() {
   boolean status= searchCust.searchCustomerByName("Victoria Terces");
   Assert.assertEquals(true,status);	  
   }
	  
	
}
