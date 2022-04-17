package com.jp.assignment.api;

import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environment.JsonPlaceHolderEndpoints;
import io.restassured.response.Response;

public class CommentsRestClient extends RestAssuredClient {
    private String commentEndPoint = JsonPlaceHolderEndpoints.comments;
    public CommentsRestClient(DefaultEnv env){
        super(env);
    }

    /**
     *
     * @param msg
     * @return
     */
    public Response commentOnPost(String msg){
        Response response = postToJsonPlaceHolder(commentEndPoint, msg);
        return response;
    }

     /*public Response getAllCommentsOnPost(){

    }

    public Response getCommentWithFilters(List<String> filters ){

    }


    public Response updateComment(){

    }

    public Response patchComment(){

    }

    public Response deleteComment(){

    }*/
}
