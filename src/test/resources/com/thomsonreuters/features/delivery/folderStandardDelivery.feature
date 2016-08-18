Feature: [821556] View Folders Page - Deliver Documents within a folder

Background: 
	Given ANZ user is logged in with following details
	  | userName         | ANZtestuser2 |

Scenario: Clean folders
	Given API cleans all folders and history

###############################################################################################################
## Download	
###############################################################################################################
Scenario Outline: [821556] Download documents in PDF format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| A Single Merged File |
	Then user downloads the document with name "Practical Law - 2 full text items from <folder> folder" and extension ".pdf" 
	And the document includes document body that contains text "<docText1>" 
	And the document includes document body that contains text "<docText2>" 
	Examples: 
		| 	folder 		| docText1 								| docText2 |
		|	testFolder 	| This note summarises how the courts	| Why is financial assistance prohibited? |

Scenario Outline: [821556] Download documents in zip file
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| Multiple Files (zip) |
	Then user downloads the document with name "Practical Law - 2 full text items from <folder> folder" and extension "zip" 
	Examples: 
		| 	folder 	| 
		|	testFolder | 

Scenario Outline: [821556] Download list of items in PDF format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| PDF	|
	Then user downloads the document with name "Practical Law - List of 2 items from <folder> folder" and extension ".pdf" 
	And the document includes title "<title1>"
	And the document includes title "<title2>"
	And the document does not include document body that contains text "<docText1>" 
	And the document does not include document body that contains text "<docText2>" 
	Examples: 
		| 	folder 		|  title1 													| title2 				| docText1 									| docText2 |
		|	testFolder 	|  Dismissing an ill or injured employee: adverse action: case law	| Financial assistance 	| This note summarises how the courts	| Why is financial assistance prohibited?|

Scenario Outline: [821556] Download list of items in CSV format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Microsoft Excel (CSV)	|
	Then user downloads the document with name "Practical Law - List of 2 items from <folder> folder" and extension ".csv" 
	Examples: 
		| 	folder 	| 
		|	testFolder |   

Scenario Outline: [821556] Download document in RTF format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Format		| Word Processor (RTF) 	|
	Then user downloads the document with name "<name>" and extension ".rtf" 
	Examples: 
		| 	folder 		| name |							
		|	testFolder 	| Financial Assistance  |

Scenario Outline: [821556] Download document in Microsoft Word format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Format		| Microsoft Word 	|
	Then user downloads the document with name "<name>" and extension ".doc" 
	Examples: 
		| 	folder		| name |							
		|	testFolder 	| Financial Assistance |

Scenario Outline: [821556] Download document with table of contents in PDF format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document includes table of contents that contains title "<contentsText>" 
	Examples: 
		|folder	 	|name 					|docText 								| contentsText |
		|testFolder |Financial Assistance 	|The general prohibition on financial 	| Why is financial assistance prohibited|		 

Scenario Outline: [821556] Download document without table of contents in PDF format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows 
		| Format		| PDF 	|
		| Table of Contents | Not Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document does not include table of contents that contains title "<contentsText>" 
	Examples: 
		|folder	 	|name 					|docText 					| contentsText |
		|testFolder |Financial Assistance 	|The general prohibition on financial 	| Why is financial assistance prohibited|		 

###############################################################################################################
## Email	
###############################################################################################################
  Scenario Outline: [821556] Email document in Microsoft Word format
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
	And the user should be able to see Email basic tab options as follows 
		| Subject       | <subject> |
	When the user edits the basic email options as follows 
		| To            |  <mailbox>         |
		| Format		| Microsoft Word 				|
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<subject>" 
	Examples: 
		| folder	| subject			|  mailbox |
 		|testFolder | Practical Law - Financial assistance | tr-anz-tester1@yandex.com |

Scenario Outline: [821556][847182]  Email resource link only
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
	And the user should be able to see Email basic tab options as follows 
		| Subject       | Practical Law - <title> |
	When the user edits the basic email options as follows 
		| To            |  <mailbox>         |
		| Format            | Resource Link Only |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "Practical Law - <title>"  
	When user copies the link in valid format from email into the browser
	Then user should be presented with proper document "<title>"
	Examples: 
		|folder		| guid  								| title		|  mailbox |
		|testFolder | Ifb5c2817995811e598dc8b09b4f043e0 	| Financial assistance | tr-anz-tester1@yandex.com |


###############################################################################################################
## Print 	
###############################################################################################################		
  Scenario Outline: [821556] Print a single document
	Given the user opens <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
	And user prints the document with name "Print" and extension ".pdf"
	Examples: 
		| 	folder 	| 
		|	testFolder |

###############################################################################################################
## Table of content
###############################################################################################################
  Scenario: Table of content checkbox for Email is memorized
    Given the user opens testFolder folder with the set of documents
      |Iabd12ee0995911e598dc8b09b4f043e0|
      |Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    And the user edits the basic email options as follows
      | Table of Contents | Selected                  |
      | To                | tr-anz-tester1@yandex.com |
    And Email button is clicked
    And user relogs in
    And the user opens testFolder folder with the set of documents
	  |Iabd12ee0995911e598dc8b09b4f043e0|
	  |Ifb5c2817995811e598dc8b09b4f043e0|
	And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    Then the user should be able to see Email basic tab options as follows
      | Table of Contents | Selected |

  Scenario: Table of content checkbox for Print is memorized
    Given the user opens testFolder folder with the set of documents
      |Iabd12ee0995911e598dc8b09b4f043e0|
      |Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
    And the user edits the basic print options as follows
      | Table of Contents | Not Selected |
    Then user prints the document with name "Print" and extension ".pdf"
    When user relogs in
    And the user opens testFolder folder with the set of documents
	  |Iabd12ee0995911e598dc8b09b4f043e0|
	  |Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
    Then the user should be able to see Print basic tab options as follows
      | Table of Contents | Not Selected |

  Scenario: Table of content checkbox for Download is memorized
    Given the user opens testFolder folder with the set of documents
      |Iabd12ee0995911e598dc8b09b4f043e0|
      |Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Table of Contents | Selected |
    Then user downloads the document with name "Financial Assistance" and extension ".pdf"
    When user relogs in
    And the user opens testFolder folder with the set of documents
	  |Iabd12ee0995911e598dc8b09b4f043e0|
	  |Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    Then the user should be able to see Download basic tab options as follows
      | Table of Contents | Selected |
		