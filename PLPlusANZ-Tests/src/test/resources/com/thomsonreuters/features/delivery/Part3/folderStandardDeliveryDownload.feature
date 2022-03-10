@ffh
Feature: [821556] View Folders Page - Deliver Documents within a folder - Download

  Background:
    Given ANZ user is logged in
    When API cleans all folders and history and user relogs in

  Scenario: Clean folders
    Given API cleans all folders and history

###############################################################################################################
## Download	
###############################################################################################################

  @smoke @gold
  Scenario Outline: [821556] Download documents in PDF format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Documents | Selected             |
      | Format    | PDF                  |
      | As        | A Single Merged File |
    Then user downloads the document with name "2 full text items from <folder> folder" and extension ".pdf"
    And the document includes document body that contains text "<docText1>"
    And the document includes document body that contains text "<docText2>"
    Examples:
      | folder     | docText1                            | docText2                                | id |
      | testFolder | This note summarises how the courts | Why is financial assistance prohibited? | 1  |

  Scenario Outline: [821556] Download documents in zip file-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Documents | Selected             |
      | Format    | PDF                  |
      | As        | Multiple Files (zip) |
    Then user downloads the document with name "2 full text items from <folder> folder" and extension "zip"
    Examples:
      | folder     | id |
      | testFolder | 1  |

  Scenario Outline: [821556] Download list of items in PDF format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | List of Items | Selected |
      | Format Value  | PDF      |
    Then user downloads the document with name "List of 2 items from <folder> folder" and extension ".pdf"
    And the document includes title "<title1>"
    And the document includes title "<title2>"
    And the document does not include document body that contains text "<docText1>"
    And the document does not include document body that contains text "<docText2>"
    Examples:
      | folder     | title1                                                                            | title2               | docText1                            | docText2                                | id |
      | testFolder | Summary of significant adverse action case law involving ill or injured employees | Financial assistance | This note summarises how the courts | Why is financial assistance prohibited? | 1  |

  Scenario Outline: [821556] Download list of items in CSV format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    And the user selects the checkbox associated with document "2"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | List of Items | Selected              |
      | Format Value  | Microsoft Excel (CSV) |
    Then user downloads the document with name "List of 2 items from <folder> folder" and extension ".csv"
    Examples:
      | folder     | id |
      | testFolder | 1  |

  Scenario Outline: [821556] Download document in RTF format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Format | Word Processor (RTF) |
    Then user downloads the document with name "Full text items from <folder> folder" and extension ".rtf"
    Examples:
      | folder     | id |
      | testFolder | 1  |

  Scenario Outline: [821556] Download document in Microsoft Word format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Format | Microsoft Word |
    Then user downloads the document with name "Full text items from <folder> folder" and extension ".doc"
    Examples:
      | folder     | id |
      | testFolder | 1  |

  Scenario Outline: [821556] Download document with table of contents in PDF format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Format            | PDF      |
      | Table of Contents | Selected |
    Then user downloads the document with name "Full text items from <folder> folder" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document includes table of contents that contains title "<contentsText>"
    Examples:
      | folder     | docText                              | contentsText                           | id |
      | testFolder | The general prohibition on financial | Why is financial assistance prohibited | 1  |

  Scenario Outline: [821556] Download document without table of contents in PDF format-<id>
    Given the user opens <folder> folder with the set of documents
      | Iabd12ee0995911e598dc8b09b4f043e0 |
      | Ifb5c2817995811e598dc8b09b4f043e0 |
    And the user selects the checkbox associated with document "1"
    When user clicks on Download delivery option for Folder
    And the user edits the basic download options as follows
      | Format            | PDF          |
      | Table of Contents | Not Selected |
    Then user downloads the document with name "Full text items from <folder> folder" and extension ".pdf"
    And the document includes document body that contains text "<docText>"
    And the document does not include table of contents that contains title "<contentsText>"
    Examples:
      | folder     | docText                              | contentsText                           | id |
      | testFolder | The general prohibition on financial | Why is financial assistance prohibited | 1  |