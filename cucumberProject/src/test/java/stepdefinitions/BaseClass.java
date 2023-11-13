package stepdefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;
import java.util.Properties;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    public static Logger logger;
    public Properties configprop;
    
//Created for generating random string for unique email id
public static String randomstring() {
	String generatedString1=RandomStringUtils.randomAlphabetic(5);
	return (generatedString1);  //RandomStringUtils is predefined class creates 5 character random string 
}






}
