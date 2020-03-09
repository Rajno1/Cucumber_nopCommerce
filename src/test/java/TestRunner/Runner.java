package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features =".//Features/Customers.feature",
		glue = "StepDefinitions",  
		plugin= {"pretty",
				"html:test_output"},
		tags= {"@DeleteCustomerRole"},  // to run only specific scenario
		monochrome = true,
		dryRun = true
		
		)
public class Runner {

}
