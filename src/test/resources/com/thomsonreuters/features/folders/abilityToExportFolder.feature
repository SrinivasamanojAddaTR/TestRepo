Feature: [821552] View Folders Page - Options "Export Folder" Feature

Background: 
	Given ANZ user is logged in


###############################################################################################################
## Export Process Basic Features  
## Export the documents in either Microsoft Word, RTF or PDF format
###############################################################################################################
Scenario: Clean folders
	Given API cleans all folders and history

Scenario Outline: [821552] Export documents in PDF format as a single merged file
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| A Single Merged File |
		| Table of Contents | Selected |
	Then user exports the document with name "Practical Law - 2 full text items exported from My Folders" and extension ".pdf" 
	And the document includes document body that contains text "<docText1>" 
	And the document includes document body that contains text "<docText2>" 
	Examples: 
		| 	folder 		| docText1 													| docText2 |
		|	testFolder 	| This note summarises how the courts have decided 	| Why is financial assistance prohibited? |


Scenario Outline: [821552] Export documents in RTF format as a single merged file
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| Word Processor (RTF) 	|
		| As			| A Single Merged File |
		| Table of Contents | Selected |
	Then user exports the document with name "Practical Law - 2 full text items exported from My Folders" and extension ".rtf" 
	Examples: 
		| 	folder 	|
		|	testFolder | 
  
Scenario Outline: [821552] Export documents in Microsoft Word format as a single merged file
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| Microsoft Word  	|
		| As			| A Single Merged File |
		| Table of Contents | Selected |
	Then user exports the document with name "Practical Law - 2 full text items exported from My Folders" and extension ".doc" 
	Examples: 
		| 	folder 	|
		|	testFolder |
		
###############################################################################################################
## Export the list of items in either Microsoft Word, RTF, PDF or EXcel format
###############################################################################################################
Scenario Outline: [821552] Download list of items in PDF format
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|	
    When the user selects option Export and chooses the "<folder>" folder 	 
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| PDF	|
	Then user exports the document with name "Practical Law - List of 2 items exported from My Folders" and extension ".pdf" 
	And the document includes title "<title1>"
	And the document includes title "<title2>"
	And the document does not include document body that contains text "<docText1>" 
	And the document does not include document body that contains text "<docText2>" 
	Examples:
      | folder 	   | title1 													                       | title2 			  | docText1 							| docText2 |
      |	testFolder | Summary of significant adverse action case law involving ill or injured employees | Financial assistance | This note summarises how the courts	| Why is financial assistance prohibited?|

Scenario Outline: [821552] Download list of items in CSV format
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Microsoft Excel (CSV)	|
	Then user exports the document with name "Practical Law - List of 2 items exported from My Folders" and extension ".csv" 
	Examples: 
		| 	folder 	|  
		|	testFolder | 		
 
Scenario Outline: [821552] Download list of items in RTF format
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Word Processor (RTF)|
	Then user exports the document with name "Practical Law - List of 2 items exported from My Folders" and extension ".rtf" 
	Examples: 
		| 	folder 	|  
		|	testFolder | 	
 
Scenario Outline: [821552] Download list of items in Microsoft Word format
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    When the user selects option Export and chooses the "<folder>" folder 
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| Microsoft Word |
	Then user exports the document with name "Practical Law - List of 2 items exported from My Folders" and extension ".rtf" 
	Examples: 
		| 	folder 	|  
		|	testFolder | 

Scenario Outline: [821552] Download list of items in PDF format from 2+ folders
	Given the user has <folder1> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
	And the user has <folder2> folder with the set of documents
		|I1b743e1c995911e598dc8b09b4f043e0|
	When the user chooses set of folders for Export
		|<folder1>|
		|<folder2>| 
    And the user edits the basic download options as follows 
		| List of Items     |  Selected         |
		| Format Value		| PDF	|
	Then user exports the document with name "Practical Law - List of 3 items exported from My Folders" and extension ".pdf" 
	And the document includes title "<title1>"
	And the document includes title "<title2>"
	And the document includes title "<folder1>" 
	And the document includes title "<folder2>"
	Examples:
		| 	folder1 	| 	folder2 		|  title1 								                                             | title2 |
		|	testFolder 	|	secondFolder 	|  Summary of significant adverse action case law involving ill or injured employees | Heads of agreement|

###############################################################################################################
## Cancel button and Back button
###############################################################################################################
Scenario: [821552] There will be a cancel button to cancel the transaction
	When the user clicks on 'Folders' link on the header
    When the user selects option Export
    And the user sees an Export wizard
    And the user clicks the Cancel button on Export Wizard
	Then the user doesn't see an Export wizard
 
Scenario Outline: [821552] There will be a back button to go back and select a new folder
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    And the user selects option Export and chooses the "<folder>" folder 
    And the user clicks the Back button on Export Wizard
	Then the user sees an Export wizard
	Examples: 
		| 	folder 	|
		|	testFolder |		
###############################################################################################################
## Exporting Documents 
## Exporting multiple files will be exported in a Zip file format
###############################################################################################################
Scenario Outline: [821552] Export documents in zip file
	Given the user has <folder> folder with the set of documents
		|Iabd12ee0995911e598dc8b09b4f043e0|
		|Ifb5c2817995811e598dc8b09b4f043e0|
    When the user selects option Export and chooses the "<folder>" folder 
    When the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| Multiple Files (zip) |
	Then user exports the document with name "Practical Law - 2 full text items exported from My Folders" and extension ".zip" 
	Examples: 
		| 	folder 	|
		|	testFolder |

@manual
 Scenario: [821552] Export documents in zip file from 2+ folders
	Given user has Test1 folder with the set of documents
	And the user has Test2 folder with the set of documents
    When the user selects option Export
    And the user chooses set of folders for Export
		|Test1|
		|Test2| 
    And the user edits the basic download options as follows 
		| Documents     |  Selected         |
		| Format		| PDF	|
		| As			| Multiple Files (zip) |
	Then user exports the document with name "Practical Law - 2 full text items exported from My Folders" and extension ".zip" 
	And there are folder Test1 and folder Test2 in zip archive file
	
