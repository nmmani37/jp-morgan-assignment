package com.jp.assignment.environmentconfig;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.utils.URLifier;

import java.net.URL;

public class TestEnvDefaults {

    private static final String baseUrl = "https://jsonplaceholder.typicode.com";

    public static URL getValidatedURL(){
        return URLifier.getValidURLFromString(baseUrl);
    }

    public static DefaultEnv getTestEnv(){
        DefaultEnv env = new DefaultEnv(getValidatedURL());
        return env;
    }
}
