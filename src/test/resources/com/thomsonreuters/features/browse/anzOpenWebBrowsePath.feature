Feature: [850065] As an open web user, I can launch the PL Australia web site and browse within the site, So that I can see the practice areas, topics, resources and international materials that are available

 Scenario: User verifies the home page and its relative tabs in open web
    Given PL+ ANZ user navigates to home page
	Then user verifies the "Home" page
	And user verifies the following tabs are displayed 
		|Practice areas|
		|Resources     |
		|International |
    And user opens "Practice areas" link
	And user verifies the following practice area links are displayed 
		|Employment            |
		|Company Law           |
		|Corporate Transactions|
	When user selects the following tab and see the relative links or content 
		|tab | content |
		| Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary, Global guides|
		|International | Countries, International subscriptions, International collections, International practice areas,Country Q&A comparison tool |

 Scenario Outline: User verifies the Practice area pages and its relative tabs in Open Web
    Given PL+ ANZ user navigates to home page
	And user opens "Practice areas" link
	And user navigates to PA page "<PA>"
	Then user verifies the "<PA>" page
	And user verifies the following tabs are displayed
	  | Topics    |
	  | Resources |
	When user selects the following tab and see the relative links or content
	  | Tab       | Content                                                                                                          |
	  | Topics    | <Topics>                                                                                                         |
	  | Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary |
	  | Resources | Practice notes, Standard documents and drafting notes, Checklists, Glossary                                      |
	Examples:
	  | PA                     | Topics                                                                                     |
	  | Company Law            | Company administration and meetings, Directors                                             |
	  | Corporate Transactions | Share acquisitions: private                                                                |
	  | Employment             | Federal discrimination and harassment, Ill and injured employees, Federal unfair dismissal |


  Scenario Outline: [831936] User verifies the topic page in Open Web.
    Given PL+ ANZ user navigates to home page
    When user opens "Practice areas" link
	And the user navigates to practice area "<PA>" filtered by "<Topic Page>" topic page
	Then user verifies the "<Topic Page>" page
	And user verifies the "<Resources>" facets are displayed on the topic page
	And user verifies the "<Resources>" sections are displayed on topic page in alphabetical order
	Examples:
	  |PA                     | Topic Page                                | Resources                             |
	  |Employment             | Federal unfair dismissal                  | Practice notes,  Checklists, Glossary |
	  |Employment             | Ill and injured employees                 | Practice notes, Checklists , Glossary |
	  |Company Law            | Directors                                 | Practice notes                        |
	  |Corporate Transactions | Share acquisitions: private               | Practice notes, Checklists, Glossary  |


  Scenario: User verifies the Practice area pages and its relative tabs in Open Web
    Given PL+ ANZ user navigates to home page
	When user selects the following tab and see the relative links or content
	  |tab | content |
	  | Resources | Practice notes, Standard documents and drafting notes, Standard clauses and drafting notes, Checklists, Glossary, Global guides|
	Then user verifies following Resource Types with presence of all Practice Areas
	  | Resource Types                        | Practice Areas |
	  | Practice notes                        | Company Law, Corporate Transactions, Employment |
      | Standard documents and drafting notes | Company Law, Corporate Transactions, Employment |
	  | Checklists                            | Company Law , Corporate Transactions, Employment|
	  | Glossary                              |                 -                               |


  Scenario:  User verifies the International pages and its relative tabs
	Given PL+ ANZ user navigates to home page
	When user selects the following tab and see the relative links or content
	  |tab | content |
	  |International | Countries, International subscriptions, International practice areas,Country Q&A comparison tool |
	Then user should see following countries with "Country Q&A" and "All Country resources" tabs
	  | Argentina    |
	  | Austria      |
	  | Brazil       |
	  | Canada       |
	  | China        |
	  | France       |
	  | Germany      |
	  | Hong Kong    |
	  | India        |
	  | Indonesia    |
	  | Italy        |
	  | Japan        |
	  | Mexico       |
	  | Norway       |
	  | Russian Federation |
	  | Singapore    |
	  | South Africa |
	  | South Korea  |
	  | Spain        |
	  | Sweden       |
	  | Switzerland  |
	  | Turkey       |
      | United Kingdom |
	  | United States|
	Then user selects following links and should see their respective pages
	  |International transaction guides|
	  |Global guides |
	  |Arbitration   |
	  |EU Law        |
	  |Competition   |
	  |Country Q&A Comparison Tool|
   

    Scenario: [861977] User verifies the topic page state in login and Open Web state.
        Given PL+ ANZ user navigates to home page
        When user opens "Practice areas" link
        When the user navigates to practice area "Employment" filtered by "Ill and injured employees" topic page
        And user verifies the "Ill and Injured Employees" page
        And user verifies the "Practice Notes" facets are displayed on the topic page
        And the user clicks on Sign On link on the header
        Then the user is able to sign in with OnePass
        | userName | AUtestuser3 |
        Then user verifies the "Ill and Injured Employees" page
        And user verifies the "Practice Notes" facets are displayed on the topic page