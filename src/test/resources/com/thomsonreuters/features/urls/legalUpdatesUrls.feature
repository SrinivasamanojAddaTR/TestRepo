Feature: [822643] Legal Updates URLs

Background:
  Given ANZ user is logged in

  
  Scenario Outline: Legal Updates URLs
	When the user opens "<url>" url on PL AU website
	Then the user is presented with a page with header "<name>"
    And the page URL contains "<url>"
  Examples:
   | url          								| name   	 | 
 #  | /LegalUpdates/Practice/w-000-6549 			| Legal Updates \| Company Law |
 #  | /LegalUpdates/Practice/w-000-5040 			| Legal Updates \| Corporate Transactions |
   | /LegalUpdates/Practice/w-000-5039   		| Legal Updates \| Employment |  

 
  Scenario Outline: Invalid URL
	When the user opens "<url>" url on PL AU website
	Then the user is redirected to an error page
  Examples:
   | url          							| 
   | /LegalUpdates/Practice/4-383-abcd		| 