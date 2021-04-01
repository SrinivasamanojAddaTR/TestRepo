Feature: [821929] As a PLAU User I want to create a group So that I can share a folder with a group of users
  [821927] As a PLAU User I want to view my contacts and groups So that I can insert people and groups to the list of recipients I want to share my folder with

  Background:
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |


  Scenario:
    When API cleans all folders and history
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When API cleans all folders and history
    Given ANZ user is logged in with following details
      | userName | ANZtestuser4 |
    When API cleans all folders and history


  Scenario Outline: Share via email address
    When API cleans all folders and history and user relogs in
    When the user clicks on 'Folders' link on the header
    And the user creates new folder "<folder>" in "<parentFolder>" folder
    Then the folder "<folder>" appears in the "<parentFolder>" folder
    When the user "<owner>" shares the folder "<folder>" with the email "<emailToShare>"
    Then invitation email is received at "<emailToShare>" with link to PLAU

    Examples:
      | folder | parentFolder | owner        | emailToShare                                      |
      | share1 | root         | ANZtestuser2 | tr-anz-tester2@epam-email-pluk.thomsonreuters.com |


  Scenario Outline: Share with groups and endsharing
    When API cleans all folders and history and user relogs in
    When the user clicks on 'Folders' link on the header
    And the user creates new folder "<folder>" in "<parentFolder>" folder
    Then the folder "<folder>" appears in the "<parentFolder>" folder
    When the user share the folder "<folder>" with new group "<group>" and member 'PL_TEST, 0008' as "<role>"
    Then new message about the folder "<folder>" sharing is displayed
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" appears in shared folders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When the user clicks on 'Folders' link on the header
    And the user unshare the folder "<folder>"
    Then new message about the folder "<folder>" is no longer shared
    And the user closes share folder popup
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" is absent in shared folders

    Examples:
      | folder | parentFolder | role     | group |
      | share2 | root         | Reviewer | gr1   |

  Scenario Outline: Share with contacts and endsharing
  When API cleans all folders and history and user relogs in
    When the user clicks on 'Folders' link on the header
    And the user creates new folder "<folder>" in "<parentFolder>" folder
    Then the folder "<folder>" appears in the "<parentFolder>" folder
    When the user share the folder "<folder>" with contact 'PL_TEST, 0008' as "<role>" and contact 'PL_TEST, 0009' as "<role2>"
    Then new message about the folder "<folder>" sharing is displayed
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" appears in shared folders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser4 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" appears in shared folders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When the user clicks on 'Folders' link on the header
    And the user unshare the folder "<folder>"
    Then new message about the folder "<folder>" is no longer shared
    And the user closes share folder popup
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" is absent in shared folders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser4 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" is absent in shared folders

    Examples:
      | folder | parentFolder | role     | role2       |
      | share3 | root         | Reviewer | Contributor |

  Scenario Outline: [821925] Edit the Users I have shared my folders with
    When API cleans all folders and history and user relogs in
    When the user clicks on 'Folders' link on the header
    And the user creates new folder "<folder>" in "<parentFolder>" folder
    And the user creates new folder "<folder2>" in "<folder>" folder
    When the user share the folder "<folder>" without subfolders with contact 'PL_TEST, 0008' as "<role>"
    Then new message about the folder "<folder>" sharing is displayed
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" appears in shared folders
    And user is not able to expand shared folder "<folder>"
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When the user clicks on 'Folders' link on the header
    When the user adds contact 'PL_TEST, 0009' as "<role2>" to shared folder "<folder>" with subfolders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser4 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" appears in shared folders
    And user is able to expand shared folder "<folder>"
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When the user clicks on 'Folders' link on the header
    When the user removes contact 'PL_TEST, 0009' from shared folder "<folder>"
    Given ANZ user is logged in with following details
      | userName | ANZtestuser4 |
    When the user clicks on 'Folders' link on the header
    Then the folder "<folder>" is absent in shared folders
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then user with role "<role>" is not able to create new folder "<folder3>" in shared folder "<folder>"
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
    When the user clicks on 'Folders' link on the header
    When the user changes contact 'PL_TEST, 0008' permissions to "<role2>" for shared folder "<folder>"
    Given ANZ user is logged in with following details
      | userName | ANZtestuser3 |
    When the user clicks on 'Folders' link on the header
    Then user with role "<role2>" is able to create new folder "<folder3>" in shared folder "<folder>"

    Examples:
      | folder | parentFolder | role     | role2       | folder2      | folder3       |
      | share4 | root         | Reviewer | Contributor | nestedshare4 | nested2share4 |
