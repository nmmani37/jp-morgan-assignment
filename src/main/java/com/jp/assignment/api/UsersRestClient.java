package com.jp.assignment.api;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environment.JsonPlaceHolderEndpoints;
import io.restassured.response.Response;

public class UsersRestClient extends RestAssuredClient {

    private String userEndPoint = JsonPlaceHolderEndpoints.users;

    public UsersRestClient(DefaultEnv env){
        super(env);
    }

    public Response getAllUsers(){
        Response response = getFromJsonPlaceHolder(userEndPoint);
        return response;
    }

     /*public Response updateUser(){

    }

    public Response patchUser(){

    }

    public Response deleteUser(){

    }*/
}
