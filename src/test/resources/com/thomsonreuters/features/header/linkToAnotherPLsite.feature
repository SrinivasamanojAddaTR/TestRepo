Feature: [809948][850090] Link to another Practical Law web site

#bug 869259 [PLAU] all links to Westlaw AU are failing

  Scenario Outline: Logged in User verifies the "Country Toggle Drop-down" links and styling
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When user hovers over the country toggle dropdown
    Then user should see the following countries with respective links
      | Country    | Link                                                                                             |
      | Australia  | https://au.practicallaw.demo.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default) |
      | Australia  | https://au.practicallaw.qed.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default)  |
      | UK         | https://uk.practicallaw.thomsonreuters.com/                                                      |
      | US         | http://us.practicallaw.com/                                                                      |
      | Canada     | http://ca.practicallaw.com/                                                                      |
      | Global     | http://global.practicallaw.com/                                                                  |
      | Westlaw AU | https://a.au.practicallaw.demo.thomsonreuters.com/transfer.html?domainKey=WestlawAU              |
      | Westlaw AU | https://a.au.practicallaw.qed.thomsonreuters.com/transfer.html?domainKey=WestlawAU               |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab

    Examples: 
      | countryName | url                                                    | websiteTitle                  |
      | US          | http://us.practicallaw.com/                            | Practical Law - Home - US     |
      | UK          | https://uk.practicallaw.thomsonreuters.com/Search/Home | UK Homepage \| Practical Law  |
      | Canada      | http://ca.practicallaw.com/                            | Practical Law - Home - Canada |
      | Global      | http://global.practicallaw.com/                        | Practical Law - Home - Global |


  Scenario: Logged in User verifies the Westlaw AU  links
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2  |

    When user hovers over the country toggle dropdown  
    And the user selects "Westlaw AU"
    And the user is taken to the login page in WLAU
 
	
	#open web
  Scenario Outline: User verifies the "Country Toggle Drop-down" links and styling
    Given the user navigates to the main PLANZ page
    And ANZ user is not logged in
    When user hovers over the country toggle dropdown
    Then user should see the following countries with respective links
      | Country    | Link                                                                                             |
      | Australia  | https://au.practicallaw.demo.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default) |
      | Australia  | https://au.practicallaw.qed.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default)  |
      | UK         | https://uk.practicallaw.thomsonreuters.com/                                                      |
      | US         | http://us.practicallaw.com/                                                                      |
      | Canada     | http://ca.practicallaw.com/                                                                      |
      | Global     | http://global.practicallaw.com/                                                                  |
      | Westlaw AU | https://au.practicallaw.demo.thomsonreuters.com/transfer.html?domainKey=WestlawAU                |
      | Westlaw AU | https://au.practicallaw.qed.thomsonreuters.com/transfer.html?domainKey=WestlawAU                 |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab

    Examples: 
      | countryName | url                                                    | websiteTitle                  |
      | US          | http://us.practicallaw.com/                            | Practical Law - Home - US     |
      | UK          | https://uk.practicallaw.thomsonreuters.com/Search/Home | UK Homepage \| Practical Law |
      | Canada      | http://ca.practicallaw.com/                            | Practical Law - Home - Canada |
      | Global      | http://global.practicallaw.com/                        | Practical Law - Home - Global |

  Scenario: Open Web User verifies the Westlaw AU  links
    Given the user navigates to the main PLANZ page
    And ANZ user is not logged in
    When user hovers over the country toggle dropdown  
    And the user selects "Westlaw AU"
    And the user is taken to the login page in PLAU
       
