Feature: [821544] As a PLAU User I want to rename a folder so that I can rename the folder based on my needs


  Scenario Outline: 
     Given ANZ user is logged in
    When API cleans all folders and history
    And user relogs in
    And the user clicks on 'Folders' link on the header
    And the user creates new folder "<folder1>" in "<parentFolder1>" folder
    Then the folder "<folder1>" appears in the "<parentFolder1>" folder
    When the user renames folder "<folder1>" to "<folder2>" by double click
    Then the folder "<folder2>" appears in the "<parentFolder1>" folder
    When the user renames folder "<folder2>" to "<folder3>"
    Then the folder "<folder3>" appears in the "<parentFolder1>" folder
    When the user deletes the folder "<folder3>"
    Then the folder "<folder3>" disappear from "<parentFolder1>" folder

    Examples: 
      | folder1 | parentFolder1 | folder2 | folder3 |
      | new123  | root          | pl1245  | pl1     |
