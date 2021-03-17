Feature: [831297] View History Page - Deliver Documents  listed within my document history

#some tests may intermittently fail due to:
#bug 830073 [REGRESSION] Several documents don't display in History

#The user name was ANZtestuser1
Background: 
	Given ANZ user is logged in with following details
		| userName | ANZtestuser1 |

###############################################################################################################
## Print 	
###############################################################################################################

  @smoke
  Scenario: [831297] Print a single document
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    And user clicks on Print delivery option for History
	And user prints the document with name "Print" and extension ".pdf"

###############################################################################################################
## Download	
###############################################################################################################
Scenario Outline: [831297] Download documents in PDF format
	Given ANZ user navigates directly to document with guid "<guid1>"
	And ANZ user navigates directly to document with guid "<guid2>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
	And the user selects the date Today with -
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| A Single Merged File |
	Then user downloads the document with name "2 full text items" and extension ".pdf"
	And the document includes document body that contains text "<docText1>" 
	And the document includes document body that contains text "<docText2>" 
	Examples: 
		|guid1							  |guid2							|docText1							|docText2              |
      	|If13ba3b4d4d811e598dc8b09b4f043e0|Ifb5c26cc995811e598dc8b09b4f043e0|When are shares transferable?		|What is due diligence?|


Scenario Outline: [831297] Download documents in zip file
	Given ANZ user navigates directly to document with guid "<guid1>"
	And ANZ user navigates directly to document with guid "<guid2>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
	And the user selects the date Today with -
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| Multiple Files (zip) |
	Then user downloads the document with name "2 full text items" and extension "zip"
	Examples: 
		|guid1							  |guid2                            |
      	|If13ba3b4d4d811e598dc8b09b4f043e0|Ifb5c26cc995811e598dc8b09b4f043e0|


Scenario Outline: [831297] Download list of items in PDF format
	Given ANZ user navigates directly to document with guid "<guid1>"
	And ANZ user navigates directly to document with guid "<guid2>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
	And the user selects the date Today with -
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| PDF	|
	Then user downloads the document with name "List of 2 items" and extension ".pdf"
	And the document includes title "<title1>"
	And the document includes title "<title2>"
	And the document does not include document body that contains text "<docText1>" 
	And the document does not include document body that contains text "<docText2>" 
	Examples: 
		|guid1							  |guid2							|title1 									| title2 						| docText1 						| docText2 |
		|If13ba3b4d4d811e598dc8b09b4f043e0|Ifb5c26cc995811e598dc8b09b4f043e0|Transfer of shares | Due diligence: acquisitions	|When are shares transferable?	|What is due diligence?|


Scenario Outline: [831297] Download list of items in CSV format
	Given ANZ user navigates directly to document with guid "<guid1>"
	And ANZ user navigates directly to document with guid "<guid2>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
	And the user selects the date Today with -
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Microsoft Excel (CSV)	|
	Then user downloads the document with name "List of 2 items" and extension ".csv"
	Examples: 
		|guid1							  |guid2							|
		|If13ba3b4d4d811e598dc8b09b4f043e0|Ifb5c26cc995811e598dc8b09b4f043e0|


Scenario Outline: [831297] Download document in RTF format
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Format		| Word Processor (RTF) 	|
	Then user downloads the document with name "<name>" and extension ".rtf" 
	Examples: 
		|guid								| name |							
		|If13ba3b4d4d811e598dc8b09b4f043e0 	| Transfer of shares |


Scenario Outline: [831297] Download document in Microsoft Word format
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Format		| Microsoft Word 	|
	Then user downloads the document with name "<name>" and extension ".doc" 
	Examples: 
		|guid								| name |							
		|If13ba3b4d4d811e598dc8b09b4f043e0  | Transfer of shares |


Scenario Outline: [831297] Download document with table of contents in PDF format
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Selected |
    And the user clicks on Download advanced tab
    When the user edits the advanced download options as follows
        | Cover Page | Not Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document includes table of contents that contains title "<contentsText>" 
	Examples: 
		|guid								| name 							|docText 			| contentsText |
		|If13ba3b4d4d811e598dc8b09b4f043e0  | Transfer of shares 	|As a general rule 	| Steps for transferring shares  |


Scenario Outline: [831297] Download document without table of contents in PDF format
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for History
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Not Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document does not include table of contents that contains title "<contentsText>" 
	Examples: 
		|guid								| name 							|docText 			| contentsText |
		|If13ba3b4d4d811e598dc8b09b4f043e0  | Transfer of shares 	|As a general rule 	| Steps for transferring shares  |

###############################################################################################################
## Email	
###############################################################################################################
	Scenario Outline: [831297] Email document in Microsoft Word format
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for History
	And the user should be able to see Email basic tab options as follows 
		| Subject       | <subject> |
	When the user edits the basic email options as follows 
		| To            | <mailbox>         |
		| Format		| Microsoft Word 				|
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<subject>" 
	Examples: 
		|guid								| subject			| mailbox |
		|If13ba3b4d4d811e598dc8b09b4f043e0 	| Transfer of shares | tr-anz-tester1@epam-email-pluk.thomsonreuters.com |

Scenario Outline: [831297][847182] Email resource link only
	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on 'History' link on the header
	And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for History
	And the user should be able to see Email basic tab options as follows 
		| Subject       | <title> |
	When the user edits the basic email options as follows 
		| To            |  <mailbox>         |
		| Format            | Resource Link Only |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "<title>" 
	When user copies the link in valid format from email into the browser
	Then user should be presented with proper document "<title>"
	Examples: 
		| guid  								| title			| mailbox|
		| If13ba3b4d4d811e598dc8b09b4f043e0	| Transfer of shares | tr-anz-tester1@epam-email-pluk.thomsonreuters.com |

		