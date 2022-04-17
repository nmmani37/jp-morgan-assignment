package com.jp.assignment.api;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environment.JsonPlaceHolderEndpoints;
import io.restassured.response.Response;

public class PostsRestClient extends RestAssuredClient {
    private String postEndPoint = JsonPlaceHolderEndpoints.posts;
    public PostsRestClient(DefaultEnv env){
        super(env);
    }


    /**
     * Method to create posts on: https://jsonplaceholder.typicode.com/posts
     * @param body - String or msg body which needs to be posted
     * @return {Response} - response of the API
     */
    public Response createPost(String body){
        Response response = postToJsonPlaceHolder(postEndPoint, body);
        return response;
    }

    public Response createPostViaUser(int user_id, String body){
        Response response = postToJsonPlaceHolder(JsonPlaceHolderEndpoints.getPostsViaUsers(String.valueOf(user_id)), body);
        return response;
    }

    public Response getAllPosts(){
        Response response = getFromJsonPlaceHolder(postEndPoint);
        return response;
    }

    public Response getPostById(int post_id){
        Response response = getFromJsonPlaceHolder(postEndPoint+"/"+post_id);
        return response;
    }

    public Response getPostByUserId(int user_id){
        Response response = getFromJsonPlaceHolder(JsonPlaceHolderEndpoints.getPostsViaUsers(String.valueOf(user_id)));
        return response;
    }


    public Response updatePost(int post_id, String updateMsg){
        Response response = putToJsonPlaceHolder(postEndPoint+"/"+post_id, updateMsg);
        return response;
    }

    /*public Response patchPost(){

    }*/

    public Response deletePost(int post_id){
        Response response = deleteFromJsonPlaceHolder(postEndPoint+"/"+post_id);
        return response;
    }
}
