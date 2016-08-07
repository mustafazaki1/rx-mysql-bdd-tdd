Feature: user_history
  Scenario Outline: get_histor
    Given a user
    When he enters <user_name>
    Then I view user's history <urlList>
    Examples:
      | user_name |
      | "ssadas"  |