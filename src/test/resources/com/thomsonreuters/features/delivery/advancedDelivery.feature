@manual 
Feature: [818077][821556][831297][818825][820474][821552] Advanced delivery features 

#manual tests for advanced delivery features from different areas of the site
#All the tests are written down as a one scenario outline
#Please read the scenario carefully and follow the test data table

#Font size: Normal = 10 pt, Large = 13 pt

Scenario Outline: Advanced Delivery features 

	Given the user is on the <page> page 
	When he is using <option> delivery option 
	And on the standard delivery panel he selects to deliver whole documents in <format> format 
	And sets Table of contents checkbox to <toc> 
	And , if it is search, sets Only pages with terms to <term> 
	Then he switches to the advanced delivery panel 
	And , if it is search, sets Term Highlighting to <term> 
	And he sets Expanded margin checkbox to <margin> 
	And sets Cover page checkbox to <cover> and adds some comment, if yes 
	And sets Font Size to <font> 
	And sets Links to <links> 
	And sets Underline checkbox to <underline> 
	Then he delivers the document and verifies that all the parameters listed above work as expected 
	And there is metadata information in the end: date, resource type and jurisdictions
	And author name is present under the document title
	And if font size is Large, the titles font size is also larger compared to Normal
	
	Examples: 
	| No	| page 		| option 	| format 	| toc 	|term 	| margin 	| cover 	|  font 	| links 	| underline 	|
	| 1		| Document 	| Download 	| MS Word 	| yes 	| - 	| yes 		| yes 		| Large 	| Blue 		| yes 			|
	| 2		| Search	| Download 	| PDF		| no 	| yes	| no		| yes		| Normal	| Black		| yes			|
	| 3		| Search	| Print		| -			| yes	| no	| yes		| no		| Normal	| Black		| no			|		
	| 4		| History	| Print		| - 		| no	| - 	| yes		| yes		| Large		| Blue		| no			|
	| 5		| Glossary	| Email		| MS Word	| no	| - 	| no		| yes		| Normal	| Blue		| yes			|		
	| 6		| Folder	| Email		| RTF 		| no	| - 	| yes		| no		| Normal	| Blue		| no			|
	| 7		| Folder	| Export	| PDF 		| yes	| - 	| no		| yes		| Large		| Black		| yes			|