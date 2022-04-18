Feature: Use case for validating comment's endpoint on Json Place Holder.
  Description: Purpose of this usecase is to validate the endpoint "/comments" with different test data.

  Background: Setup steps
    Given provided the Json place holder service is up and running, create the test environment for comments endpoint.

  Scenario Outline: Creating comments via endpoint
    When form the test input data using the following input files <comment_input_data> for comments endpoint
    Then create comment on social media and validate the response <expected_status_code> and <validate_response_body>
    Examples:
      |comment_input_data|expected_status_code|validate_response_body|
      # all the fields provided
      |"{\"postId\": 1, \"name\": \"comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\"}"|201|"true"|

      # missing mandatory parameters status code should be 400, since the dummy service is returning 201 (keeping the status code as 201))
      |"{\"postId\": 1, \"name\": \"comment-name-1\", \"body\": \"comment-body-1\"}" |201|"false"|
      |"{\"postId\": 1, \"email\": \"user@email\", \"body\": \"comment-body-1\"}"                |201|"false"|
      |"{\"postId\": 1, \"name\": \"comment-name-1\", \"email\": \"user@email\"}"                |201|"false"|
      |"{\"postId\": 1}"                                                                         |201|"false"|
      |"{}"                                                                                      |201|"false"|

      # with additional fields
      |"{\"postId\": 1, \"name\": \"comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\", \"additional-field\": \"additional-field-value\"}"|201|"false"|


  Scenario Outline: Creating comment via post endpoint
    When form the test input data using the following input files <comment_input_data> for comments endpoint
    Then create comment via post on social media and validate the response <post_id>, <expected_status_code> and <validate_response_body>

    Examples:
    |post_id|comment_input_data|expected_status_code|validate_response_body|
    # all the fields provided
    |1|"{\"name\": \"comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\"}"   |201|"true"|
    |10|"{\"name\": \"comment-name-10\", \"email\": \"user@email\", \"body\": \"comment-body-10\"}"|201|"true"|

     # missing mandatory parameters status code should be 400, since the dummy service is returning 201 (keeping the status code as 201)
    |10|"{\"name\": \"comment-name-1\"}"                     |201|"false"|
    |2 |"{}"                                                 |201|"false"|

    # invalid user id - status code (hould be 400, since the dummy service is returning 201 (keeping the status code as 201)
    |20|"{\"name\": \"comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\"}"  |201|"false"|


  Scenario Outline: Get all comments present on social media
    When get all the comments present in social media <expected_status_code> and validate the total comments present is <expected_total_posts>

    Examples:
    |expected_status_code|expected_total_posts|
    |200                 |500                 |

  Scenario Outline: Get comment by id present on social media
    When get the comment present in social media by comment_id <comment_id>
    And validate the received comment contains same id or not <comment_id>, <expected_status_code> and <validate_response>

    Examples:
    # valid post id
      |comment_id|expected_status_code|validate_response|
      |1     |200                   |"true"           |
      |100   |200                   |"true"           |

    # post id which is not existing in social media
      |610   |404                   |"false"          |
      |0     |404                   |"false"          |

  Scenario Outline: Get comment by post id present on social media
    When get the comment present in social media by post_id <post_id>
    And validate the received comment contains same post_id or not <post_id>, <expected_status_code> and <validate_response>

    Examples:
    # valid post id
      |post_id|expected_status_code|validate_response|
      |1    |200                   |"true"           |
      |10   |200                   | "true"          |

    # post id which is not existing in social media (expecting 404, but receiving 200 from sample service (so keeping 200))
      |112   |200                   |"false"           |

  Scenario Outline: Update comment present on social media
    When form the test input data using the following input files <comment_update_data> for comments endpoint
    Then update the comment present in social media by comment_id <comment_id> and validate the response is updated or not <expected_status_code>, <validate_response>

    Examples:
    |comment_id|comment_update_data|expected_status_code|validate_response|
    # positive scenarios
    |1|"{\"postId\": 1, \"id\":1, \"name\": \"udpated-comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\"}"  |200|"true"|
    |1|"{\"postId\": 1, \"id\":1, \"name\": \"comment-name-1\", \"email\": \"user@email\", \"body\": \"updated-comment-body-1\"}"  |200|"true"|

     # Negative scenarios (valid comment id, but body missing mandatory fields. Expecting 404, but receiving 200 from sample service)
    |1|"{\"id\": \"101\"}"                                                       |200|"false"|
    |1 |"{}"                                                                     |200|"false"|

    # Negative scenarios (invalid comment id,Expecting 404, but receiving 500 from sample service)
    |600|"{\"postId\": 1, \"id\":600, \"name\": \"udpated-comment-name-1\", \"email\": \"user@email\", \"body\": \"comment-body-1\"}" |500|"false"|

  Scenario Outline: Delete the comment present on social media
    When delete the comment using comment_id <comment_id> and verify the response is <expected_status_code>, <validate_response>

    Examples:
    |comment_id|expected_status_code|validate_response|
    #positive scenarios
    |1      |200                 |"true"           |
    |10     |200                 |"true"           |

    #negative scenarios (invalid comment id,Expecting 404, but receiving 200 from sample service)
    |601    |200                 |"false"          |
    |0      |200                 |"false"          |

