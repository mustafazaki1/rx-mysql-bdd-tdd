Feature: user_login
  Scenario Outline: login_success
    Given user
    When he enters <user_name>
    Then print welcome
    Examples:
      | user_name |
      | "ssadas"  |