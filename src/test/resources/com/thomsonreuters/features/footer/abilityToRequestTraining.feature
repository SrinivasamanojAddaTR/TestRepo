Feature: [809732] Request a Training
	As a PLAU User
	I want to request training
	So that I can learn how to use the Practical Law web site

  Background:
    Given ANZ user is logged in

  @smoke @gold
  Scenario: The request training link takes user to request-training web page
    When the user clicks link 'Request Training' on footer
    Then user was taken to url "https://support.thomsonreuters.com.au/"
    

    