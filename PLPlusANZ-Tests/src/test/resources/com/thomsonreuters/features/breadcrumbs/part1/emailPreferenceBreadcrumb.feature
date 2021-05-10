@archived
Feature: Verify that logged in PLAU user can see a breadcrumb trail with Email preferences text.


 Scenario: Verify breadcrumb in email preferences page
     Given ANZ user is logged in
    When the user opens email preference popup using user dropdown
    Then the user verifies that the complete breadcrumb is "Home > Email preferences"
   