Feature: [809111]As a user, I want to see the "Legal Information" and "Privacy" link functional on the footer

  Background:
    Given ANZ user is logged in

  Scenario Outline: User verifies the functionality of "Privacy" Information"-<id>
    When user should see footer
    When user should see the "How are we using your information?" link
    And user should see the "<Link>" link
    Then user clicks on "<Link>" link
    Then user should see Private Policy and Cookies page in the new tab

    Examples:
      | Link    | id |
      | Privacy | 1  |

  Scenario Outline: User verifies the functionality of "Legal Information"-<id>
    When user should see footer
    When user should see the "How are we using your information?" link
    And user should see the "<Link>" link
    Then user clicks on "<Link>" link
    And user should see the "<PageTitle>" page is opened

    Examples:
      | Link                               | PageTitle                         | id |
      | How are we using your information? | How we are using your information | 1  |

  Scenario: User verifies the functionality of "Legal Information"
    When user clicks on "Permitted Use FAQs" link
    Then user was taken to url "https://support.thomsonreuters.com.au/sites/default/files/2018-07/Permitted%20Use%20FAQs.pdf"

  @gold
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

  Scenario: [838706] Footer is not dislocated to the middle of the page
    And ANZ user navigates directly to document with guid "Id50b77a49d2f11e598dc8b09b4f043e0"
    When the user scrolled to the bottom of the document
    Then the footer is displayed below the end of the document
