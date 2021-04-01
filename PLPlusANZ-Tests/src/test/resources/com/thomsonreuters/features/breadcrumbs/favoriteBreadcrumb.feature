Feature: To view Breadcrumbs for Favorites Page

Background: 
    Given ANZ user is logged in

Scenario: Verify that logged in PLAU user can see a breadcrumb trail with a clickable Favourites page link on PLAU site when navigating to a Practice area page from Favourites and navigate back to the Favourites page when clicking on this link. 

    When the user open 'Practice Areas' link
    And the user open 'Corporate Transactions' link
    Then user adds practice area 'Corporate Transactions' to favourite group 'FavTestGroup'
    When the user clicks on 'Favourites' link on the header
    And the user is presented with a page with header "Favourites"
    Then the user verifies that the complete breadcrumb is "Home > Favourites"
    When the user clicks 'Corporate Transactions' link on Favourites page
    And the user is presented with a page with header "Corporate Transactions"
    Then the user verifies that the complete breadcrumb is "Home > Favourites > Corporate Transactions "
    When the user clicks on the 'Favourites' link in the breadcrumb
    Then the user is presented with a page with header "Favourites"

   
Scenario: User conducting a scope search, then navigating to Folders. Favourites and History

    When the user open 'Practice Areas' link
    And the user open 'Corporate Transactions' link
    When the user searches for term "law"
    Then the user verifies that the complete breadcrumb is "Home > Corporate Transactions > Search Results"
    When the user clicks on 'Folders' link on the header
    Then the user verifies that the complete breadcrumb is "Home > Folders"
    When the user clicks on 'History' link on the header
    Then the user verifies that the complete breadcrumb is "Home > History"
    When the user clicks on 'Favourites' link on the header
    Then the user verifies that the complete breadcrumb is "Home > Favourites"
   