Feature: [820792][822937] As a PLAU user,  I want to view folders, favourites and history
  So that I can folder my favourite documents and pages and view the history of my session

  @smoke @gold @ffh @sanity
  Scenario: User wants to see Favourite, Folders and History links
    Given ANZ user is logged in
    Then user should see "Folders" link
    When  user clicks on "Folders" link
    Then  user should see folders page
    And user should see "History" link
    When  user clicks on "History" link
    Then  user should see history page
    And user should see "Favourites" link
    When  user clicks on "Favourites" link
    Then  user should see "Favourites" page



  @gold @ffh @open_web
  Scenario: User wants to see Favourite, Folders and History links not available in open web
    When PL+ ANZ user navigates to home page
    Then user should not see the "Folders" link
    And user should not see the "History" link
    And user should not see the "Favourites" link

