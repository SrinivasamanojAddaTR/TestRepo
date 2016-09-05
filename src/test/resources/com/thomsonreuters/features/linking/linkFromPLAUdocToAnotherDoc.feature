Feature: [826373][826378] Linking from PLAU document to another PLAU document and external website

Background:
Given ANZ user is logged in

Scenario Outline: [826373] Linking from one PLAU document to another PLAU document   
	As a PLAU User
	I want to Link from one PLAU document to another PLAU document
	So that I can retrieve a PLAU document that I am interested in working with

	Given ANZ user navigates directly to document with guid "<guid>"
	When the user clicks on hardcoded "<linktext>" link
	And the target PLAU document is displayed in the same tab
	Examples: 
		|guid  								| linktext|
		|I09ee048de0c911e598dc8b09b4f043e0	| Practice note, Federal discrimination: making or defending a complaint in the AHRC|

Scenario Outline: [826378]Linking from one PLAU document to an external website
    When ANZ user navigates directly to document with guid "<guid>"
    And user clicks on "<linktext>" link 
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user is taken to "<href>" resource
    Examples: 
      | guid                            	|linktext			|href|
      | I53cfffa798de11e598dc8b09b4f043e0 	|ASIC company search|https://asicconnect.asic.gov.au|


      
