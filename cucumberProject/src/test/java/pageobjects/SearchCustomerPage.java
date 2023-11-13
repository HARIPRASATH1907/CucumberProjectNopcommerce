package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public  WebDriver ldriver;

	WaitHelper waithelper;
	public SearchCustomerPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	

	@FindBy(xpath="//input[@name='SearchEmail']")
	@CacheLookup
	public WebElement txtEmail;
	@FindBy (xpath="//input[@name='SearchFirstName']")
	@CacheLookup
	public WebElement txtFirstName;
	@FindBy (xpath="//input[@name='SearchLastName']")
	@CacheLookup
	public WebElement txtLastName;
	@FindBy (xpath="//select[@name='SearchMonthOfBirth']")
	@CacheLookup
	public WebElement drpdobMonth;
	@FindBy (xpath="//select[@name='SearchDayOfBirth']")
	@CacheLookup
	public WebElement drpdobDay ;
	@FindBy (xpath="//input[@name='SearchCompany']")
	@CacheLookup
	public WebElement txtCompany;
	@FindBy (xpath="//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	public WebElement txtcustomerRoles;
	@FindBy (xpath= "//li[contains(text(),'Administrators')]")
	@CacheLookup
	public WebElement lstitemAdministrators;
	@FindBy (xpath= "//li[contains(text(),'Registered')]")
	@CacheLookup
	public WebElement lstitemRegistered;
	@FindBy (xpath= "//li[contains(text(),'Guests')]")
	@CacheLookup
	public WebElement lstitemGuests;
	@FindBy (xpath= "//li[contains(text(),'vendors')]")
	@CacheLookup
	public WebElement lstitemVendors ;
	@FindBy (xpath="//button[@id=\"search-customers\"]")
	@CacheLookup
	public WebElement btnSearch;
	@FindBy (how = How .XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;
	@FindBy (xpath= "//table[@id='customers-grid']")
	public WebElement table;
	@FindBy (xpath= "//table[@id='customers-grid']//tbody/tr")
	public List<WebElement> tableRows;
	@FindBy (xpath= "//table[@id='customers-grid']//tbody/tr/td")
	public List<WebElement> tableColumns;




//Actions

public void setEmail (String email) {
waithelper.WaitForElement(txtEmail,30);
txtEmail.clear();
txtEmail.sendKeys (email);
}

public void setFirstName(String fname) {
waithelper.WaitForElement(txtFirstName,30);
txtFirstName.clear();
txtFirstName.sendKeys(fname);
}

public void setLastName (String lname) {
waithelper.WaitForElement(txtLastName,30);
txtLastName.clear();
txtLastName.sendKeys(lname);
}


public void clicksearch () {
btnSearch.click();
waithelper.WaitForElement(btnSearch, 30);
}


public int getNoOfRows() {
return(tableRows.size());
}

public int getNoOfColumns() {
return(tableColumns.size());
}

public boolean searchCustomerByEmail(String email) {
boolean flag=false;
for (int i=1;i<=getNoOfRows();i++)
{
String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();

System.out.println(emailid);


if(emailid.equals(email))
{
flag=true;
}
}
return flag;
}


public boolean searchCustomerByName(String Name) {
boolean flag=false;
for (int i=1;i<=getNoOfRows();i++)
{
String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();

String names[]=name.split(" "); //Seperating Firstname & lastname


if(names[0].equals("Victoria") && names[1].equals("Terces"))
{
flag=true;
}
}
return flag;
}











}
