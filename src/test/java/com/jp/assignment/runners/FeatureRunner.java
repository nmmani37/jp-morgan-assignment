package com.jp.assignment.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featurefiles/post-endpoint-tc.feature"},
        glue = {"com.jp.assignment.tests"},
        plugin = {
                "html:target/html-reports/cucumber-html-report.html",
                "json:target/json-reports/cucumber-json-report.json",
                "usage:target/cucumber-usage.json",
                "timeline:target/test-output-thread/"
                },
        monochrome = true
        )

public class FeatureRunner{

}
