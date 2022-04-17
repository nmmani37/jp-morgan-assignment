package com.jp.assignment.tests;

import com.jp.assignment.api.PostsRestClient;
import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environmentconfig.TestEnvDefaults;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.Assert;
import java.io.FileReader;


public class PostEndpointTC {
    private DefaultEnv env;
    private PostsRestClient postsRestClient;
    private Response response;
    JSONObject postJsonObject;

    @Given("provided the Json place holder service is up and running, create the test environment.")
    public void setup(){
        env = TestEnvDefaults.getTestEnv();
        postsRestClient = new PostsRestClient(env);
    }

    @When("form the test input data using the following input files {string}")
    public void readInput(String inputFile){
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(inputFile);
            postJsonObject = (JSONObject)object;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Then("create post on social media and validate the response {int} and {string}")
    public void createPostAndValidate(int expectedStatusCode, String validateResponse){
        response = postsRestClient.createPost(postJsonObject.toString());
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of user id failed ",
                    response.body().jsonPath().get("userId").toString().equals(postJsonObject.get("userId").toString()));
            Assert.assertTrue("Validation of Post title is failed",
                    response.body().jsonPath().get("title").toString().equals(postJsonObject.get("title")));
            Assert.assertTrue("Validation of Post body is failed",
                    response.body().jsonPath().get("body").toString().equals(postJsonObject.get("body")));
        }
    }

    @Then("create post via user on social media and validate the response {int}, {int} and {string}")
    public void createPostViaUserAndValidate(int user_id, int expectedStatusCode, String validateResponse){
        response = postsRestClient.createPostViaUser(user_id, postJsonObject.toString());
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of user id failed ",
                    response.body().jsonPath().get("userId").toString().equals(String.valueOf(user_id)));
            Assert.assertTrue("Validation of Post title is failed",
                    response.body().jsonPath().get("title").toString().equals(postJsonObject.get("title")));
            Assert.assertTrue("Validation of Post body is failed",
                    response.body().jsonPath().get("body").toString().equals(postJsonObject.get("body")));
        }
    }

    @When("get all the posts present in social media {int} and validate the total posts present is {int}")
    public void getAllPostsAndValidated(int expectedStatusCode, int totalPosts){
        response = postsRestClient.getAllPosts();
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode){
            Assert.assertTrue("Validation failed on total number of posts count",
                    response.body().jsonPath().getList("$").size() == totalPosts);
        }
    }

    @When("get the post present in social media by postid {int}")
    public void getPostByPostId(int post_id){
        response = postsRestClient.getPostById(post_id);
    }

    @And("validate the received post contains same id or not {int}, {int} and {string}")
    public void validatePostByPostId(int id, int expectedStatusCode, String validateResponse){
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of post contains same id or not is failed :",
                    response.body().jsonPath().get("id").toString().equals(String.valueOf(id)));
        }
    }

    @When("get the post present in social media by userid {int}")
    public void getPostByUserId(int user_id){
        response = postsRestClient.getPostByUserId(user_id);

    }

    @And("validate the received post contains same user id or not {int}, {int} and {string}")
    public void validatePostByUserId(int user_id, int expectedStatusCode, String validateResponse){
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of post contains same id or not is failed :",
                    response.body().jsonPath().get("[0].userId").toString().equals(String.valueOf(user_id)));
        }
    }

    @When("update the post present in social media by post_id {int} and validate the response is updated or not {int}, {string}")
    public void updatePostAndValidate(int post_id, int expectedStatusCode, String validateResponse ){
        response = postsRestClient.updatePost(post_id, postJsonObject.toString());
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of updated user id failed ",
                    response.body().jsonPath().get("userId").toString().equals(postJsonObject.get("userId").toString()));
            Assert.assertTrue("Validation of updated Post title is failed",
                    response.body().jsonPath().get("title").toString().equals(postJsonObject.get("title")));
            Assert.assertTrue("Validation of updated Post body is failed",
                    response.body().jsonPath().get("body").toString().equals(postJsonObject.get("body")));
        }
    }

    @When("delete the post using post_id {int} and verify the response is {int}, {string}")
    public void deletePostAndValidate(int post_id, int expectedStatusCode, String validateResponse){
        response = postsRestClient.deletePost(post_id);
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);
    }
}
