package com.jp.assignment.tests;

import com.jp.assignment.cucumber.TestContext;
import io.cucumber.java.en.Given;

public class BaseStepDef {
    TestContext testContext;

    public BaseStepDef(TestContext testContext){
        this.testContext = testContext;
    }

    @Given("Set the Base url which needs to be tested {string}")
    public void setup(String baseUrl) {
        testContext.setDefaultEnv(baseUrl);
    }
}
