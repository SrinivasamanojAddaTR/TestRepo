Feature: [822630] Practice Areas URLs

  Background:
    Given ANZ user is logged in
    And API cleans all folders and history


  Scenario Outline: Practice Area page URL-<id>
    When the user opens "<paUrl>" url on PL AU website
    Then the user is presented with a page with header "<paName>"
    And the page URL contains "<paUrl>"
    And Add Page to Favorites icon is displayed
    Examples:
      | paUrl                                       | paName                 | id |
      | /Browse/Home/Practice/CompanyLaw            | Company Law            | 1  |
      | /Browse/Home/Practice/Employment            | Employment             | 2  |
      | /Browse/Home/Practice/CorporateTransactions | Corporate Transactions | 3  |