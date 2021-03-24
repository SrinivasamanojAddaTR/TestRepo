Feature: [850127]OPEN WEB (TEST ONLY) Footer Pages
  As a open web user
  I want to view and link to footer pages
  So that I can view and find out more information about practical law

  Background:
    Given PL+ ANZ user navigates to home page

  Scenario: [809111]User verifies the "Footer Link Items" for the respetive Page's links
    When user should see footer
    And user clicks on "About Practical Law" link
    Then user should see Tabs and corresponding urls
      | About us     | /Browse/Home/About/AboutUs                     |
      | Testimonials | /Browse/Home/About/Testimonials                |
      | Careers      | https://www.thomsonreuters.com/en/careers.html |
    And the user clicks on the browser back button
    When user should see footer
    And user clicks on "Our Team" link
    Then user should see Tabs and corresponding urls
      | Team members             | /Browse/Home/About/OurTeam                |
      | Advisory board           | /Browse/Home/About/AdvisoryBoard          |
      | Contributors             | /Browse/Home/About/Contributors           |
      | Australian Contributors  | /Browse/Home/About/AustralianContributors |
      | New Zealand Contributors | /Browse/Home/About/NewZealandContributors |
    And user clicks on "Product Support" link
    Then user was taken to url "https://support.thomsonreuters.com.au/product/practical-law-australia-incl-nz-resource-centre"
    And user clicks on "Request Training" link
    Then user was taken to url "https://support.thomsonreuters.com.au/request-training-0"

  Scenario Outline: User verifies the functionality of footer links that lead to internal pages
    When user clicks on "<footer>" link
    Then the user is presented with a page with header "<header>"
    And the page URL contains "<url>"
    Examples:
      | footer                             | header                            | url                                            |
      | About Practical Law                | About Us                          | /Browse/Home/About/AboutUs                     |
      | Meet the team                      | Our team                          | /Browse/Home/About/OurTeam                     |
      | How are we using your information? | How we are using your information | Browse/Home/About/HowWeAreUsingYourInformation |
  @archived
    Examples:
      | footer             | header       | url                             |
      | Contributing Firms | Contributors | /Browse/Home/About/Contributors |

  Scenario Outline: User verifies the functionality of footer links that lead to internal pages - indirect
    When user clicks on "<firstLink>" link
    When user clicks on "<footer>" link
    Then the user is presented with a page with header "<header>"
    And the page URL contains "<url>"
    Examples:
      | footer         | header         | url                              | firstLink           |
      | Testimonials   | Testimonials   | /Browse/Home/About/Testimonials  | About Practical Law |
      | Advisory board | Advisory board | /Browse/Home/About/AdvisoryBoard | Our Team            |

  Scenario Outline: User verifies the functionality of footer links that lead to external pages
    When user clicks on "<footer>" link
    Then user was taken to url "<url>"
    When user clicks on "<footer>" link
    And user should see the "<PageTitle>" page opened in new tab
    Examples:
      | footer           | url                                                                   | PageTitle                                  |
      | Product Support  | https://support.thomsonreuters.com.au/product/practical-law-australia | Practical Law Australia \| Thomson Reuters |
      | Request Training | https://support.thomsonreuters.com.au                                 | Request Training \| Thomson Reuters        |
      | Privacy          | https://www.thomsonreuters.com/en/privacy-statement.html              | Privacy statement \| Thomson Reuters       |
  @archived
    Examples:
      | footer       | url                                                                                                                   | PageTitle                                                               |
      | Master Terms | https://legal.thomsonreuters.com.au/terms/pdf/AU-Thomson-Reuters-Master-Service-Agreement-Master-Terms-v1.1-03-16.pdf | AU-Thomson-Reuters-Master-Service-Agreement-Master-Terms-v1.1-03-16.pdf |
      | Careers      | https://www.thomsonreuters.com/en/careers.html                                                                        | Careers \| Thomson Reuters                                              |

