Feature: [808912]As a PLAU User, I want to browse down to a Country page So that I can view the Practical Law coverage for that country law

  Scenario:  User verifies the International pages and its relative tabs
   Given ANZ user is logged in with following details
	  | userName         | ANZtestuser2 |
   When user selects the following tab and see the relative links or content
      |tab | content |
      |International | Countries, International subscriptions, International practice areas,Country Q&A comparison tool |
   Then user should see following countries with "Country Q&A" and "All Country resources" tabs
    | Argentina    |
    | Austria      |
    | Brazil       |
    | Canada       |
    | China        |
    | France       |
    | Germany      |
    | Hong Kong    |
    | India        |
    | Indonesia    |
    | Italy        |
    | Japan        |
    | Mexico       |
    | Norway       |
    | Russian Federation |
    | Singapore    |
    | South Africa |
    | South Korea  |
    | Spain        |
    | Sweden       |
    | Switzerland  |
    | Turkey       |
    | United Kingdom |
    | United States |
    Then user selects following links and should see their respective pages
    |International transaction guides|
    |Global guides |
    |Arbitration   |
    |EU Law        |
    |Competition   |
    |Country Q&A comparison tool|


  Scenario:  User verifies the International links using Browse drop-down
    Given ANZ user is logged in with following details
	  | userName         | ANZtestuser2 |
    And user clicks on "Browse Menu" dropdown
    Then user selects following links and should see their respective pages through BrowseMenu
      |International transaction guides|
      |Global guides |
      |Arbitration   |
      |EU Law        |
      |Competition   |


Scenario Outline: [835281] User uses browse menu to navigate to Global Country page
   Given ANZ user is logged in with following details
	  | userName         | ANZtestuser2 |
    When user clicks on "Browse Menu" dropdown
    And  user clicks on following sub-menu and see the respective links according to the design
      | International  |
	And user clicks on "<country>" in International subscriptions
	Then user was taken to url "<url>"
	Examples:
	|country	|url|
	|China		|http://global.practicallaw.com/country/china|
	|Canada		|http://ca.practicallaw.com/|
	|US			|http://us.practicallaw.com/|