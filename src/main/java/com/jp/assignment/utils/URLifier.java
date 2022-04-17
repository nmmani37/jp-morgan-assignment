package com.jp.assignment.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class URLifier {

    public static URL getValidURLFromString(String theURL){
        try{
            return new URL(theURL);
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            throw new RuntimeException(String.format(
                    "URL %s is not correctly formatted",
                    theURL));
        }
    }
}
