package com.jp.assignment.environment;

import java.net.URL;

public class DefaultEnv {
    private final URL theURL;

    public DefaultEnv(URL theURL){
        this.theURL = theURL;
    }

    public URL getURL(){
        return theURL;
    }
}
