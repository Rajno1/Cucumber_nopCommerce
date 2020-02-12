Feature: Login

@Sanity
Scenario: Successful login with valid credentials
	Given user launch chrome browser
	When user opens url "https://admin-demo.nopcommerce.com/login"
	And user enters Email as "admin@yourstore.com" and Password as "admin"
	Then click on login
	And page Title should be "Dashboard / nopCommerce administration"
	When user click on logout link
	And close browser

@Regression	
Scenario Outline: Login With Data Driven
	Given user launch chrome browser
	When user opens url "https://admin-demo.nopcommerce.com/login"
	And user enters Email as "<email>" and Password as "<password>"
	Then click on login
	And page Title should be "Dashboard / nopCommerce administration"
	When user click on logout link
	And close browser
	
	Examples: 
		| email | password |
		| admin@yourstore.com| admin|
		| admin1@yourstore.com| admin123|