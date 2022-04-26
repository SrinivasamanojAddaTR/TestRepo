@open_web
Feature: Verify that logged in Open Web user can see breadcrumb trail while navigated to document view page

  Background:
    Given PL+ ANZ user navigates to home page


  Scenario: Verify that open web  user can see a Document level in breadcrumb trail.
    When the user open 'Practice Areas' link
    And the user opens 'Employment' link
    And the user opens 'Employment contracts' link
    And the user selects the document 'Confidentiality'
    And the user verifies that the complete breadcrumb is " Home >Employment >Employment contracts >Confidentiality "


  Scenario: Verify that logged in PLAU user can see a dropdown list when navigating from one document to another.
    When the user open 'Practice Areas' link
    And the user open 'Commercial' link
    And the user open 'Topics' link
    And the user open 'Data protection and privacy' link
    Then the user selects the document 'Cybersecurity'
    And the user clicks on the Related Content 'Data issues in the financial services sector' link in the doc
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Cybersecurity |

  @archived
  Scenario: Verify that logged in PLAU user can see clickable links to the Documents in the dropdown list when navigating from one document to another and navigate to the documents through these links.

    When the user open 'Practice Areas' link
    And the user open 'Company Law' link
    And the user open 'Company administration and meetings' link
    Then the user selects the document 'Shareholder rights and remedies'
    And the user clicks on the 'Company constitutions' link in the doc
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    When the user clicks on a "Shareholder rights and remedies" in the dropdown
    Then the document opens correctly
    And document title is displayed as "Shareholder rights and remedies"
    When the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Company constitutions |
    And the user should not view the current document in the dropdown
      | Shareholder rights and remedies |

  @archived
  Scenario: Verify that logged in PLAU user can see max 10 Documents in the dropdown list when navigating from one document to another.

    When the user open 'Practice Areas' link
    And the user navigates to practice area "Corporate Transactions" filtered by "Asset acquisitions" topic page
    And the user selects the document 'Completion accounts: acquisitions'
    And the user clicks on the 'Alternatives to termination of employment for redundancy' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Dismissed' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Dividends' link in the doc
    And the user goes back in browser
    And the user clicks on the 'The prohibition on discrimination in employment' link in the doc
    And the user goes back in browser
    And the user clicks on the 'The statutory entitlement to redundancy pay' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Completion accounts: asset adjustment: asset purchase agreement' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Retention (warranty claims): asset purchase agreement' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Duty on asset acquisitions' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Duty on share and unit acquisitions' link in the doc
    And the user goes back in browser
    And the user clicks on the 'General protections' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Escrow instruction letter: share or asset purchases' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Escrow instruction letter: share or asset purchases             |
      | General protections                                             |
      | Duty on share and unit acquisitions                             |
      | Duty on asset acquisitions                                      |
      | Retention (warranty claims): asset purchase agreement           |
      | Completion accounts: asset adjustment: asset purchase agreement |
      | The statutory entitlement to redundancy pay                     |
      | The prohibition on discrimination in employment                 |
      | Dividends                                                       |
      | Dismissed                                                       |
    When the user clicks on the 'Checklist for making a jurisdictional objection in unfair dismissal proceedings' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Checklist for making a jurisdictional objection in unfair dismissal proceedings |
      | Escrow instruction letter: share or asset purchases                             |
      | General protections                                                             |
      | Duty on share and unit acquisitions                                             |
      | Duty on asset acquisitions                                                      |
      | Retention (warranty claims): asset purchase agreement                           |
      | Completion accounts: asset adjustment: asset purchase agreement                 |
      | The statutory entitlement to redundancy pay                                     |
      | The prohibition on discrimination in employment                                 |
      | Dividends                                                                       |

  @archived
  Scenario: Verify that logged in PLAU user can see most recent Document at the top of the dropdown list when navigating from one document to another.
    When the user open 'Practice Areas' link
    And the user navigates to practice area "Corporate Transactions" filtered by "Asset acquisitions" topic page
    And the user selects the document 'Completion accounts: acquisitions'
    And the user clicks on the 'Alternatives to termination of employment for redundancy' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Dismissed' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views The top list of the documents
      | Dismissed                                                |
      | Alternatives to termination of employment for redundancy |