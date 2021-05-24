Feature: [809948][850090] Link to another Practical Law web site

  Scenario Outline: Logged in User verifies the Footer links and styling-<id>
    Given ANZ user is logged in
    Then user should see the following countries with respective links
      | Australia | http://anzlaw.                    |
      | UK        | /PLCTransfer.html?domainKey=PLCUK |
      | US        | /PLCTransfer.html?domainKey=PLCUS |
      | Canada    | /PLCTransfer.html?domainKey=PLCCA |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab
    @qed
    Examples:
      | countryName | url             | websiteTitle             | id |
      | US          | https://1.next. | US Home \| Practical Law | 1  |
    Examples:
      | countryName | url                      | websiteTitle                          | id |
      | UK          | https://uk.practicallaw. | UK Home \| Practical Law              | 2  |
      | Canada      | https://ca.practicallaw. | Practical Law Canada \| Practical Law | 3  |
  @archived
    Examples:
      | countryName | url                                                                 | websiteTitle                     |
      | Global      | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global       | Global Homepage \| Practical Law |
      | China       | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China | Global \| China \| Practical Law |

    @demo
  Scenario Outline: Logged in User verifies the Footer links and styling -US(demo)-<id>
    Given ANZ user is logged in
    Then user should see the following countries with respective links
      | Australia | http://anzlaw.                    |
      | UK        | /PLCTransfer.html?domainKey=PLCUK |
      | US        | /PLCTransfer.html?domainKey=PLCUS |
      | Canada    | /PLCTransfer.html?domainKey=PLCCA |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab
    Examples:
      | countryName | url             | websiteTitle             | id |
      | US          | https://a.next. | US Home \| Practical Law | 1  |

  Scenario: Logged in User verifies the Westlaw AU  links
    Given ANZ user is logged in
    When the user selects "Resources" tab
    And the user selects "Westlaw AU"
    Then the user is taken to the login page in WLAU

  #open web user tests
  Scenario Outline: Open Web User verifies the Footer links and styling-<id>
    Given PL+ ANZ user navigates to home page
    Then user should see the following countries with respective links
      | Australia | http://anzlaw.                    |
      | UK        | /PLCTransfer.html?domainKey=PLCUK |
      | US        | /PLCTransfer.html?domainKey=PLCUS |
      | Canada    | /PLCTransfer.html?domainKey=PLCCA |
    And the user selects "<countryName>"
    And the Practical Law Country "<url>" web site "<websiteTitle>" will be launched in the same window and tab
    Examples:
      | countryName | url                      | websiteTitle                          | id |
      | US          | https://1.next.          | US Home \| Practical Law              | 1  |
      | UK          | https://uk.practicallaw. | UK Home \| Practical Law              | 2  |
      | Canada      | https://ca.practicallaw. | Practical Law Canada \| Practical Law | 3  |
  @archived
    Examples:
      | countryName | url                                                                 | websiteTitle                     |
      | Global      | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global       | Global Homepage \| Practical Law |
      | China       | https://uk.practicallaw.thomsonreuters.com/Browse/Home/Global/China | Global \| China \| Practical Law |

  Scenario: Open Web User verifies the Westlaw AU  links
    Given PL+ ANZ user navigates to home page
    When the user selects "Resources" tab
    And the user selects "Westlaw AU"
    Then the user is taken to the login page in WLAU
       
