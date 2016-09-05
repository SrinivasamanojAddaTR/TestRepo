Feature: [822634] Topics URL

  Background: 
    Given ANZ user is logged in

  Scenario Outline: Topics page URL
    When the user opens "<topicUrl>" url on PL AU website
    Then the user is presented with a page with header "<topicName>"
    And the page URL contains "<topicUrl>?sv=<practiceAreaRef>"
    And Add Page to Favorites icon is displayed

    Examples: 
      | topicUrl          | topicName                     | practiceAreaRef |
      | /topic/w-000-5049 | Discrimination & Harassment   | w-000-5039      |
      | /topic/w-000-5054 | Ill and injured employees     | w-000-5039      |
      | /topic/w-000-5069 | Unfair Dismissal              | w-000-5039      |
      | /topic/w-000-6561 | Share acquisitions: private   | w-000-5040 		|
      | /topic/w-000-6565 | Company administration and meetings  | w-000-6549 |
      | /topic/w-000-6568 | Directors						| w-000-6549	|
      

#the following scenario is not suitable for AU for now: no topics matching criteria
	@wip
  Scenario Outline: Topics page URL with multiple practice areas
    When the user opens "<topicUrl>" url on PL AU website
    Then the user is presented with several practice areas options
    When user clicks on "<practiceArea>" link
    Then the user is presented with a page with header "<topicName>"
    And the page URL contains "<topicUrl>?sv=<practiceAreaRef>"

    Examples: 
      | topicUrl          | topicName                   | practiceArea           | practiceAreaRef |
      | /topic/w-000-6561 | Share acquisitions: private | Corporate Transactions | w-000-5040      |
