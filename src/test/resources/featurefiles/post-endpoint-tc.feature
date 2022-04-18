Feature: Use case for validating post's endpoint on Json Place Holder.
  Description: Purpose of this usecase is to validate the endpoint (https://jsonplaceholder.typicode.com/posts) with different test data.

  Background: Setup steps
    Given provided the Json place holder service is up and running, create the test environment for posts endpoint.

  Scenario Outline: Creating post via the endpoint "/posts"
    When form the test input data using the following input files <post_input_data> for posts endpoint
    Then create post on social media and validate the response <expected_status_code> and <validate_response_body>
    Examples:
      |post_input_data|expected_status_code|validate_response_body|
      # all the fields provided
      |"{\"userId\": 1, \"title\": \"title-1\",\"body\": \"post-msg-1\"}"|201|"true"|

      # missing mandatory parameters status code should be 400, since the dummy service is returning 201 (keeping the status code as 201))
      |"{\"title\": \"title-2\",\"body\": \"post-msg-2\"}"               |201|"false"|
      |"{\"userId\": 1, \"body\": \"post-msg-3\"}"                       |201|"false"|
      |"{\"userId\": 1, \"title\": \"title-4\"}"                         |201|"false"|
      |"{\"userId\": 1}"                                                 |201|"false"|
      |"{}"                                                              |201|"false"|

      # with additional fields
      |"{\"userId\": 1, \"title\": \"title-1\",\"body\": \"post-msg-1\", \"additional-field\": \"additional-field-value\"}"|201|"false"|


  Scenario Outline: Creating post via user endpoint "/users/user_id/posts"
    When form the test input data using the following input files <post_input_data> for posts endpoint
    Then create post via user on social media and validate the response <user_id>, <expected_status_code> and <validate_response_body>

    Examples:
    |user_id|post_input_data|expected_status_code|validate_response_body|
    # all the fields provided
    |1|"{\"title\": \"title-1\",\"body\": \"post-msg-1\"}"   |201|"true"|
    |10|"{\"title\": \"title-10\",\"body\": \"post-msg-10\"}"|201|"true"|

     # missing mandatory parameters status code should be 400, since the dummy service is returning 201 (keeping the status code as 201)
    |10|"{\"title\": \"title-10\"}"                          |201|"false"|
    |2 |"{}"                                                 |201|"false"|

    # invalid user id - status code (hould be 400, since the dummy service is returning 201 (keeping the status code as 201)
    |20|"{\"title\": \"title-1\",\"body\": \"post-msg-1\"}"  |201|"false"|


  Scenario Outline: Get all posts present on social media "/posts"
    When get all the posts present in social media <expected_status_code> and validate the total posts present is <expected_total_posts>

    Examples:
    |expected_status_code|expected_total_posts|
    |200                 |100                 |

  Scenario Outline: Get post by id present on social media "/posts/post_id"
    When get the post present in social media by postid <post_id>
    And validate the received post contains same id or not <post_id>, <expected_status_code> and <validate_response>

    Examples:
    # valid post id
      |post_id|expected_status_code|validate_response|
      |1     |200                   |"true"           |
      |100   |200                   |"true"           |

    # post id which is not existing in social media
      |110   |404                   |"false"          |
      |0     |404                   |"false"          |

  Scenario Outline: Get post by user id present on social media "/user/id/posts"
    When get the post present in social media by userid <user_id>
    And validate the received post contains same user id or not <user_id>, <expected_status_code> and <validate_response>

    Examples:
    # valid user id
      |user_id|expected_status_code|validate_response|
      |1    |200                   |"true"           |
      |10   |200                   | "true"          |

    # post id which is not existing in social media (expecting 404, but receiving 200 from sample service (so keeping 200))
      |12   |200                   |"false"           |

  Scenario Outline: Update post present on social media "/posts/post_id"
    When form the test input data using the following input files <post_update_data> for posts endpoint
    Then update the post present in social media by post_id <post_id> and validate the response is updated or not <expected_status_code>, <validate_response>

    Examples:
    |post_id|post_update_data|expected_status_code|validate_response|
    # positive scenarios
    |1|"{\"title\": \"title-1\",\"body\": \"updated-post-msg-1\", \"userId\": 1}"  |200|"true"|
    |1|"{\"title\": \"updated-title-10\",\"body\": \"post-msg-10\", \"userId\": 1}"|200|"true"|

     # Negative scenarios (valid post id, but body missing mandatory fields. Expecting 404, but receiving 200 from sample service)
    |1|"{\"id\": \"101\"}"                                                       |200|"false"|
    |1 |"{}"                                                                     |200|"false"|

    # Negative scenarios (invalid post id,Expecting 404, but receiving 200 from sample service)
    |20|"{\"title\": \"title-1\",\"body\": \"post-msg-1\"}"                      |200|"false"|

  Scenario Outline: Delete the post present on social media "posts/post_id"
    When delete the post using post_id <post_id> and verify the response is <expected_status_code>, <validate_response>

    Examples:
    |post_id|expected_status_code|validate_response|
    #positive scenarios
    |1      |200                 |"true"           |
    |10     |200                 |"true"           |

    #negative scenarios (invalid post id,Expecting 404, but receiving 200 from sample service)
    |101    |200                 |"false"          |
    |0      |200                 |"false"          |

