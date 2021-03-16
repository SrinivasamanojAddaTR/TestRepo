Feature: [808905]As a PLAU User, I want to view the home page So that I can view the practice areas, resources and international materials that are available in the PLAU web site

  Scenario Outline: User verifies the Pratice area pages and its relative tabs
    Given ANZ user is logged in
    And user opens "Practice Areas" link
    And user navigates to PA page "<PA>"
    Then user verifies the "<PA>" page
    And user verifies the following tabs are displayed
      | Topics    |
      | Resources |
    When user selects the following tab and see the relative links or content
      | Tab       | Content                                                                                                          |
      | Topics    | <Topics>                                                                                                         |
      | Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary |
    Examples:
      | PA                     | Topics                                                                             |
      | Company Law            | Company administration and meetings, Company formation and constitution, Directors |
      | Corporate Transactions | Share acquisitions: private                                                        |
      | Employment             | Federal discrimination and harassment, Ill and injured employees                   |