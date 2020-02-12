package pageObjects;


import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomer {
	
	public WebDriver driver;
	WaitHelper waithelper;
	//Crete constructor
	public SearchCustomer(WebDriver ldriver) {
		driver=ldriver;
		PageFactory.initElements(ldriver, this);
		waithelper = new WaitHelper(ldriver);
	}
	
	
	//identify elements
	
	@FindBy(how=How.XPATH,using="//input[@id='SearchEmail']")
	WebElement txt_email;
	
	@FindBy(how=How.XPATH,using="//input[@name='SearchFirstName']")
	WebElement txt_firstname;
	
	@FindBy(how=How.XPATH,using="//input[@id='SearchLastName']")
	WebElement txt_lastname;
	
	@FindBy(how=How.XPATH,using="//button[@id='search-customers']")
	WebElement btn_search;
	

	@FindBy(how=How.XPATH,using="//table[@role='grid']")
	WebElement tblSearchResults;
	
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> table_row;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> table_column;
	
	
	// Create actions methods
	public void enter_email(String mail) {
		
		waithelper.waitForelement(txt_email, 30);
		txt_email.clear();  
		txt_email.sendKeys(mail);
	}
	public void enter_firstname(String firstname) {
		waithelper.waitForelement(txt_firstname, 30);
		txt_firstname.clear();
		txt_firstname.sendKeys(firstname);
	}
	public void enter_lastname(String lastname) {
		waithelper.waitForelement(txt_lastname, 30);
		txt_lastname.clear();
		txt_lastname.sendKeys(lastname);
	}
	public void click_searchbtn() {
		btn_search.click();
	}
	
	
	public int getNumOfRows() {
		return table_row.size();
	}
	
	public int getNumOfColumns() {
		return table_column.size();
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;
		for(int i=1;i<=getNumOfRows();i++) {
			String emailid= table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
		
		System.out.println(emailid);
		
		if(emailid.equals(email)) {
			flag = true;
			}
		}
		return flag;
}
	
	
	public boolean searchCustomerByname(String Name) {
		boolean flag = false;
		for(int i=1;i<=getNumOfRows();i++) {
			String name= table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
		
			String names[ ] = name.split(" ");
		
		if(names[0].equals("Victoria") && names[1].equals("Terces")) {
			flag = true;
			}
		}
		return flag;
}
}