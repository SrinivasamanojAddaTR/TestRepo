Feature: To View Footer Breadcrumbs

  Background: 
   Given PL+ ANZ user navigates to home page

  Scenario Outline: As a Practical Law user, I want to view the breadcrumb trail on the Practical website,so that I can view my navigation path when navigating to the PL footer pages
    Then user verifies the "Home" page
    When user should see footer
    Then user clicks on "<Footer link>" link
    Then the user verifies that the complete breadcrumb is "<Breadcrumb>"

    Examples: 
      | Footer link         | Breadcrumb                           |
      | About Practical Law | Home > About us: About Practical Law |
      | Testimonials        | Home > Testimonials                  |
      | Meet the Team       | Home > Our team                      |
      | Advisory Board      | Home > Our team                      |
      | Contributing Firms  | Home > Our team                      |
     
Scenario Outline: Verify that logged in PLAU user can see an  "Our team" breadcrumb in breadcrumb trail when navigating to any Practice area Our team page. 
    When user clicks on "<Page>" link
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    When the user selects a practice area "<PA>" within the table of contents
    And practice area "<PA>" in Table of Contents is active
    Then the user verifies that the current PageTitle contains '<pageTitle1>'
    And the user verifies that the complete breadcrumb is "<Breadcrumb>"
   
    Examples: 
      | Page          | pageTitle | PA                     |pageTitle1                        | Breadcrumb      |
      | Meet the Team | Our team  | Commercial             |Our team: Commercial             | Home > Our team |
      | Meet the Team | Our team  | Company Law            |Our team: Company Law            | Home > Our team |
      | Meet the Team | Our team  | Corporate Transactions |Our team: Corporate Transactions | Home > Our team |
      | Meet the Team | Our team  | Employment             |Our team: Employment             | Home > Our team |
      | Meet the Team | Our team  | Dispute Resolution     |Our team: Dispute Resolution     | Home > Our team |
      
   
Scenario Outline:As a PLAU user,I want to view "About us" breadcrumb in the breadcrumb trail when viewing any page from "About" section in the site Footer of the Practical Law Australia website, so that I can be aware that I am viewing a page from About pages.
    
    When user clicks on "<Page>" link    
    Then the user verifies that the current PageTitle contains '<pageTitle>'
    When the user selects a tab "<tabToSelect>" within the table of contents
    And tab "<tabToSelect>" in Table of Contents is active
    Then the user verifies that the current PageTitle contains '<pageTitleForSelectedTab>'
    And the user verifies that the complete breadcrumb is "<Breadcrumb>"
    
    Examples: 
      | Page                | pageTitle                     | tabToSelect          |pageTitleForSelectedTab          | Breadcrumb      |  
      |About Practical Law  | About us: About Practical Law |Why use Practical Law?|About us: Why use Practical Law? |Home >About us: Why use Practical Law? |          
      | About Practical Law | About us: About Practical Law | Resources            | About us: Resources             |Home >About us: Resources    |
      | About Practical Law | About us: About Practical Law | Our team             | About us: Our team              |Home > About us: Our team          |