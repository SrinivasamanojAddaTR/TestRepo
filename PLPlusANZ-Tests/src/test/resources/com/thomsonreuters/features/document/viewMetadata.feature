Feature: [818000][850561] View Meta Data 
As a User I want to view State Jurisdiction within document view So that I know which state the law applies too
#There are no documents with Resource History section. Need to add in future.

Background:
   Given ANZ user is logged in


  @smoke @gold
  Scenario Outline: [818000] As a PL AU login/password user I want to view document metadata-<id>
	Given ANZ user navigates directly to document with guid "<guid>"
	Then resource status "<resource status>" is displayed on the document right hand panel 
	And resource type is displayed as "<documentType>" on right hand panel 
	And following jurisdictions are displayed on the document right hand panel 
		| <jurisdictions> |
	And maintained icon is <maintained> on the document right hand panel 
	And 'View Resource History' link is <resource history> on the right hand panel 
	And 'Related Content' link is <related content> on the right hand panel 
	Examples: 
	| guid 					                      | resource status    			    | jurisdictions     	| resource history  | related content | documentType          | maintained    | id |
	| Ifb5c26cb995811e598dc8b09b4f043e0   | Maintained		      		    | Australia,Federal		| Not displayed			| displayed			  | Practice notes		    | displayed		  | 1 |
	| I7b0defd8cac011e598dc8b09b4f043e0   | Law stated as at 17-Dec-2015| Australia,Victoria  | Not displayed		  | Not displayed		| Legal update: archive | Not displayed | 2 |
	| Ic177c5b0bef311e598dc8b09b4f043e0   | Maintained					        | Australia,Federal   | Not displayed			| Not displayed 	| Toolkit		            | displayed	    | 3 |


@gold
Scenario: [818000] As a PL AU login/password user I want to view related content section after clicking on link
	Given ANZ user navigates directly to document with guid "I1b74907e995911e598dc8b09b4f043e0"	
	When the user clicks on "Related Content" link
    Then the user should see the related content section displayed


Scenario: [850561] As a PL AU login/password user I want to view all State Jurisdiction  within document 
	When ANZ user navigates directly to document with guid "Ic177c5b0bef311e598dc8b09b4f043e0"
	Then following jurisdictions are displayed on the document right hand panel 
		| Australia,Federal|	
	When user clicks on 'View all' link to view all jurisdictions
	Then following jurisdictions are displayed on the document right hand panel 
		| Australia,Federal |

