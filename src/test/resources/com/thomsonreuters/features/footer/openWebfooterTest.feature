Feature: [850127]OPEN WEB (TEST ONLY) Footer Pages
As a open web user
I want to view and link to footer pages
So that I can view and find out more information about practical law

  Background: 
    Given PL+ ANZ user navigates to home page 

  Scenario: [809111]User verifies the "Footer Link Items" for the respetive Page's links
    When user should see footer
    Then user should see the following FooterLinks under heading "About" with links to pages
      | FooterLink          | LinksToPage                               |
      | About Practical Law | /Browse/Home/About/AboutUs                |
      | Testimonials        | /Browse/Home/About/Testimonials           |
      | Careers             | http://thomsonreuters.com/en/careers.html |
    Then user should see the following FooterLinks under heading "Our team" with links to pages
      | Meet the Team      | /Browse/Home/About/OurTeam       |
      | Advisory Board     | /Browse/Home/About/AdvisoryBoard |
      | Contributing Firms | /Browse/Home/About/Contributors  |
    Then user should see the following FooterLinks under heading "Product support" with links to pages
      | User Guides      | training.thomsonreuters.com.au/products/practical-law-au/ |
      | Request Training | training.thomsonreuters.com.au/request-training/          |
      | Feedback         | javascript                                                |

  Scenario Outline: User verifies the functionality of footer links that lead to internal pages
	When user clicks on "<footer>" link
	Then the user is presented with a page with header "<header>"
    And the page URL contains "<url>"
	Examples: 
	|footer					|header			|url							|
	|About Practical Law	|About Us		|/Browse/Home/About/AboutUs   	|
	|Testimonials 			|Testimonials 	|/Browse/Home/About/Testimonials   	|
	|Meet the Team			|Our team		|/Browse/Home/About/OurTeam   	|
	|Advisory Board			|Advisory board	|/Browse/Home/About/AdvisoryBoard   	|
	|Contributing Firms		|Contributors	|/Browse/Home/About/Contributors  	|
	|How are we using your information?|How we are using your information | Browse/Home/About/HowWeAreUsingYourInformation|
	

	Scenario Outline: User verifies the functionality of footer links that lead to external pages
	When user clicks on "<footer>" link
    Then user was taken to url "<url>"
    And user should see the "<PageTitle>" page is opened
	Examples: 
	|footer				|url							|PageTitle|
	|Careers			|http://thomsonreuters.com/en/careers.html  	|Careers|
	|User Guides		|http://training.thomsonreuters.com.au/products/practical-law-au/|Training at Thomson Reuters AU|
	|Request Training	|http://training.thomsonreuters.com.au/request-training/|Request Training|
	|Privacy			|http://legal.thomsonreuters.com.au/terms/privacy-policy.aspx|Privacy Policy - Thomson Reuters Australia|
    |Master Terms       |http://legal.thomsonreuters.com.au/terms/pdf/AU-Thomson-Reuters-Master-Service-Agreement-Master-Terms-v1.1-03-16.pdf| AU-Thomson-Reuters-Master-Service-Agreement-Master-Terms-v1.1-03-16.pdf |
	