Feature: [818051] Document - Standard delivery features 

# bug 842166 Bug [PLAU only] Delivery: For ANZ documents delivery options for drafting notes not present

#intermittent failures on demo
#bug 834611 Bug [PLAU & PLUK] document is not available by plc ref on demo pc1


  Background:
    Given ANZ user is logged in

###########################################################################################################################################
#email delivery
###########################################################################################################################################
#mailbox: tr-anz-tester1@epam-email-pluk.thomsonreuters.com
#password: tranztest



  @smoke @gold @sanity
  Scenario Outline: [818051] Email document - MS Word format-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic download options as follows
      | To     | <mailbox>      |
      | Format | Microsoft Word |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<subject>"

    Examples:
      | guid                              | subject                                                | mailbox                                           | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form: private acquisitions | tr-anz-tester6@epam-email-pluk.thomsonreuters.com | 1  |


  Scenario Outline: [818051] Email document - RTF format-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic download options as follows
      | Format | Word Processor (RTF) |
      | To     | <mailbox>            |
    And Email button is clicked
    And user receives an email at "<mailbox>" with document in Word Processor (RTF) format and with subject "<subject>"
    Examples:
      | guid                              | subject                                                | mailbox                                           | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form: private acquisitions | tr-anz-tester5@epam-email-pluk.thomsonreuters.com | 1  |
	
	#intermittent failures on demo
	#bug 834611 Bug [PLAU & PLUK] document is not available by plc ref on demo pc1

  Scenario Outline: [818051][847182] Email document - Resource Link Only format-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <title> |
    When the user edits the basic download options as follows
      | Format | Resource Link Only |
      | To     | <mailbox>          |
    And Email button is clicked
    Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<resourceid>" and with subject "<title>"
    When user copies the link in valid format from email into the browser
    Then user should be presented with proper document "<title>"
    Examples:
      | guid                              | title                                                  | mailbox                                           | resourceid | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form: private acquisitions | tr-anz-tester6@epam-email-pluk.thomsonreuters.com | W-001-0164 | 1  |

  Scenario Outline: [818051] Email document - PDF format, table of contents included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic download options as follows
      | Format            | PDF                                               |
      | To                | tr-anz-tester7@epam-email-pluk.thomsonreuters.com |
      | Table of Contents | Selected                                          |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document
    And the document includes document body that contains text "<docText>"
    And the document includes table of contents that contains title "<contentsText>"
    Examples:
      | guid                              | subject                                                | docText                      | contentsText             | mailbox                                           | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form: private acquisitions | When acting for a transferee | Execution by the parties | tr-anz-tester7@epam-email-pluk.thomsonreuters.com | 1  |


  Scenario Outline: [818051] Email document - PDF format, table of contents not included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic download options as follows
      | Format            | PDF          |
      | To                | <mailbox>    |
      | Table of Contents | Not Selected |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document
    And the document includes document body that contains text "<docText>"
    And the document does not include table of contents that contains title "<contentsText>"
    Examples:
      | guid                              | subject                                                | docText                      | contentsText             | mailbox                                           | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form: private acquisitions | When acting for a transferee | Execution by the parties | tr-anz-tester8@epam-email-pluk.thomsonreuters.com | 1  |


  Scenario Outline: [818051] Email document - PDF format, drafting notes not included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject                     | <subject>    |
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    When the user edits the basic download options as follows
      | Format | PDF       |
      | To     | <mailbox> |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document
    And the document includes document body that contains text "<docText>"
    And the document does not include drafting notes
    Examples:
      | guid                              | subject                           | docText                  | mailbox                                           | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes: transfer of shares | The chairperson reported | tr-anz-tester20@epam-email-pluk.thomsonreuters.com | 1  |


  Scenario Outline: [818051] Email document - PDF format, drafting notes only-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject                     | <subject>    |
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    When the user edits the basic download options as follows
      | Format              | PDF       |
      | To                  | <mailbox> |
      | Only Drafting Notes | Selected  |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document
    And the document does not include document body that contains text "<docText>"
    And the document includes drafting notes
    Examples:
      | guid                              | subject                           | docText                  | mailbox                                           | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes: transfer of shares | The chairperson reported | tr-anz-tester5@epam-email-pluk.thomsonreuters.com | 1  |


  Scenario Outline: [818051] Email document - PDF format, drafting notes included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on email delivery option for the document
    Then the user should be able to see Email basic tab options as follows
      | Subject                     | <subject>    |
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    When the user edits the basic download options as follows
      | Format                      | PDF       |
      | To                          | <mailbox> |
      | Document and Drafting Notes | Selected  |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in PDF format and with subject "<subject>" and downloads the document
    And the document includes document body that contains text "<docText>"
    And the document includes drafting notes
    Examples:
      | guid                              | subject                           | docText                  | mailbox                                           | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes: transfer of shares | The chairperson reported | tr-anz-tester6@epam-email-pluk.thomsonreuters.com | 1  |
		
		
		
		
###########################################################################################################################################
#download
###########################################################################################################################################
  @smoke @gold @sanity
  Scenario Outline: [818051] Download document - MS Word format-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format | Microsoft Word |
    Then user downloads the document with name "<name>" and extension ".doc"
    Examples:
      | guid                              | name                                                  | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | 1  |


  Scenario Outline: [818051] Download document - RTF format-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format | Word Processor (RTF) |
    Then user downloads the document with name "<name>" and extension ".rtf"
    Examples:
      | guid                              | name                                                  | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | 1  |

  Scenario Outline: [818051] Download document - PDF format, with table of contents-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format            | PDF      |
      | Table of Contents | Selected |
    Then user downloads the document with name "<name>" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document includes table of contents that contains title "<contentsText>"
    Examples:
      | guid                              | name                                                  | docText                      | contentsText             | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | When acting for a transferee | Execution by the parties | 1  |

  Scenario Outline: [818051] Download document - PDF format, without table of contents-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    And the user edits the basic download options as follows
      | Format            | PDF          |
      | Table of Contents | Not Selected |
    Then user downloads the document with name "<name>" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document does not include table of contents that contains title "<contentsText>"
    Examples:
      | guid                              | name                                                  | docText                      | contentsText             | id |
      | I53cfffa798de11e598dc8b09b4f043e0 | Completing a share transfer form private acquisitions | When acting for a transferee | Execution by the parties | 1  |


  Scenario Outline: [818051] Download document - PDF format, drafting notes not included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    Then the user should be able to see Download basic tab options as follows
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    And the user edits the basic download options as follows
      | Format | PDF |
    Then user downloads the document with name "<name>" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document does not include drafting notes

    Examples:
      | guid                              | name                             | docText                  | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes transfer of shares | The chairperson reported | 1  |


  Scenario Outline: [818051] Download document - PDF format, drafting notes included-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    Then the user should be able to see Download basic tab options as follows
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    And the user edits the basic download options as follows
      | Format                      | PDF      |
      | Document and Drafting Notes | Selected |
    Then user downloads the document with name "<name>" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document includes drafting notes
    Examples:
      | guid                              | name                             | docText                  | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes transfer of shares | The chairperson reported | 1  |


  Scenario Outline: [818051] Download document - PDF format, drafting notes only-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Download delivery option for the document
    Then the user should be able to see Download basic tab options as follows
      | Document                    | Selected     |
      | Only Drafting Notes         | Not Selected |
      | Document and Drafting Notes | Not Selected |
    And the user edits the basic download options as follows
      | Format              | PDF      |
      | Only Drafting Notes | Selected |
    Then user downloads the document with name "<name>" and extension ".pdf"
    And the document does not include document body that contains text "<docText>"
    And the document includes drafting notes

    Examples:
      | guid                              | name                             | docText                  | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | Board minutes transfer of shares | The chairperson reported | 1  |
	
###########################################################################################################################################
#Print
###########################################################################################################################################
  @smoke @gold
  Scenario Outline: [818051] Print document-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    When clicks on Print delivery option for the document
    And user prints the document with name "Print" and extension ".pdf"
    Examples:
      | guid                              | id |
      | Ifb5c26ca995811e598dc8b09b4f043e0 | 1  |
		
	