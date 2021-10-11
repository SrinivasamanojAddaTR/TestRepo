Feature: [822634] Topics URL

  Background: 
    Given ANZ user is logged in

  Scenario Outline: Topics page URL-<id>
    When the user opens "<topicUrl>" url on PL AU website
    Then the user is presented with a page with header "<topicName>"
    And the page URL contains "<topicUrl>?sv=<practiceAreaRef>"
    And Add Page to Favorites icon is displayed

    Examples: 
      | topicUrl          | topicName                           | practiceAreaRef | id |
      | /topic/w-000-5049 | Discrimination & Harassment         | w-000-5039      | 1  |
      | /topic/w-000-5054 | Health, safety and incapacity       | w-000-5039      | 2  |
      | /topic/w-000-5069 | Unfair Dismissal                    | w-000-5039      | 3  |
      | /topic/w-000-6561 | Share acquisitions: private         | w-000-5040 	  | 4  |
      | /topic/w-000-6565 | Company administration and meetings | w-000-6549      | 5  |
      | /topic/w-000-6568 | Directors						    | w-000-6549	  | 6  |
      

  Scenario Outline: Topics page URL with multiple practice areas-<id>
    When user clicks on "<practiceArea>" link
    And user clicks on "<topicName>" link
    Then the user is presented with a page with header "<topicName>"
    And the page URL contains "<topicUrl>?sv=<practiceAreaRef>"

    Examples: 
      | topicUrl          | topicName                   | practiceArea           | practiceAreaRef | id |
      | /topic/w-000-6561 | Share acquisitions: private | Corporate Transactions | w-000-5040      | 1  |
