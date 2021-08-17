Feature: [812292] As a PLAU User I want to signout of PLAU So that I can ensure my session is closed safely and securely



  @smoke @gold @sanity
  Scenario: [812292] As a PLAU User I want to signout of PLAU So that I can ensure my session is closed safely and securely
    Given ANZ user is logged in
    When user clicks on Sign out
    Then user should see Log out confirmation page
    And user should see a branding logo on Log out screen
    And user should see Sign On button on this page
    And user should see session summary on this page
    