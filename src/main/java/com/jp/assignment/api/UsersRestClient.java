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

    public Response getUserById(int id){
        Response response = getFromJsonPlaceHolder(userEndPoint+"/"+id);
        return response;
    }

    public Response getUserByPostId(int post_id){
        Response response = getFromJsonPlaceHolder(JsonPlaceHolderEndpoints.getUsersViaPosts(String.valueOf(post_id)));
        return response;
    }

    public Response getUserByCommentId(int comment_id){
        Response response = getFromJsonPlaceHolder(JsonPlaceHolderEndpoints.getUsersViaComments(String.valueOf(comment_id)));
        return response;
    }

     /*public Response updateUser(){

    }

    public Response patchUser(){

    }

    public Response deleteUser(){

    }*/
}
