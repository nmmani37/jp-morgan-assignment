package com.jp.assignment.tests;

import com.jp.assignment.api.CommentsRestClient;
import com.jp.assignment.cucumber.ListenerPlugin;
import com.jp.assignment.cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;


public class CommentStepDef{
    private CommentsRestClient commentsRestClient;
    private Response response;
    JSONObject commentJsonObject;

    private static final Logger LOG = LogManager.getLogger(ListenerPlugin.class);
    TestContext testContext;

    public CommentStepDef(TestContext testContext){
        this.testContext = testContext;
    }

    @Given("Create the test environment for comments endpoint.")
    public void setup(){
        commentsRestClient = new CommentsRestClient(testContext.getDefaultEnv());
    }

    @When("form the test input data using the following input files {string} for comments endpoint")
    public void readInput(String inputFile){
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(inputFile);
            commentJsonObject = (JSONObject)object;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Then("create comment on social media and validate the response {int} and {string}")
    public void createCommentAndValidate(int expectedStatusCode, String validateResponse){
        response = commentsRestClient.createComment(commentJsonObject.toString());
        validateStatusCode(expectedStatusCode);
        if(validateResponse.equals("true"))
            validateResponseBody(expectedStatusCode, commentJsonObject.get("postId").toString());
    }

    @Then("create comment via post on social media and validate the response {int}, {int} and {string}")
    public void createCommentViaPostAndValidate(int post_id, int expectedStatusCode, String validateResponse){
        response = commentsRestClient.createCommentViaPost(post_id, commentJsonObject.toString());
        validateStatusCode(expectedStatusCode);
        if(validateResponse.equals("true"))
            validateResponseBody(expectedStatusCode, String.valueOf(post_id));
    }

    @When("get all the comments present in social media {int} and validate the total comments present is {int}")
    public void getAllCommentsAndValidate(int expectedStatusCode, int totalPosts){
        response = commentsRestClient.getAllComments();
        validateStatusCode(expectedStatusCode);

        if(response.statusCode() == expectedStatusCode){
            Assert.assertTrue("Validation failed on total number of posts count",
                    response.body().jsonPath().getList("$").size() == totalPosts);
        }
    }

    @When("get the comment present in social media by comment_id {int}")
    public void getCommentById(int comment_id){
        response = commentsRestClient.getCommentById(comment_id);
    }

    @And("validate the received comment contains same id or not {int}, {int} and {string}")
    public void validateCommentById(int id, int expectedStatusCode, String validateResponse){
        validateStatusCode(expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of post contains same id or not is failed :",
                    response.body().jsonPath().get("id").toString().equals(String.valueOf(id)));
        }
    }

    @When("get the comment present in social media by post_id {int}")
    public void getCommentByPostId(int post_id){
        response = commentsRestClient.getCommentByPostId(post_id);

    }

    @And("validate the received comment contains same post_id or not {int}, {int} and {string}")
    public void validateCommentByPostId(int post_id, int expectedStatusCode, String validateResponse){
       validateStatusCode(expectedStatusCode);

        if(response.statusCode() == expectedStatusCode && validateResponse.equals("true")){
            Assert.assertTrue("Validation of post contains same id or not is failed :",
                    response.body().jsonPath().get("[0].postId").toString().equals(String.valueOf(post_id)));
        }
    }

    @When("update the comment present in social media by comment_id {int} and validate the response is updated or not {int}, {string}")
    public void updateCommentAndValidate(int comment_id, int expectedStatusCode, String validateResponse ){
        response = commentsRestClient.updateComment(comment_id, commentJsonObject.toString());
        validateStatusCode(expectedStatusCode);
        if(validateResponse.equals("true"))
            validateResponseBody(expectedStatusCode, commentJsonObject.get("postId").toString());
    }

    @When("delete the comment using comment_id {int} and verify the response is {int}, {string}")
    public void deleteCommentAndValidate(int comment_id, int expectedStatusCode, String validateResponse){
        response = commentsRestClient.deleteComment(comment_id);
        validateStatusCode(expectedStatusCode);
    }
    
    private void validateStatusCode(int expectedStatusCode){
        Assert.assertTrue("Response code is not "+expectedStatusCode+", received response is :"+response.statusCode(),
                response.statusCode()==expectedStatusCode);
    }
    
    private void validateResponseBody(int expectedStatusCode, String postId){
        if(response.statusCode() == expectedStatusCode){
            Assert.assertTrue("Validation of post id failed ",
                    response.body().jsonPath().get("postId").toString().equals(postId));
            Assert.assertTrue("Validation of name is failed",
                    response.body().jsonPath().get("name").toString().equals(commentJsonObject.get("name")));
            Assert.assertTrue("Validation of email is failed",
                    response.body().jsonPath().get("email").toString().equals(commentJsonObject.get("email")));
            Assert.assertTrue("Validation of comment body is failed",
                    response.body().jsonPath().get("body").toString().equals(commentJsonObject.get("body")));
        }
    }
}
