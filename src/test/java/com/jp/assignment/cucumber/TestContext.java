package com.jp.assignment.cucumber;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.utils.URLifier;
import io.cucumber.java.Scenario;

import java.net.URL;

public class TestContext {

    DefaultEnv defaultEnv;

    public DefaultEnv getDefaultEnv(){
        return defaultEnv;
    }
    public void setDefaultEnv(String url, Scenario scn){
        this.defaultEnv = new DefaultEnv(getValidatedURL(url));
    }

    public static URL getValidatedURL(String baseUrl){
        return URLifier.getValidURLFromString(baseUrl);
    }
}
