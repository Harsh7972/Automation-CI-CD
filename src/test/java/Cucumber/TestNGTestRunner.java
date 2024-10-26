package Cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",glue = "Harshalkapse.stepDefinitions",monochrome = true,tags = "@Regression", plugin = {"html:target/Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
