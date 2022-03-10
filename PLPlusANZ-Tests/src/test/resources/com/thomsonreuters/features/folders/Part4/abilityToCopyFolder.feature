@ffh
Feature: [821542] As a PLAU User I want to copy a folder and its contents So that I can save time creating folders and adding documents

  Scenario: 
    Given ANZ user is logged in
    When API cleans all folders and history
    And user relogs in
    And the user clicks on 'Folders' link on the header
    And the user creates new folder "folder1" in "root" folder
    And the user creates new folder "folder2" in "root" folder
    And the user copies "folder2" in "folder1" folder
    Then the folder "folder2" appears in the "folder1" folder
    And the folder "folder2" appears in the "root" folder
