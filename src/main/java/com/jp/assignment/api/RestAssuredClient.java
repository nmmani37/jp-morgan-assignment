package com.jp.assignment.api;

import com.jp.assignment.environment.DefaultEnv;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;
import static io.restassured.RestAssured.given;

public class RestAssuredClient {
    private final URL baseUrl;
    protected Response response;

    public RestAssuredClient(DefaultEnv env){
        this.baseUrl = env.getURL();
    }

    protected Response getFromJsonPlaceHolder(String endPoint){
        URL theEndPointURL = createEndPointURL(endPoint);

        Response response = given()
                .when()
                .get(theEndPointURL.toExternalForm())
                .andReturn();

        if( response.statusCode()!=200 && response.statusCode()!=401 &&
                response.statusCode()!=409 && response.statusCode()!=302){
            System.out.println("POTENTIAL BUG - " + response.statusCode() + " FOR " + endPoint );
        }

        return response;
    }

    protected Response postToJsonPlaceHolder(String endPoint, String msg){
        URL theEndPointURL = createEndPointURL(endPoint);

        Response response = given()
                .headers("Content-type", "application/json", "charset", "UTF-8")
                .body(msg)
                .when()
                .post(theEndPointURL.toExternalForm())
                .andReturn();

        if( response.statusCode()!=201 && response.statusCode()!=401 &&
                response.statusCode()!=409 && response.statusCode()!=302){
            System.out.println("POTENTIAL BUG - " + response.statusCode() + " FOR " + endPoint + "\n" + msg );
        }

        return response;
    }

    protected Response putToJsonPlaceHolder(String endPoint, String msg){
        URL theEndPointURL = createEndPointURL(endPoint);

        Response response = given()
                .headers("Content-type", "application/json", "charset", "UTF-8")
                .body(msg)
                .contentType("application/json")
                .when()
                .put(theEndPointURL.toExternalForm())
                .andReturn();

        if( response.statusCode()!=201 && response.statusCode()!=401 &&
                response.statusCode()!=409 && response.statusCode()!=302){
            System.out.println("POTENTIAL BUG - " + response.statusCode() + " FOR " + endPoint + "\n" + msg );
        }

        return response;
    }

    protected Response patchToJsonPlaceHolder(String endPoint, String msg){
        URL theEndPointURL = createEndPointURL(endPoint);

        Response response = given()
                .headers("Content-type", "application/json", "charset", "UTF-8")
                .body(msg)
                .contentType("application/json")
                .when()
                .patch(theEndPointURL.toExternalForm())
                .andReturn();

        if( response.statusCode()!=201 && response.statusCode()!=401 &&
                response.statusCode()!=409 && response.statusCode()!=302){
            System.out.println("POTENTIAL BUG - " + response.statusCode() + " FOR " + endPoint + "\n" + msg );
        }

        return response;
    }

    protected Response deleteFromJsonPlaceHolder(String endPoint){
        URL theEndPointURL = createEndPointURL(endPoint);

        Response response = given()
                .when()
                .delete(theEndPointURL.toExternalForm())
                .andReturn();

        if( response.statusCode()!=201 && response.statusCode()!=401 &&
                response.statusCode()!=409 && response.statusCode()!=302){
            System.out.println("POTENTIAL BUG - " + response.statusCode() + " FOR " + endPoint );
        }

        return response;
    }

    private URL createEndPointURL(String endpoint){
        try{
            return new URL(baseUrl, endpoint);
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            new RuntimeException(
                    String.format("Could not construct %s endpoint using %s",
                            endpoint, baseUrl.toExternalForm()));
            return null;
        }
    }



}
