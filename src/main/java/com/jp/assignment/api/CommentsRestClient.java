package com.jp.assignment.api;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environment.JsonPlaceHolderEndpoints;
import io.restassured.response.Response;

public class CommentsRestClient extends RestAssuredClient {
    private String commentEndPoint = JsonPlaceHolderEndpoints.comments;
    public CommentsRestClient(DefaultEnv env){
        super(env);
    }

    public Response createComment(String body){
        Response response = postToJsonPlaceHolder(commentEndPoint, body);
        return response;
    }

    public Response createCommentViaPost(int post_id, String body){
        Response response = postToJsonPlaceHolder(JsonPlaceHolderEndpoints.getCommentsViaPosts(String.valueOf(post_id)), body);
        return response;
    }

    public Response getAllComments(){
        Response response = getFromJsonPlaceHolder(commentEndPoint);
        return response;
    }

    public Response getCommentById(int comment_id){
        Response response = getFromJsonPlaceHolder(commentEndPoint+"/"+comment_id);
        return response;
    }

    public Response getCommentByPostId(int post_id){
        Response response = getFromJsonPlaceHolder(JsonPlaceHolderEndpoints.getCommentsViaPosts(String.valueOf(post_id)));
        return response;
    }


    public Response updateComment(int comment_id, String updateMsg){
        Response response = putToJsonPlaceHolder(commentEndPoint+"/"+comment_id, updateMsg);
        return response;
    }

    /*public Response patchPost(){

    }*/

    public Response deleteComment(int comment_id){
        Response response = deleteFromJsonPlaceHolder(commentEndPoint+"/"+comment_id);
        return response;
    }

}
