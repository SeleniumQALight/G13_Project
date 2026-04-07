package org;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/privatBankRates.feature",
        glue = "org.bdd.stepDefinitions",
        tags = "@R004",
        plugin = {"pretty", "html:target/cucumber-privatbank.html"}
)
public class RunCucumberPrivatBankTest {
}