Feature: Use case for Listing the user's in different ways
  Description: Purpose of this usecase is to validate the endpoint (https://jsonplaceholder.typicode.com/users) on Json Place Holder.

  Background: Setup steps
    Given Set the Base url which needs to be tested "https://jsonplaceholder.typicode.com"
    Given Create the test environment for users endpoint.

  Scenario Outline: Get all users present on social media "/users"
    When get all the users present in social media <expected_status_code> and validate the total users present is <expected_total_users>

    Examples:
      |expected_total_users|expected_status_code|
      |10                 |200                  |


  Scenario Outline: Get user by id present on social media "/users/user_id"
    When get the user present in social media by user_id <user_id>
    And validate the received user contains same id or not <user_id>, <expected_status_code> and <validate_response>

    Examples:
    # valid user id
      |user_id|expected_status_code|validate_response|
      |2     |200                   |"true"           |

    # invalid user id which is not existing in social media
      |11    |404                   |"false"           |
      |0     |404                   |"false"           |

  Scenario Outline: Get all users by post_id present on social media "/posts/id/users/id"
    When get all the users by post_id present in social media <post_id>, <expected_status_code>

    Examples:
    # valid post id
      |post_id|expected_status_code|
      |1    |200                   |
      |10   |200                   |

    # invalid post id which is not existing in social media (expecting 404, but receiving 200 from sample service (so keeping 200))
      |101   |200                   |

  Scenario Outline: Get all users by comment_id present on social media "/comments/id/users"
    When get all the users by comment_id present in social media <comment_id>, <expected_status_code>

    Examples:
    # valid comment id
      |comment_id|expected_status_code|
      |1    |200                   |
      |20   |200                   |

    # comment id which is not existing in social media (expecting 404, but receiving 200 from sample service (so keeping 200))
      |700   |200                   |