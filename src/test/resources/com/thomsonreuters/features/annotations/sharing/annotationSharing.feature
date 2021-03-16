Feature: Sharing annotations


  @gold @AnnotationsSmokeTests @e2e @deletionAnnotations
  Scenario:User should be able to share the annotations and stop sharing
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user has shared the annotations with another contact "PL_TEST_GEN, 0057"
    And user logs out
    And ANZ user is logged in with following details
      | userName | myShareAnnotationUser |
    And user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    Then shared annotation should be displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    And user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user clicks the saved annotation
    And user clicks on shared with and stop sharing annotation with "0057 PL_TEST_GEN" user
    And saving the annotation
    And user logs out
    And ANZ user is logged in with following details
      | userName | myShareAnnotationUser |
    And user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And shared annotation is not displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |

  @gold @AnnotationsSmokeTests @e2e @deletionAnnotations
  Scenario:User should be able to share the annotation with group
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user has shared the annotations with new group and "PL_TEST_GEN, 0058" as member
    And user logs out
    And ANZ user is logged in with following details
      | userName | userForAnnotationGroup |
    And user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    Then shared annotation should be displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |

  @gold @deletionAnnotations
  Scenario: User is able to share group with users from his organisation
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user shared the annotations with group and "PL_TEST_GEN, 0057" as member and this group available to others
    And user logs out
    And ANZ user is logged in with following details
      | userName | userForAnnotationGroup |
    And user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    Then user verifies that shared group is displayed on groups tab
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |

  @gold @deletionAnnotations
  Scenario: User is able to share annotations with previously shared users
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ic8bab96de0b511e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And verify the share with Contacts and Previous Contacts link is present
    And user has shared the annotations with another contact "PL_TEST_GEN, 0057"
    And user clicks the saved annotation
    And user clicks on shared with and stop sharing annotation with "0057 PL_TEST_GEN" user
    And saving the annotation
    And user clicks the saved annotation
    And user clicks on previously shared
    Then user should see "0057 PL_TEST_GEN" user

  @gold @AnnotationsSmokeTests @e2e
  Scenario:User can create, edit and delete a group
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "I445609a69ab311e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user creates new group
    Then user verifies that this group is displayed and user count is "2"
    Then group info pop up contains users from group
    And user edits the group "0057 PL_TEST_GEN" and remove one memeber
    Then user verifies that user count for group is "1"
    And user removes group
    Then message "The personal group was deleted" appears
