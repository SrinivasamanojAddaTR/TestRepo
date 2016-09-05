@robot @wip
Feature: [830655] Download document in Microsoft Word
As a PLAU User I want to download a standard document or clause in Microsoft Word in the PL house style
So that I can work with my document off line


Background:
Given ANZ user is logged in

Scenario Outline: [830655] Download standard document - MS Word format 
	Given the user opens "<plc ref>" url on PL AU website
	And the user deletes all files with name "<name>" and extension ".doc" from Downloads 
	When the user clicks on "Open in Word" delivery option for the document
	Then the document is downloaded with name "<name>" and extension ".doc" 
	Examples: 
		| plc ref 		| name													  |
		| /w-001-1772  	| Lettertomedicalpractitionerseekingreportonfitnesstowork |
		| /w-001-2798	| Deedofreleaseforsettlingadiscriminationclaim			  |
		| /w-001-1409	| Governinglawandjurisdiction							  |