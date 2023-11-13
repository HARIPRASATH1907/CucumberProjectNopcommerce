package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions      // In this we have to define the feature file and step definition location
(
	    features=".//Features/Customer.feature",
			glue="stepdefinitions",
	      dryRun=false,        //Without actual real execution initially it will cross checks whether every steps is having methods implemented or not
	                             //checks every feature file contains corresponding feature file or not
	  monochrome=true,          // removes unnecessary characters in the output console
	      plugin= {"pretty",        //pretty is the parameter which will give the output very clearly in the console
			
			"html:target/test-output"}//, // output
	
	     //  tags = "@sanity"          //"@sanity"
                                   //AND
	                             // "@sanity,@regression"  OR       */
)


public class TestRun {

}

//NOTE:
//to run all feature files features=".//Features/"
	
// if you want to execute only 3 feature files from 5 feature 
	// features={".//Features/Login.feature",".//Features/Customer.feature"},