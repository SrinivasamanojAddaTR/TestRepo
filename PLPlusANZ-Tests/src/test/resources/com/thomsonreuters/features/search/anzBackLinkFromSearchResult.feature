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

  Scenario Outline: Verify Back to practice area link is displayed on practice area search results page-<id>
    Given the user selects "Practice Areas" tab
    When has selected the link to the practice area "<paName>"
    And the user runs a free text search for the query "<query>"
	And the user clicks breadcrumb link '<paName>' on 'search' page
	Then the user is presented with a page with header "<paName>"
    Examples:
    |paName		 |query		| id |
    |Company Law |document	| 1  |

  Scenario Outline: Verify Back to topic link is displayed on topic search results page-<id>
    Given the user selects "Practice Areas" tab
    When the user clicks category link '<paName>'and topic link '<TopicLink>' on 'Home' page
    And the user runs a free text search for the query "<query>"
	And the user clicks breadcrumb link '<TopicTitle>' on 'search' page
	Then the user is presented with a page with header "<TopicTitle>"
    Examples:
    |paName			|TopicLink 			       | TopicTitle		          |query		| id |
    |Employment  	|Enterprise bargaining     |Enterprise bargaining     | award		| 1  |
 
  @archived
  Scenario Outline: Verify Back to document link is displayed on search results page-<id>
    When the user runs a free text search for the query "<query>"
    And the user clicks link '<title of document>' on 'search' page
	And the user see title of opened document as '<title of document>'
	And the user runs a free text search for the query "<query>"
	And the user clicks link 'Back' on 'search' page
	Then the user see title of opened document as '<title of document>'
    Examples:
    |query		|title of document		                                                                         | id |
    |document	|Execution of deeds and documents by companies incorporated under the Corporations Act 2001 (Cth)| 1  |

  @archived
  Scenario Outline: Verify Back to country link is displayed on country search results page-<id>
    Given has selected the link entitled International
    When has selected the link to the country page "<country>"
    And the user runs a free text search for the query "<query>"
	And the user clicks link '<country>' on 'search' page
	Then the user is presented with a page with header "<country>"
    Examples:
    |country	|query		| id |
    |Italy		|document	| 1  |
 
  Scenario Outline: Verify Back link is displayed on folder search results page-<id>
    Given the user clicks on 'Folders' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'Folders' on 'search' page
	Then 'Folders' page opens
    Examples:
    |query		| id |
    |document	| 1  |

  Scenario Outline: Verify Back link is displayed on history search results page-<id>
    Given the user clicks on 'History' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'History' on 'search' page
	Then 'History' page opens
    Examples:
    |query		| id |
    |document	| 1  |


  Scenario Outline: Verify Back link is displayed on favourite search results page-<id>
    Given the user clicks on 'Favourites' link on the header
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'Favourites' on 'search' page
	Then the user is presented with a page with header "Favourites"
    Examples:
    |query		| id |
    |document	| 1  |

  Scenario Outline: Verify Back link is displayed on footer search results page-<id>
    Given user clicks on "About Practical Law" link
    When the user runs a free text search for the query "<query>"
	And the user clicks link 'About us' on 'search' page
	Then the user is presented with a page with header "About us: Practical Law"
    Examples:
    |query		| id |
    |document	| 1  |
