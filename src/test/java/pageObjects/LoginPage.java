package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	 WebDriver driver;
	 //identify page elements
	@FindBy(id="Email")
	WebElement txtEmail;
	
	@FindBy(how=How.NAME,using = "Password") 
	WebElement txtPassword;
	
	@FindBy(how=How.XPATH,using="//input[@value='Log in']") 
	WebElement btnLogin;
	
	@FindBy(how=How.LINK_TEXT,using="Logout")
	WebElement lnkLogout;

	//Create construtor
	
	public LoginPage(WebDriver ldriver) 
	{
	 driver = ldriver;
	 PageFactory.initElements(ldriver,this);
	}
 
 	// create action method for elements identified
 
	public void setUsername(String uname) {
	  txtEmail.clear();
	  txtEmail.sendKeys(uname);
	}
 
	public void setPassword(String pwd) {
	 txtPassword.clear();
	 txtPassword.sendKeys(pwd);
	}
 
	public void click_login() {
	 btnLogin.click();
	}
	public void click_logout() {
	 lnkLogout.click();
	}
 
}
