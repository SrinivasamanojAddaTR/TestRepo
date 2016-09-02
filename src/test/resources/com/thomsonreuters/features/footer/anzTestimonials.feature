Feature: [809375]As a PL Australia User (PLAU) I want to view the "Testimonials" pages So that I can find out what users and clients think of the Practical Law web site
  [809456]As a PL Australia User (PLAU) I want to navigate from one our team tab to another our team tab So that I can find out information about the practical law web site
  [809401]As a PL Australia User (PLAU) i want to navigate from one our team tab to another our team tab So that I can find out information about the practical law team and contributing firms

  Background: 
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |

  Scenario Outline: 
    When user clicks on "<Page>" link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    And tabs are displayed on about company page
      | About us     |
      | Testimonials |
      | Careers      |
    And tab "<Tab>" on about company page is active
    And user selects a tab "<tabToSelect>" on about company page and this tab becomes active
    Then the user verifies that the current PageTitle contains '<pageTitleAfterClickonTab>'

    Examples: 
      | Page                | pageTitle    | Tab          | tabToSelect  | pageTitleAfterClickonTab |
      | Testimonials        | Testimonials | Testimonials | About us     | About us                 |
      | About Practical Law | About us     | About us     | Testimonials | Testimonials             |

  Scenario Outline: 
    When user clicks on "<Page>" link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    When the user selects a tab "<tabToSelect>" within the table of contents
    And tab "<tabToSelect>" in Table of Contents is active

    Examples: 
      | Page         | pageTitle    | tabToSelect |
      | Testimonials | Testimonials | Law firms   |
      | Testimonials | Testimonials | Companies   |
      | Testimonials | Testimonials | The Bar     |

  Scenario Outline: [809401] As a PL Australia User (PLAU) I want to navigate from one our team tab to another our team tab So that I can find out information about the practical law team and contributing firms
    When user clicks on "<Page>" link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    And tabs are displayed on about company page
      | Team members        |
      | Contributors        |
      | Advisory Boards     |
    And tab "<Tab>" on about company page is active
    And user selects a tab "<tabToSelect>" on about company page and this tab becomes active
    Then the user verifies that the current PageTitle contains '<pageTitleAfterClickonTab>'

    Examples: 
      | Page                | pageTitle           | Tab                 | tabToSelect         | pageTitleAfterClickonTab |
      | Meet the Team       | Our team            | Team members        | Contributors 		  | Contributors             |
      | Contributing Firms  | Contributors        | Contributors        | Advisory Boards     | Advisory board           |
      | Advisory Board      | Advisory board      | Advisory Boards     | Team members        | Our team                 |

  Scenario: [809456]As a PL Australia User (PLAU) I want to navigate from one our team tab to another our team tab So that I can find out information about the practical law web site
    When user clicks on "Testimonials" link
    Then the user verifies that the current PageTitle contains 'Testimonials'
    And tabs are displayed on about company page
      | About us     |
      | Testimonials |
      | Careers      |
    And user selects a tab "Careers" on about company page
    Then user was taken to url "http://thomsonreuters.com/en/careers.html"