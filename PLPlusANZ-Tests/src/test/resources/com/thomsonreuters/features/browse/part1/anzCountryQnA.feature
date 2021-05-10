Feature: [822646] As a PLAU user, I want to use Country Q&A tool and document display to do the comparison

Background:
   Given ANZ user is logged in

  @smoke @gold
  Scenario: [822646]As a PLAU User I want to browse/select/enter a URL and link to the Country Q&A Comparison Tool So that I can compare the law for two or more Countries
    When the user opens "/QACompare/Builder/Country" url on PL AU website
    Then user verifies the "Country Q&A" page
@archived
  Scenario: [813151]User verifies the functionality of "Edit country" button on the L.H.S of the Country Q&A comparison document
    When user navigates to the "Country Q&A" tool by clicking "Start comapring" button using "International" tab on the homepage
    And user selects the topic "Arbitration"
    And user selects first questions and clicks on "Select Jurisdictions" button
    And user selects two following countries and clicks on "Comapare" button
    |Austria  |
    |Brazil    |
    And user sees the comparison page and clicks on the "Edit" button on L.H.S
    Then user should see "Edit" popup with the list of countries
    And user selects the "China" country and save it.
    And user should see the "China" appearing on L.H.S column in the comparison tool and on the page