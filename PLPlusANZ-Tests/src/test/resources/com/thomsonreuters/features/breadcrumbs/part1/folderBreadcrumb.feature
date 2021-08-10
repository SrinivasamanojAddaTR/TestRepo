Feature: To view Breadcrumbs for Folders Page

Background: 
    Given ANZ user is logged in
    When API cleans all folders and history and user relogs in
@ffh
Scenario: Verify that logged in PLAU user can see a breadcrumb trail with a clickable Folders page link on PLAU site when navigating to a Document from Folders and navigate back to the Folders page when clicking on this link. 
    
    When the user open 'Practice Areas' link
    And the user open 'Company Law' link
    And the user open 'Company administration and meetings' link
    And the user selects the document 'Corporate governance'
    Then user store document's title and guid
    And the user adds the current document to new "testbreadcrumb" folder with parent folder "root" 
    Then the user come back on to Home page
    When the user open 'Practice Areas' link
    And the user open 'Commercial' link
    And the user open 'Franchising' link
    Then the user clicks on 'Folders' link on the header
    And check document present in the "testbreadcrumb" folder
    Then the user verifies that the complete breadcrumb is "Home > Folders"
    When  user clicks on document moved to folder
    Then the user verifies that the complete breadcrumb is "Home > Folders > Corporate governance"

 
      
      