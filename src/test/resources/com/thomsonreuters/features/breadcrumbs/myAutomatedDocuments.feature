Feature: Contact Express

    
Scenario: Verify My Automated Documents is present in Silhoutte Menu 
Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
And continue to login with CE "ANZtest1 (1964876-ODVSQ4)" RegKey
When the user selects the user profile symbol
Then my Automated Documents option should be displayed


 Scenario: Verify clicking on My Automated Documents  navigates to Contract Express page
Given ANZ user is logged in with following details
      | userName | ANZtestuser1 |
And continue to login with CE "ANZtest1 (1964876-ODVSQ4)" RegKey
When the user selects the user profile symbol
Then my Automated Documents option should be displayed
 And the user selects My Automated Document option
 Then the user is directed to Contract Express page