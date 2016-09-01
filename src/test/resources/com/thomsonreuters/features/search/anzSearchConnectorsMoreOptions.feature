Feature: [813587]Search Connectors: More Options
As a PLAU User I want to apply search operators within my search query 
So that I can narrow my search results and ensure only relevant results are returned 

Background:
    Given ANZ user is logged in with following details
      | userName         | ANZtestuser2 	|

  Scenario Outline: verify that a user can submit a phrase search
  	When the user runs a free text search for the query "\"federal circuit\""
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the search terms "federal circuit" as a phrase within the full text
  Examples:
    | result | 
    | 1      |   


  Scenario Outline: Validate that use of the /p connectors retrieves terms within the same paragraph
  	When the user runs a free text search for the query "document /p review"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the search terms "document" "review" within a single paragraph in the full text
  Examples:
    | result |
    | 1      |
 
  Scenario Outline: Validate that use of the +p connector retrieves search terms where the first precedes the second in the same paragraph
   	When the user runs a free text search for the query "review +p document"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the search terms "review" "document" in the full text where the first precedes the second in the same paragraph
  Examples:
    | result |
    | 1      |
 
  Scenario Outline: Validate that use of the /n connector (where n is a number) retrieves terms within n terms of each other
    When the user runs a free text search for the query "company /4 register"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the both search terms "company" "register" within "4" terms of each other within the full text
  Examples:
    | result |
    | 2      |

  Scenario Outline: Validate that use of the +n connector (where n is a number) retrieves numerical terms within n terms of each other
	When the user runs a free text search for the query "document +3 act"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the both search terms "document" "act" "3" terms of each other in the full text with the first preceding the second
  Examples:
    | result |
    | 1      |
 
  Scenario Outline: Validate that use of the % connector prevents terms placed after it from being retrieved
    When the user runs a free text search for the query "tax % purchase"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the first search term "tax" in the full text for the first term and not the second "purchase"
  Examples:
    | result |
    | 2      |

  Scenario Outline: Validate that it is possible to create searches using a mixture of connectors and operators
    When the user runs a free text search for the query "(financial and tax) % assistance"
    And the user opens the result in position "<result>"
    Then the user verifies the search result contains the search terms "financial" and also "tax" within the full text
    And the user is able to verify that the full text will not contain the term "assistance"
  Examples:
    | result  |
    | 1       |


