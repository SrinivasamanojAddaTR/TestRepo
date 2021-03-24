Feature: [817979] View PL Document 

Background:
   Given ANZ user is logged in

  @smoke @gold
Scenario: [817979] As a PL AU login/password user I want to view document 
	Given ANZ user navigates directly to document with guid "Ifb5c2817995811e598dc8b09b4f043e0"	 
	Then document title is displayed as "Financial assistance" 
	And author name "by Practical Law Corporate" is displayed underneath the document title 
	And document summary contains "the elements of financial assistance" 
	And the full text document will be displayed including "When advising the buyer"
	And the content body contain end of document
	And the resource ID "W-001-0211" will be displayed
	And the copyright will be displayed


@gold
Scenario Outline: [817979] As a PL AU login/password user I want to view related content section
	Given ANZ user navigates directly to document with guid "<guid>"	 
	When the user scrolled to the bottom of the document
	Then the user should see the related content section displayed
	And link in related content is present with title "<title>" and status "<status>"
	When the user clicks on link in related content with title "<title>"
	Then document title is displayed as "<title>"
    Examples:
	    | guid 								 | title 			 | status     |
	    | Ibd42469eb7e111e598dc8b09b4f043e0	 | Entire agreement  | Maintained  |



#Test not valid: no document found with resource history in AU; provided guid is from uk
@gold
Scenario: [817979] As a PL AU login/password user I want to view resource history section
	Given ANZ user navigates directly to document with guid "I53a7ad86694811eaadfea82903531a62"
	When the user scrolled to the bottom of the document
	Then the user can see 3 latest resource histories displayed 
	When user clicks on 'View All' to view all resource histories
    Then the user can now see more than 3 resource history entries
	When user clicks on 'View Latest' to view latest resource histories
    Then the user can see 3 latest resource histories displayed



