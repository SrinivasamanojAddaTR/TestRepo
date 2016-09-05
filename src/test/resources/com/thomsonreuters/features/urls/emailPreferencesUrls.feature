Feature: [826011] Email Preferences URL

Background:
   Given ANZ user is logged in

Scenario Outline: Email Preferences URL
	When the user opens "<url>" url on PL AU website
	Then the user is presented with a page with header "<name>"
    And the page URL contains "<url>"

  Examples:
   | url          								| name   	 |
   |  /Browse/home/emailpreferences		| Email preferences |