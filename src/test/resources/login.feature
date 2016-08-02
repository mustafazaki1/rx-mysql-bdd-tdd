Feature: user_login
  Scenario Outline: login_success
    Given user
    When he enters <user_name>
    And he enters <password>
    Then print welcome
    Examples:
      | user_name | password |
