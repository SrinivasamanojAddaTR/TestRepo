@archived
Feature: [808912]As a PLAU User, I want to browse down to a Country page So that I can view the Practical Law coverage for that country law

  Scenario:  User verifies the International pages and its relative tabs
   Given ANZ user is logged in
   When user selects the following tab and see the relative links or content
      |tab | content |
      |International | Countries, International subscriptions, Country Q&A comparison tool |
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
   # |Arbitration   |
   # |EU Law        |
   # |Competition   |
    |Country Q&A comparison tool|


  Scenario:  User verifies the International links using Browse drop-down
    Given ANZ user is logged in with following details
	  | userName         | AUtestuser3 |
    And user clicks on "Browse Menu" dropdown
    Then user selects following links and should see their respective pages through BrowseMenu
      |International transaction guides|
      |Global guides |
      |Arbitration   |
      |EU Law        |
      |Competition   |

# commented the verification of Url for US as the Url has lrTS=20170323105558268, which is not constant.
# changed the script to verify if the country url contains the values

Scenario Outline: [835281] User uses browse menu to navigate to Global Country page-<id>
   Given ANZ user is logged in with following details
	  | userName         | AUtestuser3 |
    When user clicks on "Browse Menu" dropdown
    And  user clicks on following sub-menu and see the respective links according to the design
      | International  |
	And user clicks on "<country>" in International subscriptions
	And the page URL contains "<url>"
	#Then user was taken to url "<url>"
	Examples:
      |country	    |url                                          | id |
      |China		|https://uk.practicallaw.thomsonreuters.com/  | 1  |
      |Canada		|https://ca.practicallaw.thomsonreuters.com	  | 2  |
      |US			|https://content.next.westlaw.com/            | 3  |
		