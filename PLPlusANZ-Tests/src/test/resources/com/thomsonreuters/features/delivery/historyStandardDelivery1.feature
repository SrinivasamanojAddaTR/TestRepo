Feature: [831297] View History Page - Deliver Documents  listed within my document history

#some tests may intermittently fail due to:
#bug 830073 [REGRESSION] Several documents don't display in History

#The user name was ProdANZtestuser2
  Background:
    Given ANZ user is logged in with following details
      | userName | ProdANZtestuser2 |

###############################################################################################################
## Print 	
###############################################################################################################

  @smoke @nonDemo
  Scenario Outline: [831297] Print a single document-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When the user clicks on 'History' link on the header
    And the user clicks on 'Documents' tab on the History page
    And the user selects the checkbox associated with document "1"
    And user clicks on Print delivery option for History
    And user prints the document with name "Print" and extension ".pdf"
    Examples:
      | guid                              | id |
      | If13ba3b4d4d811e598dc8b09b4f043e0 | 1  |



		