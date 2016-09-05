Feature: [821543] As a PLAU User I want to move a folder from one folder to another So that I can organise my folders in the structure I require

  Scenario: 
    Given ANZ user is logged in
    When API cleans all folders and history
    And user relogs in
    And the user clicks on 'Folders' link on the header
    And the user creates new folder "folder1" in "root" folder
    And the user creates new folder "folder2" in "root" folder
    And the user moves "folder2" in "folder1" folder
    Then the folder "folder2" appears in the "folder1" folder
    And the folder "folder2" disappears from "folder1" folder level
