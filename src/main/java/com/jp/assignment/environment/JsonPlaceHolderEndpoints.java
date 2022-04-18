package com.jp.assignment.environment;

public class JsonPlaceHolderEndpoints {
    public static final String posts="/posts";
    public static final String comments="/comments";
    public static final String users= "/users";
    public static final String postsViaUsers = "/users/%s/posts";
    public static final String commentsViaPosts = "/posts/%s/comments";
    public static final String usersViaPosts = "/posts/%s/users";
    public static final String usersViaComments = "/comments/%s/users";


    public static String getPostsViaUsers(String userId){
        return String.format(postsViaUsers, userId);
    }

    public static String getCommentsViaPosts(String postId){
        return String.format(commentsViaPosts, postId);
    }

    public static String getUsersViaPosts(String postId){
        return String.format(usersViaPosts, postId);
    }

    public static String getUsersViaComments(String commentId){
        return String.format(usersViaComments, commentId);
    }

}
