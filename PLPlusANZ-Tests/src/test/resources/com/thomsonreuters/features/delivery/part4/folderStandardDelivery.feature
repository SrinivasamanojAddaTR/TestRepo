@ffh
Feature: [821556] View Folders Page - Deliver Documents within a folder

  Background:
    Given ANZ user is logged in
    When API cleans all folders and history and user relogs in

###############################################################################################################
## Email
###############################################################################################################

  Scenario Outline: [821556] Email document in Microsoft Word format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    And the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic email options as follows
      | To      | <mailbox>      |
      | Format  | Microsoft Word |
      | Subject | <newSubject>   |
    And Email button is clicked
    Then user receives an email at "<mailbox>" with document in Microsoft Word format and with subject "<newSubject>"
    Examples:
      | folder     | subject                                 | mailbox                                           | id | newSubject                                   |
      | testFolder | 1 full text item from testFolder folder | tr-anz-tester4@epam-email-pluk.thomsonreuters.com | 1  | 1 full text item from testFolder folder Word |

  Scenario Outline: [821556][847182]  Email resource link only-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    And the user should be able to see Email basic tab options as follows
      | Subject | <subject> |
    When the user edits the basic email options as follows
      | To      | <mailbox>          |
      | Format  | Resource Link Only |
      | Subject | <title>            |
    And Email button is clicked
    Then user receives an email at "<mailbox>" without attachments and with link to the AU document "<guid>" and with subject "<title>"
    When user copies the link in valid format from email into the browser
    Then user should be presented with proper document "<title>"
    Examples:
      | folder     | guid                              | subject                                 | mailbox                                           | title                | id |
      | testFolder | Ifb5c2817995811e598dc8b09b4f043e0 | 1 full text item from testFolder folder | tr-anz-tester4@epam-email-pluk.thomsonreuters.com | Financial assistance | 1  |


###############################################################################################################
## Print
###############################################################################################################
  Scenario Outline: [821556] Print a single document-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
    And user prints the document with name "Print" and extension ".pdf"
    Examples:
      | folder     | id |
      | testFolder | 1  |

###############################################################################################################
## Table of content
###############################################################################################################
  Scenario: Table of content checkbox for Email is memorized
    Given the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    And the user edits the basic email options as follows
      | Table of Contents | Selected                                          |
      | To                | tr-anz-tester1@epam-email-pluk.thomsonreuters.com |
    And Email button is clicked
    And user relogs in
    And the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Email delivery option for Folder
    Then the user should be able to see Email basic tab options as follows
      | Table of Contents | Selected |

  Scenario: Table of content checkbox for Print is memorized
    Given the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
    And the user edits the basic print options as follows
      | Table of Contents | Not Selected |
    Then user prints the document with name "Print" and extension ".pdf"
    When user relogs in
    And the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Print delivery option for Folder
    Then the user should be able to see Print basic tab options as follows
      | Table of Contents | Not Selected |

  Scenario: Table of content checkbox for Download is memorized
    Given the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Table of Contents | Selected |
      | Format            | PDF      |
    Then user downloads the document with name "Full text items from testFolder folder" and extension ".pdf"
    When user relogs in
    And the user opens testFolder folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    Then the user should be able to see Download basic tab options as follows
      | Table of Contents | Selected |
