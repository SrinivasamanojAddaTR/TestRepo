Feature: [822630] Practice Areas URLs

Background:
Given ANZ user is logged in
  

Scenario Outline: Practice Area page URL
	When the user opens "<paUrl>" url on PL AU website
	Then the user is presented with a page with header "<paName>"
    And the page URL contains "<paUrl>"
    And Add Page to Favorites icon is displayed
  Examples:
   | paUrl          								| paName   	 | 
   | /Browse/Home/Practice/CompanyLaw				| Company Law   |
   | /Browse/Home/Practice/Employment 				| Employment | 
   | /Browse/Home/Practice/CorporateTransactions 	| Corporate Transactions |