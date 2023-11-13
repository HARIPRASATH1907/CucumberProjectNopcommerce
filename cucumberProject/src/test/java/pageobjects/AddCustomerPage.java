package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AddCustomerPage {

	public  WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	

	
	//Locators
	
	@FindBy(xpath="//a[@href=\"#\"]//p[contains(text(),'Customers')]")
	        @CacheLookup
	        public WebElement lnkCustomers_menu;
	
	@FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	        @CacheLookup
	        public WebElement lnkCustomers_menuitem;
			
	@FindBy(xpath="//a[@class='btn btn-primary']") //Add new
	        @CacheLookup
	        public WebElement btnAddnew;
			
	@FindBy(xpath="//input[@id='Email']")
	        @CacheLookup
	        public WebElement txtEmail;
	
	@FindBy(xpath="//input [@id='Password']")
	        @CacheLookup	
	        public WebElement txtPassword;
	
	@FindBy(xpath="//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div")
			@CacheLookup
			public WebElement txtcustomerRoles;
			
	@FindBy(xpath="//li[contains(text(),'Administrators')]")
			@CacheLookup
			public WebElement lstitemAdministrators;
			
	@FindBy(xpath="//li[contains(text(),'Registered')]")
			@CacheLookup
			public WebElement lstitemRegistered;
			
	@FindBy(xpath="//li[contains(text(),'Guests')]")
			@CacheLookup
			public WebElement lstitemGuests;
			
	@FindBy(xpath="//li[contains(text(),'Vendors')]")
			@CacheLookup
			public WebElement lstitemVendors;
			
	@FindBy(xpath="//*[@id='VendorId']")
			@CacheLookup
			public WebElement drpmgrofVendor;
			
	@FindBy(xpath="//option[contains(text(),'Vendor 1')]")///elename have to change 
			@CacheLookup
			public WebElement rdMaleGender;
			
	@FindBy(xpath="//option[contains(text(),'Vendor 2')]")
			@CacheLookup
			public WebElement rdFeMaleGender;
			
	@FindBy(xpath="//input[@id='FirstName']")
			@CacheLookup
			public WebElement txtFirstName;
			
	@FindBy(xpath="//input[@id='LastName']")
			@CacheLookup
			public WebElement txtLastName;
			
	@FindBy(xpath="//input[@id='DateOfBirth']")
			@CacheLookup
			public WebElement txtDob;
			
	@FindBy(xpath="//input[@id='Company']")
			@CacheLookup
			public WebElement txtCompanyName;
					
	@FindBy(xpath="//textarea[@id='AdminComment']")
			@CacheLookup
			public WebElement txtAdminContent;
			
	@FindBy(xpath="//button[@name='save']")
			@CacheLookup
			public WebElement btnSave;
			
			
			//Action Methods
	
public String getPageTitle() {
	return ldriver.getTitle();   //To get page title
	}
			
			
public void clickonCustomersMenu() {
    lnkCustomers_menu.click();
}

public void clickonCustomersMenuItem() {
    lnkCustomers_menuitem.click();
}

public void clickonAddnew() {
    btnAddnew.click();
}

public void setEmail(String email) {
    txtEmail.sendKeys(email);
}

public void setPassword(String password) {
    txtPassword.sendKeys(password);
}


public void setCustomerRoles(String role) throws InterruptedException
    {
        if(!role.equals("Vendors")) //If role is vendors should not delete Register as pe
        {
        	ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@class='k-select']")).click();
        }//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[@class='k-select']

        txtcustomerRoles.click();
      
        WebElement listitem;
      
        Thread.sleep(3000) ;

if(role.equals("Administrators"))
{
	listitem=lstitemAdministrators;
}
else if(role.equals("Guests"))
{
	listitem=lstitemGuests;
}
else if(role.equals("Registered"))
{
listitem=lstitemRegistered;
}
else if(role.equals("Vendors"))
{
	listitem=lstitemVendors;
}
else
{
listitem=lstitemGuests;
}

//listitem.click();
//Thread.sleep(3000);

JavascriptExecutor js=(JavascriptExecutor)ldriver;
js.executeScript("arguments[0].click();", listitem);

 }


public void setManagerofVendor(String value) {
Select drp=new Select(drpmgrofVendor);
drp.selectByVisibleText(value);
}


public void setGender(String gender) {
	if(gender.equals("Male"))
	{
		rdMaleGender.click();	
	}
    else if(gender.equals("Female"))
    {
	rdFeMaleGender.click();	
    }
    else
    {
	rdMaleGender.click();//Default
    }

}

public void setFirstName(String fname)
{
	txtFirstName.sendKeys(fname);	
}

public void setLastName(String lname)
{
	txtLastName.sendKeys(lname) ;
}
public void setDob(String dob)
{
    txtDob.sendKeys(dob);
}
public void setCompanyName(String comname)
{
    txtCompanyName.sendKeys(comname);
}
public void setAdminContent(String content)
{
    txtAdminContent.sendKeys(content);
}
public void clickonSave()
{
    btnSave.click();
}
}
