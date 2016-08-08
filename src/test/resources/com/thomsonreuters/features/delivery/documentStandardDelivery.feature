Feature: [818051] Document - Standard delivery features 

# bug 842166 Bug [PLAU only] Delivery: For ANZ documents delivery options for drafting notes not present

#intermittent failures on demo
#bug 834611 Bug [PLAU & PLUK] document is not available by plc ref on demo pc1


Background:
Given ANZ user is logged in with following details
	  | userName         | ANZtestuser2 |
###########################################################################################################################################
#email delivery
###########################################################################################################################################
#mailbox: tr-anz-tester1@yandex.com
#password: tranztest


Scenario Outline: [818051] Email document - MS Word format 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       | <subject> |
	When the user edits the basic download options as follows 
		| To            |  <mailbox>         |
		| Format		| Microsoft Word 				|
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<subject>" 
	
	Examples: 
		| guid  							| subject			| mailbox |
		|I53cfffa798de11e598dc8b09b4f043e0 |  Practical Law - Completing a share transfer form: private acquisitions | tr-anz-tester1@yandex.com |
		

Scenario Outline: [818051] Email document - RTF format 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       | <subject> |
	When the user edits the basic download options as follows 
		| Format            | Word Processor (RTF) |
		| To            |  <mailbox>         |
	And Email button is clicked 
	And user receives an email at "<mailbox>" with document in Word Processor (RTF) format and with subject "<subject>" 
	Examples: 
		| guid 								| subject			| mailbox |
		|I53cfffa798de11e598dc8b09b4f043e0 | Practical Law - Completing a share transfer form: private acquisitions | tr-anz-tester1@yandex.com |
	
	#intermittent failures on demo
	#bug 834611 Bug [PLAU & PLUK] document is not available by plc ref on demo pc1
	@bug 
Scenario Outline: [818051][847182] Email document - Resource Link Only format 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       | Practical Law - <title> |
	When the user edits the basic download options as follows 
		| Format            | Resource Link Only |
		| To            |  <mailbox>         |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "Practical Law - <title>" 
	When user copies the link in valid format from email into the browser
	Then user should be presented with proper document "<title>"
	Examples: 
		| guid 								| title														| mailbox |
		|I53cfffa798de11e598dc8b09b4f043e0 	| Completing a share transfer form: private acquisitions	|tr-anz-tester1@yandex.com |
		

Scenario Outline: [818051] Email document - PDF format, table of contents included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       	| <subject> |
	When the user edits the basic download options as follows
		| Format            | PDF |
		| To				| tr-anz-tester1@yandex.com |
        | Table of Contents | Selected |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document 
	And the document includes document body that contains text "<docText>" 
	And the document includes table of contents that contains title "<contentsText>" 
	Examples: 
		| guid							   | subject					   | docText 			   | contentsText | mailbox |
		|I53cfffa798de11e598dc8b09b4f043e0 | Practical Law - Completing a share transfer form: private acquisitions | When acting for a transferee | Execution by the parties |tr-anz-tester1@yandex.com |
		

Scenario Outline: [818051] Email document - PDF format, table of contents not included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       	| <subject> |
	When the user edits the basic download options as follows
		| Format            | PDF |
		| To            |  <mailbox>         |
		| Table of Contents | Not Selected     |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document 
	And the document includes document body that contains text "<docText>" 
	And the document does not include table of contents that contains title "<contentsText>" 
	Examples: 
		| guid							   | subject					   | docText 			   | contentsText | mailbox |
		|I53cfffa798de11e598dc8b09b4f043e0 | Practical Law - Completing a share transfer form: private acquisitions | When acting for a transferee | Execution by the parties |tr-anz-tester1@yandex.com |
		
		

Scenario Outline: [818051] Email document - PDF format, drafting notes not included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       	| <subject> |
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	When the user edits the basic download options as follows 
		| Format            | PDF |
		| To            |  <mailbox>         |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document 
	And the document includes document body that contains text "<docText>" 
	And the document does not include drafting notes 
	Examples: 
		| guid 								| subject			| docText | mailbox |
		|Ifb5c26ca995811e598dc8b09b4f043e0| Practical Law - Board minutes: transfer of shares | The chairperson reported |tr-anz-tester1@yandex.com |
		
		
 
Scenario Outline: [818051] Email document - PDF format, drafting notes only 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       	| <subject> |
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	When the user edits the basic download options as follows 
		| Format            | PDF |
		| To            |  <mailbox>         |
		| Only Drafting Notes         | Selected   |
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document 
	And the document does not include document body that contains text "<docText>" 
	And the document includes drafting notes 
	Examples: 
		| guid 														| subject			| docText | mailbox |
		|Ifb5c26ca995811e598dc8b09b4f043e0| Practical Law - Board minutes: transfer of shares | The chairperson reported |tr-anz-tester1@yandex.com |
		

Scenario Outline: [818051] Email document - PDF format, drafting notes included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on email delivery option for the document 
	Then the user should be able to see Email basic tab options as follows 
		| Subject       	| <subject> |
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	When the user edits the basic download options as follows 
		| Format             		  | PDF |
		| To            |  <mailbox>         |
		| Document and Drafting Notes | Selected   		|
	And Email button is clicked 
	Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document 
	And the document includes document body that contains text "<docText>" 
	And the document includes drafting notes 
	Examples: 
		| guid 														| subject			| docText | mailbox |
		|Ifb5c26ca995811e598dc8b09b4f043e0| Practical Law - Board minutes: transfer of shares | The chairperson reported |tr-anz-tester1@yandex.com |
		
		
		
		
###########################################################################################################################################
#download
###########################################################################################################################################

Scenario Outline: [818051] Download document - MS Word format 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	And the user edits the basic download options as follows 
		| Format		| Microsoft Word 				|
	Then user downloads the document with name "<name>" and extension ".doc" 
	Examples: 
		| guid 							| name			|
		|I53cfffa798de11e598dc8b09b4f043e0 |Completing a share transfer form private acquisitions |
		
		
Scenario Outline: [818051] Download document - RTF format 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	And the user edits the basic download options as follows 
		| Format		| Word Processor (RTF) 				|
	Then user downloads the document with name "<name>" and extension ".rtf" 
	Examples: 
		| guid 														| name			|
		|I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions |
		
Scenario Outline: [818051] Download document - PDF format, with table of contents 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	And the user edits the basic download options as follows
		| Format		| PDF		|
        | Table of Contents | Selected |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document includes table of contents that contains title "<contentsText>" 
	Examples: 
		| guid 								| name			| docText | contentsText |
		|I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | When acting for a transferee | Execution by the parties |

Scenario Outline: [818051] Download document - PDF format, without table of contents 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	And the user edits the basic download options as follows 
		| Format		| PDF		|
		| Table of Contents | Not Selected     |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document does not include table of contents that contains title "<contentsText>" 
	Examples: 
		| guid 								| name			| docText | contentsText |
		|I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | When acting for a transferee | Execution by the parties |
		

Scenario Outline: [818051] Download document - PDF format, drafting notes not included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	Then the user should be able to see Download basic tab options as follows 
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	And the user edits the basic download options as follows 
		| Format		| PDF		|
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document does not include drafting notes 
	
	Examples: 
		| guid								| name							| docText | 
		|Ifb5c26ca995811e598dc8b09b4f043e0|Board minutes transfer of shares | The chairperson reported |
		

Scenario Outline: [818051] Download document - PDF format, drafting notes included 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	Then the user should be able to see Download basic tab options as follows 
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	And the user edits the basic download options as follows 
		| Format		| PDF		|
		| Document and Drafting Notes | Selected   |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document includes document body that contains text "<docText>" 
	And the document includes drafting notes 
	Examples: 
		| guid								| name							| docText |
		|Ifb5c26ca995811e598dc8b09b4f043e0| Board minutes transfer of shares| The chairperson reported |


Scenario Outline: [818051] Download document - PDF format, drafting notes only 
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Download delivery option for the document 
	Then the user should be able to see Download basic tab options as follows 
		| Document                    | Selected       |
		| Only Drafting Notes         | Not Selected   |
		| Document and Drafting Notes | Not Selected   |
	And the user edits the basic download options as follows 
		| Format		| PDF		|
		| Only Drafting Notes | Selected   |
	Then user downloads the document with name "<name>" and extension ".pdf" 
	And the document does not include document body that contains text "<docText>" 
	And the document includes drafting notes 
	
	Examples: 
		| guid 								| name			| docText |
		|Ifb5c26ca995811e598dc8b09b4f043e0| Board minutes transfer of shares | The chairperson reported |
	
###########################################################################################################################################
#Print
###########################################################################################################################################

Scenario Outline: [818051] Print document
	Given ANZ user navigates directly to document with guid "<guid>"
	When clicks on Print delivery option for the document 
	And user prints the document with name "Print" and extension ".pdf"
	Examples: 
		| guid 								|
		|Ifb5c26ca995811e598dc8b09b4f043e0 |
		
	