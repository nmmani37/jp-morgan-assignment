package com.jp.assignment.environment;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class DefaultEnv {
    private URL theURL;

    public DefaultEnv(){

    }
    public DefaultEnv(URL theURL){
        this.theURL = theURL;
    }
}
