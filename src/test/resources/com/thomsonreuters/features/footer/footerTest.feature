Feature: [809111]As a user, I want to see the "Legal Information" and "Privacy" link functional on the footer

  Background:
    Given ANZ user is logged in

  Scenario Outline: User verifies the functionality of "Privacy" Information"
    When user should see footer
    When user should see the "How are we using your information?" link
    And user should see the "<Link>" link
    Then user clicks on "<Link>" link
    Then user should see Private Policy and Cookies page in the new tab

    Examples:
      | Link    |
      | Privacy |

  Scenario Outline: User verifies the functionality of "Legal Information"
    When user should see footer
    When user should see the "How are we using your information?" link
    And user should see the "<Link>" link
    Then user clicks on "<Link>" link
    And user should see the "<PageTitle>" page is opened

    Examples:
      | Link                               | PageTitle                         |
      | How are we using your information? | How we are using your information |

  Scenario: User verifies the functionality of "Legal Information"
    When user clicks on "Permitted Use FAQs" link
    Then user was taken to url "https://support.thomsonreuters.com.au/sites/default/files/2018-07/Permitted%20Use%20FAQs.pdf"


  @gold
  Scenario: [809111]User verifies the "Footer Link Items" for the respetive Page's links
    When user should see footer
    Then user should see the following FooterLinks under heading "About" with links to pages
      | FooterLink          | LinksToPage                               |
      | About Practical Law | /Browse/Home/About/AboutUs                |
      | Testimonials        | /Browse/Home/About/Testimonials           |
      | Careers             | http://thomsonreuters.com/en/careers.html |
    Then user should see the following FooterLinks under heading "Our team" with links to pages
      | Meet the Team      | /Browse/Home/About/OurTeam       |
      | Advisory Board     | /Browse/Home/About/AdvisoryBoard |
      | Contributing Firms | /Browse/Home/About/Contributors  |
    Then user should see the following FooterLinks under heading "Product support" with links to pages
      | User Guides      | training.thomsonreuters.com.au/products/practical-law-au/ |
      | Request Training | training.thomsonreuters.com.au/request-training/          |
      | Feedback         | javascript                                                |

  Scenario: [838706] Footer is not dislocated to the middle of the page
    And ANZ user navigates directly to document with guid "Id50b77a49d2f11e598dc8b09b4f043e0"
    When the user scrolled to the bottom of the document
    Then the footer is displayed below the end of the document
