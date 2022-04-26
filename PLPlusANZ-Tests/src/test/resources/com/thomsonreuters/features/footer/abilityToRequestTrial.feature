@gold
Feature: [855815][865579][869995] Request a Trial
	As a User 
	I want to request a trial
	So that I can access trial the AU practical law materials in full

Scenario: The Request a Trial link takes user to Request a Trial web page 
	Given ANZ user is logged in
	When the user clicks link 'Request a Trial' on footer 
	Then the user is taken to "https://legal.thomsonreuters.com.au/products/practical-law/contact-us.aspx?form=practical-law-trial&utm_source=practical-law-product-site&utm_medium=platform&utm_campaign=practical-law&utm_content=footer-button_v1" resource

@open_web
Scenario: The Request a Trial link takes Open Web user to Request a Trial web page 
	Given ANZ user is not logged in 
	And PL+ ANZ user navigates to home page 
	When the user clicks link 'Request a Trial' on footer 
	Then the user is taken to "https://legal.thomsonreuters.com.au/products/practical-law/contact-us.aspx?form=practical-law-trial&utm_source=practical-law-product-site&utm_medium=platform&utm_campaign=practical-law&utm_content=footer-button_v1" resource
