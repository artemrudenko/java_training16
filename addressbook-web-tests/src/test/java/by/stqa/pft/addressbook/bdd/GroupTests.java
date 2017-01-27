package by.stqa.pft.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by artemr on 1/27/2017.
 */
@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "json:build/cucumber-report/result.json"})
public class GroupTests extends AbstractTestNGCucumberTests{
}
