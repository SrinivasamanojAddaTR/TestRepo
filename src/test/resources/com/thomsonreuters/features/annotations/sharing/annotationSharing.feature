Feature: Sharing annotations

  @AnnotationsSmokeTests @e2e @deletionAnnotations
  Scenario:User should be able to share the annotations and stop sharing
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user has shared the annotations with another contact "PL_TEST_GEN, 0057"
    And user logs out
    And ANZ user is logged in with following details
      | userName | myShareAnnotationUser |
    And user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" on PL AU website
    Then shared annotation should be displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    And user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user clicks the saved annotation
    And user clicks on shared with and stop sharing annotation with "0057 PL_TEST_GEN" user
    And saving the annotation
    And user logs out
    And ANZ user is logged in with following details
      | userName | myShareAnnotationUser |
    And user navigates directly to document with guid "Ifb5c26cb995811e598dc8b09b4f043e0" on PL AU website
    And shared annotation is not displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |

  @AnnotationsSmokeTests @e2e @deletionAnnotations
  Scenario:User should be able to share the annotation with group
    Given ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
    When user navigates directly to document with guid "Ifb5c2817995811e598dc8b09b4f043e0" on PL AU website
    And user closes disclaimer in the bottom
    And user navigates to annotations textbox with text
    And user has shared the annotations with new group and "PL_TEST_GEN, 0058" as member
    And user logs out
    And ANZ user is logged in with following details
      | userName | userForAnnotationGroup |
    And user navigates directly to document with guid "Ifb5c2817995811e598dc8b09b4f043e0" on PL AU website
    Then shared annotation should be displayed
    And user logs out
    And ANZ user is logged in with following details
      | userName | shareAnnotationUser1 |
