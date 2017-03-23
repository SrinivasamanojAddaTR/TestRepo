Feature: [848113] Legal Updates Widget
As a PLAU User I want to quickly check the latest legal updates so that I can decide if I need further exploration

#LU widget contains only 2 updates. Needs to update tests when there will be 5 or more LU
  Scenario: Legal updates wiget on Legal updates page
    Given ANZ user is logged in
    When a user is on the Legal Updates Home page
    Then the user is presented with the Legal Updates widget
    And the user should see 5 updates on a "Legal updates" widget
    And "Legal updates" widget should display publication dates of documents
  	And the user should not be presented with an 'RSS' Link
    When the user clicks on the 'View all' link of the "Legal updates" widget
    Then the user should be presented with a list of LU documents
 
 
#LU widget contains only 1 updates. Needs to update tests when there will be 5 or more LU.
  Scenario: Legal updates wiget on Topic page
    Given ANZ user is logged in
    Given a user navigate to a "Federal unfair dismissal" Topic page from a "Employment" Practice Area page
    And the user is presented with the Legal Updates widget
    And the user should see 5 updates on a "Legal Updates" widget
    And "Legal Updates" widget should display publication dates of documents
    And the user should not be presented with an 'RSS' Link
    When the user clicks on the 'View all' link of the "Legal Updates" widget
    Then the user should be taken to the "Federal unfair dismissal" Topic LU results list
    Then the user should be presented with a list of LU documents

#LU widget contains only 2 updates. Needs to update tests when there will be 5 or more LU. 
  Scenario: Legal updates wiget on PA page with LU widget
    Given ANZ user is logged in
    Given a user is on a "Employment" PA page
    And the user is presented with the Legal Updates widget
    And the user should see 5 updates on a "Legal updates" widget
    And "Legal updates" widget should display publication dates of documents
    And the user should not be presented with an 'RSS' Link
    When the user clicks on the 'View all' link of the "Legal updates" widget
    Then the user should be taken to the "Employment" Topic LU results list
    Then the user should be presented with a list of LU documents
    
  # Scenario is modifies as legal updates widget has been added for Company Law and Corporate Transactions page.
   Scenario Outline: There is legal updates widget on following PA page
  	Given ANZ user is logged in
    Given a user is on a "<practiceArea>" PA page
    And the user is presented with the Legal Updates widget
    Examples: 
      | practiceArea               | 
      | Company Law                | 
      | Corporate Transactions     | 
