Feature: [809179][825927] How are we using your information?
	As a PL Australia User (PLAU)
	I want to view the 'How are we using your information?' section
	So that I can find out what information is being captured about my personal information and session

  Background:
    Given ANZ user is logged in
  Scenario: The request training link takes user to request-training web page
    When user clicks on "How are we using your information?" link
    Then user should see the "How we are using your information" page is opened
    And the page URL contains "Browse/Home/About/HowWeAreUsingYourInformation"

