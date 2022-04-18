package com.jp.assignment.tests;

import com.jp.assignment.cucumber.ListenerPlugin;
import com.jp.assignment.cucumber.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseStepDef {
    TestContext testContext;
    Scenario scn;
    private static final Logger LOG = LogManager.getLogger(ListenerPlugin.class);

    public BaseStepDef(TestContext testContext){
        this.testContext = testContext;
    }

    @Given("Set the Base url which needs to be tested {string}")
    public void setup(String baseUrl) {
        testContext.setDefaultEnv(baseUrl, scn);
    }
}
