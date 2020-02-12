package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	//create a constructor
	public WebDriver driver;
	public AddCustomerPage(WebDriver ldriver) {
		driver = ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	//identify the elements
	By lnkCustomers_Menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomer_item = By.xpath("//a[@href='/Admin/Customer/List']//span[contains(text(),Customers)]");
	By btnAdd_new = By.xpath("//a[@class='btn bg-blue']");
	By txt_email = By.xpath("//input[@id='Email']");
	By txt_password = By.xpath("//input[@name='Password']");
	By txt_Firstname = By.xpath("//input[@id='FirstName']");
	By txt_Lastname = By.xpath("//input[@name='LastName']");
	By rdbtn_male = By.xpath("//input[@id='Gender_Male']");
	By rdbtn_female = By.xpath("//input[@id='Gender_Female']");
	By date_DOB = By.xpath("//input[@name='DateOfBirth']");
	By txt_companyName = By.xpath("//input[@id='Company']");
	By chbx_is_empty_tax = By.xpath("//input[@id='IsTaxExempt']");
	By chbx_Newsletter_yourstorename = By.xpath("//input[@name='SelectedNewsletterSubscriptionStoreIds' and @value='1']");
	By chbx_Newsletter_teststore2 = By.xpath("//input[@name='SelectedNewsletterSubscriptionStoreIds' and @value='2']");
	
	By txtCustomer_role = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	By role_Administrators = By.xpath("//li[contains(text(),'Administrators')]");
	By role_ForumModerators  = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By role_Guest = By.xpath("//li[contains(text(),'Guests')]");
	By role_Registered = By.xpath("//li[contains(text(),'Registered')]");
	By role_Vendors = By.xpath("//li[contains(text(),'Vendors')]");
	By btn_save = By.xpath("//button[@name='save']");
	By drp_ManagerOfVenderId = By.xpath("//select[@id='VendorId']");
	
	//Create actions methods for elements
	
	public String getPageTitle() {
		
		return driver.getTitle();
	}
	
	
	
	public void clickOnCustomersMenu() {
		driver.findElement(lnkCustomers_Menu).click();
	}
	public void clickOnCustomeritem() {
		driver.findElement(lnkCustomer_item).click();
	}
	public void clikOnAddnewBtn() {
		driver.findElement(btnAdd_new).click();
	}
	public void enterEmail(String email) {
		driver.findElement(txt_email).sendKeys(email);
	}
	public void setPassword(String password) {
		driver.findElement(txt_password).sendKeys(password);
	}
	public void enterFirstName(String first_name) {
		driver.findElement(txt_Firstname).sendKeys(first_name);
	}
	public void enterLastName(String last_name) {
		driver.findElement(txt_Lastname).sendKeys(last_name);
	}
	public void DOB(String date) {
		driver.findElement(date_DOB).sendKeys(date);
	}
	public void enterCompany_name(String cmpName) {
		driver.findElement(txt_companyName).sendKeys(cmpName);
	}
	public void Is_tax_info() {
		driver.findElement(chbx_is_empty_tax).click();
	}
	public void Newsletter(String store) {
		if(store.equals("your store name")) {
			driver.findElement(chbx_Newsletter_yourstorename).click();
		}else {
			driver.findElement(chbx_Newsletter_teststore2).click();
		}
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			driver.findElement(rdbtn_male).click();
		}else if(gender.equals("Female")) {
			driver.findElement(rdbtn_female).click();
		}else {
			driver.findElement(rdbtn_male).click();
		}
	}
	
	
	
	public void setCustormerRole(String role) throws InterruptedException {
		
		if(!role.equals("Vendors")) {
			driver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[contains(text(),'Registered')]"));
		}
		
		driver.findElement(txtCustomer_role).click();
		WebElement roleItems;
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			roleItems = driver.findElement(role_Administrators);
		}else if(role.equals("Forum Moderators"))
		{
			roleItems = driver.findElement(role_ForumModerators);
		}else if(role.equals("Guests"))
		{
			roleItems = driver.findElement(role_Guest);
		}else if (role.equals("Registered"))
		{
			roleItems = driver.findElement(role_Registered);
		}else if (role.equals("Vendors"))
		{
			roleItems = driver.findElement(role_Vendors);
		}
		else {
			roleItems =driver.findElement(role_Guest);
		}
		
		//roleItems.click();
		Thread.sleep(3000);
		 
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click();", roleItems);
	}
	
	
	public void mangerOfVendorId() {
		Select drp = new Select(driver.findElement(drp_ManagerOfVenderId));
		if(drp.equals("Vendor 1")) {
			drp.selectByVisibleText("Vendor 1");
		}else if (drp.equals("Vendor 2")) {
			drp.selectByVisibleText("Vendor 2");
		}else {
			drp.selectByIndex(0);
		}
		
		
	}
	
	public void clickOnSavebtn() {
		driver.findElement(btn_save).click();
	}
	
	
	
	
	
	
	
}
