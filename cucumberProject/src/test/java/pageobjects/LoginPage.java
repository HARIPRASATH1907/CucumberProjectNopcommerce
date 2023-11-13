package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public  WebDriver ldriver;

	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//input[@type='email']")
	@CacheLookup
	public WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='Password']")
	@CacheLookup
	public WebElement txtPassword;

	@FindBy(xpath="//button[contains(text(),'Log in')]")
	@CacheLookup
	public WebElement btnLogin;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	@CacheLookup
	public WebElement lnkLogout ;

	
	
	
	
	
	public void setUserName(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}	
		
		
		
}

	
	
	
	
	
	
	
	