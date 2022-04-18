package com.jp.assignment.tests;

import com.jp.assignment.api.PostsRestClient;
import com.jp.assignment.api.UsersRestClient;
import com.jp.assignment.environment.DefaultEnv;
import com.jp.assignment.environmentconfig.TestEnvDefaults;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

public class UserStepDef {

    private DefaultEnv env;
    private UsersRestClient usersRestClient;
    private Response response;
    JSONObject userJsonObject;

    @Given("provided the Json place holder service is up and running, create the test environment for users endpoint.")
    public void setup(){
        env = TestEnvDefaults.getTestEnv();
        usersRestClient = new UsersRestClient(env);
    }

    @When("form the test input data using the following input files {string} for users endpoint")
    public void readUserInput(String inputFile){
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(inputFile);
            userJsonObject = (JSONObject)object;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @When("get all the users present in social media {int} and validate the total users present is {int}")
    public void getAllPostsAndValidate(int expectedStatusCode, int totalUsers){
        response = usersRestClient.getAllUsers();
        validateStatusCode(expectedStatusCode);

        if(response.statusCode() == expectedStatusCode){
            Assert.assertTrue("Validation failed on total number of users count",
                    response.body().jsonPath().getList("$").size() == totalUsers);
        }
    }

    @When("get the user present in social media by user_id {int}")
    public void getPostByPostId(int id){
        response = usersRestClient.getUserById(id);
    }

    @And("validate the received user contains same id or not {int}, {int} and {string}")
    public void validatePostByPostId(int id, int expectedStatusCode, String validateResponse){
        validateStatusCode(expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of post contains same id or not is failed :",
                    response.body().jsonPath().get("id").toString().equals(String.valueOf(id)));
        }
    }

    private void validateStatusCode(int expectedStatusCode){
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);
    }

    @When("get all the users by post_id present in social media {int}, {int}")
    public void getUsersByPostId(int post_id, int expectedStatusCode) {
        response = usersRestClient.getUserByPostId(post_id);
        validateStatusCode(expectedStatusCode);
    }

    @When("get all the users by comment_id present in social media {int}, {int}")
    public void getUsersByCommentId(int comment_id, int expectedStatusCode) {
        response = usersRestClient.getUserByCommentId(comment_id);
        validateStatusCode(expectedStatusCode);
    }

    private void validateResponseBody(int expectedStatusCode, String user_id){
        if(response.statusCode() == expectedStatusCode){
            Assert.assertTrue("Validation of user id failed ",
                    response.body().jsonPath().get("userId").toString().equals(user_id));
            Assert.assertTrue("Validation of Post title is failed",
                    response.body().jsonPath().get("title").toString().equals(userJsonObject.get("title")));
            Assert.assertTrue("Validation of Post body is failed",
                    response.body().jsonPath().get("body").toString().equals(userJsonObject.get("body")));
        }
    }


}
