Feature: [809732] Request User Guides
	As a PL Australia User (PLAU)
	I want to link to and access user guides 
	So that I can learn about practical law Australia

  Background:
    Given ANZ user is logged in

  @archived
  Scenario: The User Guides link takes user to User Guides web page
    When the user clicks link 'User Guides' on footer
    Then user was taken to url "https://support.thomsonreuters.com.au/product/practical-law-australia-incl-nz-resource-centre"
    