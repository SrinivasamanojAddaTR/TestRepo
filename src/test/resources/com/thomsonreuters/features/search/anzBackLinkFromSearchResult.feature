Feature: [829761] Link back to the page  from where performed a search
	As a PLAU User, I want to link back to the page I performed a search from
	So that I can view the page I was viewing before I performed a search

  Background:
    Given ANZ user is logged in
     And the user is on the home page

  @smoke
  Scenario: Verify back to home link is displayed on search results page
    Given the user runs a free text search for the query "document"
    When the user clicks link 'Home' on 'search' page
    Then the user is presented with a page with header "Home"

  Scenario Outline: Verify Back to practice area link is displayed on practice area search results page
    Given the user selects "Practice Areas" tab
    When has selected the link to the practice area "<paName>"
    And the user runs a free text search for the query "<query>"
	And the user clicks breadcrumb link '<paName>' on 'search' page
	Then the user is presented with a page with header "<paName>"
    Examples:
    |paName		 |query		|
    |Company Law |document	|

  Scenario Outline: Verify Back to topic link is displayed on topic search results page
    Given the user selects "Practice areas" tab
    When the user clicks category link '<paName>'and topic link '<TopicLink>' on 'Home' page
    And the user runs a free text search for the query "<query>"
	And the user clicks link '<TopicTitle>' on 'search' page
	Then the user is presented with a page with header "<TopicTitle>"
    Examples:
    |paName			|TopicLink 			       | TopicTitle		          |query		|
    |Employment  	|Federal unfair dismissal  |Federal unfair dismissal  |	award		|
 

  Scenario Outline: Verify Back to document link is displayed on search results page
    When the user runs a free text search for the query "<query>"
    And the user clicks link '<title of document>' on 'search' page
	And the user see title of opened document as '<title of document>'
	And the user runs a free text search for the query "<query>"
	And the user clicks link 'Back' on 'search' page
	Then the user see title of opened document as '<title of document>'
    Examples:
    |query		|title of document		|
    |document	|Execution of deeds and documents by companies incorporated under the Corporations Act 2001 (Cth)|
   
@archived
  Scenario Outline: Verify Back to country link is displayed on country search results page
    Given has selected the link entitled International
    When has selected the link to the country page "<country>"
    And the user runs a free text search for the query "<query>"
	And the user clicks link '<country>' on 'search' page
	Then the user is presented with a page with header "<country>"
    Examples:
    |country	|query		|
    |Italy		|document	|
 
  Scenario Outline: Verify Back link is displayed on folder search results page
    Given the user clicks on 'Folders' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'Folders' on 'search' page
	Then 'Folders' page opens
    Examples:
    |query		|
    |document	|

  Scenario Outline: Verify Back link is displayed on history search results page
    Given the user clicks on 'History' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'History' on 'search' page
	Then 'History' page opens
    Examples:
    |query		|
    |document	|    


  Scenario Outline: Verify Back link is displayed on favourite search results page
    Given the user clicks on 'Favourites' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'Favourites' on 'search' page
	Then the user is presented with a page with header "Favourites"
    Examples:
    |query		|
    |document	|        

  Scenario Outline: Verify Back link is displayed on footer search results page
    Given user clicks on "About Practical Law" link
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'About us' on 'search' page
	Then the user is presented with a page with header "About us: About Practical Law"
    Examples:
    |query		|
    |document	|     
