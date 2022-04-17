package com.jp.assignment.environment;

public class JsonPlaceHolderEndpoints {
    public static final String posts="/posts";
    public static final String comments="/comments";
    public static final String users= "/users";
    public static final String postsViaUsers = "/users/%s/posts";


    public static String getPostsViaUsers(String userId){
        return String.format(postsViaUsers, userId);
    }


}
