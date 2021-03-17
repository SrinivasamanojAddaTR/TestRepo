Feature: [818824] Search - Standard Delivery Features 

#bug 863546 Bug [PLAU & PLUK] Search: Less, More and Most Detail icon titles should be in sentence case

Background: 
	Given ANZ user is logged in

###############################################################################################################
## Print 	
###############################################################################################################		
		

@gold
  Scenario Outline: [818824] Print single search result
	Given the user runs a free text search for the query "<query>"
	And the user selects the checkbox associated with result "1"
    When user clicks on Print delivery option for Search
    And the user edits the basic print options as follows 
		| Table of Contents | Selected |
		| Only pages with terms | Not selected |
	Then user prints the document with name "Print" and extension ".pdf" 
	Examples: 
		| 	query 	|  
		|	test | 

	

###############################################################################################################
## Download	
###############################################################################################################
@gold
Scenario Outline: [818824] Download documents in PDF format
	Given the user runs a free text search for the query "<query>"
    And the user saves document body from following results
    |1|
    |2|    
    And the user selects the checkbox associated with result "1"
    And the user selects the checkbox associated with result "2"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows
		| Documents             | Selected             |
		| Format                | PDF                  |
		| As                    | A Single Merged File |
		| Only pages with terms | Not selected         |
	Then user downloads the document with name "2 full text items for <query>" and extension ".pdf"
	And the document includes document body that contains text from result "1" 
	And the document includes document body that contains text from result "2" 
	Examples:
		| query |
		| test  |

@gold
Scenario Outline: [818824] Download documents in zip file
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "1"
    And the user selects the checkbox associated with result "2"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows
		| Documents             | Selected             |
		| Format                | PDF                  |
		| As                    | Multiple Files (zip) |
		| Only pages with terms | Not selected         |
	Then user downloads the document with name "2 full text items for <query>" and extension "zip"
	Examples:
		| query |
		| test  |

@bug
Scenario Outline: [818824] Download list of items in PDF format
	Given the user runs a free text search for the query "<query>"
	And the user can select the option to show less detail
    And the user saves title, document body from following results
    |1|
    |2|    
    And the user selects the checkbox associated with result "1"
    And the user selects the checkbox associated with result "2"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| PDF	|
	Then user downloads the document with name "Practical Law - List of 2 results for <query>" and extension ".pdf" 
	And the document includes title from result "1" 
	And the document includes title from result "2" 
	And the document does not include document body that contains text from result "1" 
	And the document does not include document body that contains text from result "2"  
	Examples: 
	| 	query 	| 
	|	test |


Scenario Outline: [818824] Download list of items in CSV format
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "1"
    And the user selects the checkbox associated with result "2"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Microsoft Excel (CSV)	|
	Then user downloads the document with name "Practical Law - List of 2 results for <query>" and extension ".csv" 
	Examples: 
		| 	query 	|  
		|	test |  


Scenario Outline: [818824] Download document in RTF format
	Given the user runs a free text search for the query "<query>"
    And the user saves file name from following results
    |<result>|	
    And the user selects the checkbox associated with result "<result>"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| Format		| Word Processor (RTF) 	|
		| Only pages with terms | Not selected |
	Then user downloads the document with name from result "<result>" and extension ".rtf" 
	Examples: 
		| 	query 	| result|							
		|	test |1|
		 

@gold
Scenario Outline: [818824] Download document in Microsoft Word format
	Given the user runs a free text search for the query "<query>"
    And the user saves file name from following results
    |<result>|	
    And the user selects the checkbox associated with result "<result>"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| Format		| Microsoft Word 	|
		| Only pages with terms | Not selected |
	Then user downloads the document with name from result "<result>" and extension ".doc" 
	Examples: 
		| 	query 	|result|						
		|	test |1|


Scenario Outline: [818824] Download document with table of contents in PDF format
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "1"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Selected |
		| Only pages with terms | Not selected |
	And the user clicks on Download advanced tab
	When the user edits the advanced download options as follows
		| Cover Page | Not Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document includes table of contents that contains title "<contentsText>" 
	Examples: 
		| 	query 	             | name 				 | docText                 | contentsText                               |
		|	Financial Assistance |  Financial Assistance | The general prohibition | How to address financial assistance issues |



Scenario Outline: [818824] Download document without table of contents in PDF format
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "1"
    When user clicks on Download delivery option for Search
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Not Selected |
		| Only pages with terms | Not selected |
    And the user clicks on Download advanced tab
    When the user edits the advanced download options as follows
        | Cover Page | Not Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document does not include table of contents that contains title "<contentsText>" 
	Examples: 
		| query 	           | name 				  | docText                 | contentsText                               |
		| Financial Assistance | Financial Assistance | The general prohibition | How to address financial assistance issues |

###############################################################################################################
## Email	
###############################################################################################################

# The associated result has been changed from no:2 to no:3	
@gold
Scenario Outline: [818824] Email document in Microsoft Word format
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "5"
    When user clicks on Email delivery option for Search
	And the user should be able to see Email basic tab options as follows 
		| Subject       | <subject> |
	When the user edits the basic email options as follows
		| To     | <mailbox>      |
		| Format | Microsoft Word |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<subject>" 
	Examples:
		| query | subject                        | mailbox                                           |
		| BOOT  | Better off overall test (BOOT) | tr-anz-tester1@epam-email-pluk.thomsonreuters.com |
	 

@gold
Scenario Outline: [818824][847182]Email resource link only
	Given the user runs a free text search for the query "<query>"
    And the user selects the checkbox associated with result "2"
    When user clicks on Email delivery option for Search
	And the user should be able to see Email basic tab options as follows 
		| Subject       | <title>|
	When the user edits the basic email options as follows
		| To     | <mailbox>          |
		| Format | Resource Link Only |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "<title>" 
	When user copies the link in valid format from email into the browser
	Then user should be presented with proper document "<title>"
	Examples:
		| query           | guid                              | title           | mailbox                                           |
		| Share purchases | Ifb5c26cb995811e598dc8b09b4f043e0 | Share purchases | tr-anz-tester1@epam-email-pluk.thomsonreuters.com |


