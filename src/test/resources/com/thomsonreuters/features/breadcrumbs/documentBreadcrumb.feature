Feature: As a PLAU user,I want to view a Document level in a breadcrumb trail on the Practical Law Australia website, so that I can view my navigation path whilst browsing the website.

Background:
 Given ANZ user is logged in

 Scenario: Verify that logged in PLAU user can see a Document level in breadcrumb trail. 
  When the user open 'Practice Areas' link
  And the user opens 'Employment' link
  And the user opens 'Employment contracts' link
  And the user selects the document 'Confidentiality'
  And the user verifies that the complete breadcrumb is " Home >Employment >Employment contracts >Confidentiality "
  
  
