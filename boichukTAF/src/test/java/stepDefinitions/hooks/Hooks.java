package stepDefinitions.hooks;

import base.DriverFactory;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}