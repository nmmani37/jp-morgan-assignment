package com.jp.assignment.environment;

public class JsonPlaceHolderEndpoints {
    public static final String posts="/posts";
    public static final String comments="/comments";
    public static final String users= "/users";
    public static final String postsViaUsers = "/users/%s/posts";
    public static final String commentsViaPosts = "/posts/%s/comments";


    public static String getPostsViaUsers(String userId){
        return String.format(postsViaUsers, userId);
    }

    public static String getCommentsViaPosts(String postId){
        return String.format(commentsViaPosts, postId);
    }

}
