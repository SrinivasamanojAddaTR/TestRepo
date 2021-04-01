Feature: [821257] As a PLAU User I want to be able to view my frequently used items So that I can quickly access the practical law pages I use the most

  Scenario: 
  Given ANZ user is logged in
    When API cleans all folders and history
    And user relogs in
    And user opens "Practice Areas" link
    And the user opens "Company Law" link
    And user clicks on "Favourites" link
    Then the user checks that 'Company Law' link presents in favourites group 'Frequently Used Items' on Favourites page
    And the user navigates to the main PL ANZ page
    And the user opens "Corporate Transactions" link
    And user clicks on "Favourites" link
    Then the user checks that 'Corporate Transactions' link presents in favourites group 'Frequently Used Items' on Favourites page
    And the user navigates to the main PL ANZ page
    And the user opens "Employment" link
    And user clicks on "Favourites" link
    Then the user checks that 'Employment' link presents in favourites group 'Frequently Used Items' on Favourites page
    And the user navigates to the main PL ANZ page
    And the user opens "Corporate Transactions" link
    And user clicks on "Favourites" link
    Then the user should see "Corporate Transactions" link appears first in Frequently Used Items in Favourites
