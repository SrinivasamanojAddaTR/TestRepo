Feature: [821261][821248] As a PLAU User I want to Organise my favourite groups So that I can display my favourite groups in the order I require
  [821282] As a PLAU User I want to delete page links from my favourite groups So that I can remove pages that are no longer my favourites
  [821309]As a PLAU User I want to change the order of my page links within a group So that I can display page links in the order that I require

  Background: 
    Given ANZ user is logged in
    When API cleans all folders and history

  #And user relogs in
  @run123
  @smoke
  Scenario: [821261]user verifies the delete and undo links operation for fav group and also verifies the "Done" button
    When user clicks on "Favourites" link
    Then the user is presented with a page with header "Favourites"
    When user creates new favourites group 'NewFavTestGroup'
    #When the user deletes the favourites group 'NewFavTestGroup'
    And user clicks on "organize" button
    Then user should see "Done" button
    When user hovers over the group 'NewFavTestGroup'
    Then user clicks on "Delete" link
    And user should see the stricken through group 'NewFavTestGroup'
    And user clicks on "Undo" link
    And user should see the group 'NewFavTestGroup'
    And user adds practice area 'Company Law' to favourite group 'NewFavTestGroup'
    And user clicks on "Favourites" link
    And the user is presented with a page with header "Favourites"
    When the user deletes the favourites group 'NewFavTestGroup'
    And the user come back on to Home page
    And the user clicks on 'Favourites' link on the header
    Then the user checks that 'Company Law' link is not in favourites group 'NewFavTestGroup' on Favourites page
    And the favourites group 'NewFavTestGroup' is absent on Favourites page

  Scenario: [821282]user verifies the delete and undo links operations for a link within the Favourite group
    When user clicks on "Favourites" link
    And user creates new favourites group 'NewFavTestGroup'
    And user adds practice area 'Company Law' to favourite group 'NewFavTestGroup'
    And user adds practice area 'Employment' to favourite group 'NewFavTestGroup'
    And user clicks on "Favourites" link
    And user clicks on "organize" button
    And user hovers over the fav group 'NewFavTestGroup' link 'Employment'
    And user clicks on "Delete" link
    And user should see the stricken through link 'Practice - Employment' of group 'NewFavTestGroup'
    And user clicks on "Undo" link
    And user clicks on "done" button
    Then the user checks that 'Company Law' link presents in favourites group 'NewFavTestGroup' on Favourites page
    When the user deletes the favourites page 'Company Law'
    And the user deletes the favourites page 'Employment'
    And the user come back on to Home page
    And user clicks on "Favourites" link
    Then the user checks that 'Company Law' link is not in favourites group 'NewFavTestGroup' on Favourites page
    And the user checks that 'Employment' link is not in favourites group 'NewFavTestGroup' on Favourites page

  Scenario: [821261]user verifies the "Save" and "Cancel" buttons operations
    When user clicks on "Favourites" link
    And user creates new favourites group 'NewFavTestGroup'
    And user adds practice area 'Company Law' to favourite group 'NewFavTestGroup'
    And user clicks on "Favourites" link
    And user clicks on "organize" button
    When user hovers over the group 'NewFavTestGroup'
    And user clicks on "Rename" link
    Then user should see the aligned "Save" and "Cancel" button for group 'NewFavTestGroup'
    And user clicks on "Cancel" button
    And user clicks on "done" button
    When the user renames the favourites group 'NewFavTestGroup' to 'pl33'
    And the user checks that 'Company Law' link presents in favourites group 'pl33' on Favourites page
    And the favourites group 'pl33' presents on Favourites page

@manual
  Scenario: [821261]user verifies the moving the group around
    When user clicks on "Favourites" link
    And user creates new favourites group 'NewTestGroup01'
    And user creates new favourites group 'NewTestGroup02'
    And user clicks on "organize" button
    And user drags group 'NewTestGroup01' down to group 'NewTestGroup02'
    And user clicks on "done" button
    Then the user should see the group 'NewTestGroup01' comes first than group 'NewTestGroup02'

  @manual
  Scenario: [821309]user verifies the moving the page around
    When user clicks on "Favourites" link
    And user creates new favourites group 'NewFavTestGroup'
    And user adds practice area 'Company Law' to favourite group 'NewFavTestGroup'
    And user adds practice area 'Employment' to favourite group 'NewFavTestGroup'
    And user clicks on "Favourites" link
    And user clicks on "organize" button
    And user drags page 'Employment' down to page 'Company Law'
    And user clicks on "done" button
    Then the user should see the page 'Company Law' comes first than page 'Employment'

  #771484 Removal of the My Start Page item is ineffective when done via Favorites-> My Start Page
  @gold
  Scenario: [821248]user verifies start page fucntionality
    When user makes practice area page 'Employment' as start page
    And user clicks on "Favourites" link
    And the user checks that 'Employment' link presents in favourites group 'My Start Page' on Favourites page
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Employment'
    Then the user deletes the start page from favourites
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Home'

  @gold
  Scenario: [831325]As a PLAU User I want to be able to set a page as my start page So that when I login to PLAU that page appears as my start page
    When user makes practice area page 'Employment' as start page
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Employment'
    And user clicks the Home Icon to remove it as Start Page
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Home'
    And user navigates to Resource "Practice notes" on resource page
    And user clicks the Home Icon to make it as Start Page
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Practice notes'
    And user clicks the Home Icon to remove it as Start Page
    And user relogs in
    Then the user verifies that the current PageTitle contains 'Home'
    #And the user selects country "Spain" on international tab
    #And user clicks the Home Icon to make it as Start Page
    #And user relogs in
    #Then the user verifies that the current PageTitle contains 'Spain'
    #And user clicks the Home Icon to remove it as Start Page
    #And user relogs in
    #Then the user verifies that the current PageTitle contains 'Home'
