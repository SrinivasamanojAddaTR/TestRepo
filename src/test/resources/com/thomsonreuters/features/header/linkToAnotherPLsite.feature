Feature: [809948][850090] Link to another Practical Law web site

#bug 869259 [PLAU] all links to Westlaw AU are failing

  Scenario Outline: Logged in User verifies the "Country Toggle Drop-down" links and styling
    Given ANZ user is logged in
    When user clicks on the country toggle dropdown
    Then user should see the following countries with respective links
      | Country    | Link                                                                                             |
      | UK         | http://uk.practicallaw.tr.com/                                                                   |
      | US         | http://us.practicallaw.tr.com/                                                                   |
      | Australia  | https://au.practicallaw.demo.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default) |
      | Australia  | https://au.practicallaw.qed.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default)  |
      | Canada     | http://ca.practicallaw.com/                                                                      |
      | China      | http://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China                               |
      | Global     | http://uk.practicallaw.thomsonreuters.com/Browse/Home/Global                                     |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab

    Examples: 
      | countryName | url                                                                    | websiteTitle                           |
      | US          | https://signon.thomsonreuters.com/?productid=PLCUS&viewproductid=PLCUS | Practical Law US (New Platform) Signon |
      | UK          | https://uk.practicallaw.thomsonreuters.com/Search/Home                 | Home \| Practical Law                  |
      | Canada      | http://ca.practicallaw.com/                                            | Practical Law - Home - Canada          |
      | Global      | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global          | Global Homepage \| Practical Law       |
      | China       | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China    | Global \| China \| Practical Law       |


  Scenario: Logged in User verifies the Westlaw AU  links
    Given ANZ user is logged in
    When the user selects "Resources" tab
    And the user selects "Westlaw AU"
    Then the user is taken to the login page in WLAU
 
	
	#open web
  Scenario Outline: User verifies the "Country Toggle Drop-down" links and styling
    Given the user navigates to the main PLANZ page
    And ANZ user is not logged in
    When user clicks on the country toggle dropdown
    Then user should see the following countries with respective links
      | Country    | Link                                                                                             |
      | UK         | http://uk.practicallaw.tr.com/                                                                   |
      | US         | http://us.practicallaw.tr.com/                                                                   |
      | Australia  | https://au.practicallaw.demo.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default) |
      | Australia  | https://au.practicallaw.qed.thomsonreuters.com/?transitionType=Default&contextData=(sc.Default)  |
      | Canada     | http://ca.practicallaw.com/                                                                      |
      | China      | http://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China                               |
      | Global     | http://uk.practicallaw.thomsonreuters.com/Browse/Home/Global                                     |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab

    Examples:
      | countryName | url                                                                    | websiteTitle                           |
      | US          | https://signon.thomsonreuters.com/?productid=PLCUS&viewproductid=PLCUS | Practical Law US (New Platform) Signon |
      | UK          | https://uk.practicallaw.thomsonreuters.com/Search/Home                 | Home \| Practical Law                  |
      | Canada      | http://ca.practicallaw.com/                                            | Practical Law - Home - Canada          |
      | Global      | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global          | Global Homepage \| Practical Law       |
      | China       | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China    | Global \| China \| Practical Law       |

  Scenario: Open Web User verifies the Westlaw AU  links
    Given the user navigates to the main PLANZ page
    And ANZ user is not logged in
    When the user selects "Resources" tab
    And the user selects "Westlaw AU"
    Then the user is taken to the login page in WLAU
       
