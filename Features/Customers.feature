Feature: Customers

Background: These are common steps for every scenario
	Given user launch chrome browser
	When user opens url "https://admin-demo.nopcommerce.com/login"
	And user enters Email as "admin@yourstore.com" and Password as "admin"
	And click on login
	Then user can view Dashboard
	When user can click on customers menu
	And click on customer menu item

@Sanity
Scenario: Add a new Customer
	Then user can click on Addnew button
	And user can view add a new customer page
	Then user enters customer info
	When click on save button
	Then user can view conformation message "The new customer has been added successfully"
	And close browser
	
@Regression
Scenario: Search customer by using emailid
	And enter customer email
	When click on serach button
	Then user should found customer email in search field
	And close browser

@Regression
Scenario: Search customer by using name
	And enter customer firstname 
	And enter customer lastname
	When click on serach button
	Then user should found customer name in search field
	And close browser
	
