Feature: As a PLAU user,I want a dropdown list to appear at the end of by breadcrumb,so that I can be presented with a list of documents that I've visited in my path to the document I'm viewing

  Background:
    Given ANZ user is logged in

  Scenario: Verify that logged in PLAU user can see a dropdown list when navigating from one document to another.
    When the user open 'Practice Areas' link
    And the user open 'Commercial' link
    And the user open 'Data protection and privacy' link
    Then the user selects the document 'Cybersecurity'
    And the user clicks on the 'Data issues in the financial services sector' link in the doc
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Cybersecurity |


  Scenario: Verify that logged in PLAU user can see clickable links to the Documents in the dropdown list when navigating from one document to another and navigate to the documents through these links.

    When the user open 'Practice Areas' link
    And the user open 'Company Law' link
    And the user open 'Company administration and meetings' link
    Then the user selects the document 'Corporate governance'
    And the user clicks on the 'Practice note, Company constitutions' link in the doc
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    When the user clicks on a "Corporate governance" in the dropdown
    Then the document opens correctly
    And document title is displayed as "Corporate governance"
    When the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Company constitutions |
    And the user should not view the current document in the dropdown
      | Corporate governance |

  Scenario: Verify that logged in PLAU user can see max 10 Documents in the dropdown list when navigating from one document to another.

    When the user open 'Practice Areas' link
    And the user navigates to practice area "Corporate Transactions" filtered by "Asset acquisitions" topic page
    And the user selects the document 'Asset purchases'
    And the user clicks on the 'Practice note, Share purchases' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Checklist, Strategic and financial buyers comparison chart' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Practice note, Corporate insolvency and related directors' duties' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Practice note, Structuring the purchase price: acquisitions' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Practice note, Completion accounts: acquisitions' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Standard documents, Asset purchase agreement: simultaneous signing and completion' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Share purchase agreement simultaneous signing and completion' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Assignment of commercial and retail leases in each state and territory' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Assignment and subleasing: practical leasing fundamentals' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Practice note, Transfer of shares' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Transfer of shares                                                     |
      | Assignment and subleasing: practical leasing fundamentals              |
      | Assignment of commercial and retail leases in each state and territory |
      | Share purchase agreement: simultaneous signing and completion          |
      | Asset purchase agreement: simultaneous signing and completion          |
      | Completion accounts: acquisitions                                      |
      | Structuring the purchase price: acquisitions                           |
      | Corporate insolvency and related directors' duties                     |
      | Strategic and financial buyers comparison chart                        |
      | Share purchases                                                        |
    When the user clicks on the 'Standard documents, Share transfer form' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views a list of the documents I viewed
      | Share transfer form                                                    |
      | Transfer of shares                                                     |
      | Assignment and subleasing: practical leasing fundamentals              |
      | Assignment of commercial and retail leases in each state and territory |
      | Share purchase agreement: simultaneous signing and completion          |
      | Asset purchase agreement: simultaneous signing and completion          |
      | Completion accounts: acquisitions                                      |
      | Structuring the purchase price: acquisitions                           |
      | Corporate insolvency and related directors' duties                     |
      | Strategic and financial buyers comparison chart                        |

  Scenario: Verify that logged in PLAU user can see most recent Document at the top of the dropdown list when navigating from one document to another.
    When the user open 'Practice Areas' link
    And the user navigates to practice area "Corporate Transactions" filtered by "Joint ventures" topic page
    And the user selects the document 'Joint ventures in Australia'
    And the user clicks on the 'Standard document, Shareholders' deed' link in the doc
    And the user goes back in browser
    And the user clicks on the 'Standard document, Priority deed' link in the doc
    And the user goes back in browser
    And the user clicks on the dropdown arrow at the end of the breadcrumb
    Then the user views The top list of the documents
      | Priority deed      |
      | Shareholders' deed |
   