
Feature: [820474] Glossary Delivery
  As a PLAU User
  I want to deliver a glossary document
  So that I can read the document offline


  Background:
    Given ANZ user is logged in
    And user navigates to a glossary page

#intermittent issue:
#848623 Bug [PLAU only] Glossary: Error when opening glossary article (Stream not readable)

  Scenario Outline: Email glossary term - MS Word format
    Given the user clicks on glossary term "<term>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <term> |
    When the user edits the basic download options as follows
      | To      | <mailbox>          |
      | Format  | Microsoft Word     |
      | Subject | <term> - test Word |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<term> - test Word"
    Examples:
      | term       | mailbox                   |
      | Disability | tr-anz-tester2@epam-email-pluk.thomsonreuters.com |

  Scenario Outline: Email glossary term - RTF format
    Given the user clicks on glossary term "<term>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <term> |
    When the user edits the basic download options as follows
      | Format  | Word Processor (RTF) |
      | To      | <mailbox>            |
      | Subject | <term> - test RTF    |
    And Email button is clicked
    And user receives an email at "<mailbox>" with document in Word Processor (RTF) format and with subject "<term> - test RTF"
    Examples:
      | term       | mailbox                   |
      | Disability | tr-anz-tester2@epam-email-pluk.thomsonreuters.com |


  Scenario Outline: Email glossary term - PDF format
    Given the user clicks on glossary term "<term>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <term> |
    When the user edits the basic download options as follows
      | Format  | PDF               |
      | To      | <mailbox>         |
      | Subject | <term> - test PDF |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<term> - test PDF" and downloads the document
    And the document includes document body that contains text "<docText>"
    Examples:
      | term       | docText                          | mailbox                   |
      | Disability | A disability may currently exist | tr-anz-tester2@epam-email-pluk.thomsonreuters.com |

  Scenario Outline: [847182] Email glossary term - Resource Link Only format
    Given the user clicks on glossary term "<term>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <term> |
    When the user edits the basic download options as follows
      | Format  | Resource Link Only |
      | To      | <mailbox>          |
      | Subject | <term> - test link |
    And Email button is clicked
    Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "<term> - test link"
    When user copies the link in valid format from email into the browser
    Then user should be presented with proper document "<term>"
    Examples:
      | term       | guid                              | mailbox                   |
      | Disability | Ice50947ba27811e598dc8b09b4f043e0 | tr-anz-tester2@epam-email-pluk.thomsonreuters.com |
		
		
		
###########################################################################################################################################
#download
###########################################################################################################################################


  Scenario Outline: Download glossary term - MS Word format
    Given the user clicks on glossary term "<term>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format | Microsoft Word |
    Then user downloads the document with name "<term>" and extension ".doc"
    Examples:
      | term       |
      | Disability |


  Scenario Outline: Download glossary term - RTF format
    Given the user clicks on glossary term "<term>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format | Word Processor (RTF) |
    Then user downloads the document with name "<term>" and extension ".rtf"
    Examples:
      | term       |
      | Disability |


  Scenario Outline: Download glossary term - PDF format
    Given the user clicks on glossary term "<term>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format | PDF |
    Then user downloads the document with name "<term>" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    Examples:
      | term       | docText                          |
      | Disability | A disability may currently exist |


###########################################################################################################################################
#print
###########################################################################################################################################

  Scenario Outline: Print glossary term
    Given the user clicks on glossary term "<term>"
    When clicks on Print delivery option for the document
    Then user prints the document with name "Print" and extension ".pdf"
    Examples:
      | term       |
      | Disability |
		