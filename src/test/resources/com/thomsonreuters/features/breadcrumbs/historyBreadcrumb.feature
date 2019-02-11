Feature: To view Breadcrumbs for History Page

Background: 
    Given ANZ user is logged in

 Scenario Outline: Verify that logged in PLAU user can see single “Home > History” breadcrumb trail when switching between History Tabs (“History: Searches”, “History: Documents”, “History: All History”)
 
    When the user open 'Company Law' link
    And the user open 'Corporate governance' link
    And the user selects the document 'Appointment of directors'
    When the user searches for term "tax"
    And the user come back on to Home page
    When the user open 'Practice Areas' link
    And the user open 'Employment' link
    When the user clicks on 'History' link on the header
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"
    When the user clicks on 'Documents' tab on the History page
    And  the user opens the document '1' link
    Then the user verifies that the complete breadcrumb is "<Breadcrumb1>"
    When the user clicks on 'History' link on the header
    When the user clicks on 'Searches' tab on the History page
    And  the user opens the document '1' link
    Then the user verifies that the complete breadcrumb is "<Breadcrumb2>"
       
   Examples: 
      | Breadcrumb   |Breadcrumb1                                | Breadcrumb2                  |      
      |Home > History| Home > History > Appointment of directors |Home >History >Search Results |
      

 Scenario Outline: Verify that logged in PLAU user can see clickable "History" breadcrumb in the “Home > History” breadcrumb trail when navigating to a document or Search Results page from History pages (“History: Searches”, “History: Documents”, “History: All History”);  and navigate back to History page by clicking on the History breadcrumb.
    When the user open 'Commercial' link
    And the user open 'Supply of goods and services' link
    And the user selects the document 'Comparative table of sale of goods legislation'
    When the user searches for term "law"
    And the user come back on to Home page
    When the user open 'Practice Areas' link
    And the user open 'Corporate' link
    When the user clicks on 'History' link on the header
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"
    When the user clicks on 'Documents' tab on the History page
    And  the user opens the document '1' link
    Then the user verifies that the complete breadcrumb is "<Breadcrumb1>"
    When the user clicks on the 'History' link in the breadcrumb
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"
    And the user is presented with a page with title "<PageTitle>"
    When the user clicks on 'History' link on the header
    When the user clicks on 'Searches' tab on the History page
    And  the user opens the document '1' link
    Then the user verifies that the complete breadcrumb is "<Breadcrumb2>"
    When the user clicks on the 'History' link in the breadcrumb
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"
    And the user is presented with a page with title "<PageTitle2>"
    When the user clicks on 'All_History' tab on the History page
    And  the user opens the document '1' link
    When the user clicks on the 'History' link in the breadcrumb
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"
    And the user is presented with a page with title "<PageTitle3>"
    
   Examples: 
      | Breadcrumb   |Breadcrumb1                                                    |PageTitle         |  Breadcrumb2                 |PageTitle2       |PageTitle3          |
      |Home > History| Home >History >Comparative table of sale of goods legislation |History: Documents|Home >History >Search Results |History: Searches|History: All History|
      