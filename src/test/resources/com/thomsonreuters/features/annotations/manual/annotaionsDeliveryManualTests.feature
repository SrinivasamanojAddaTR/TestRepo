@manual
Feature: verify the rich text annotations and inline notes in the delivered documents

  Background:
    Given PL+ user is logged in
    When user navigates directly to document with guid "I33f105ace8cd11e398db8b09b4f043e0"

  @e2e
  Scenario: verify delivery of a document with annotations and inline notes by email
    When adds annotation and inline notes to this document by selecting some rich text and adding a note
    And emails this document with Table of contents and annotations checkbox ON
    And select the document Format as RTF/PDF/Microsoft Word/Resource Link Only
    Then document should be appropriately emailed as selected format
    And the user verifies the delivered document for the following
      | Annotations have to be delivered with the formatted text                                                          |
      | And  the links (download, e-mail) will direct the user to the hyperlinked URL by opening a new tab in the browser |

  @e2e
  Scenario: verify delivery of a document with annotations and inline notes by print
    When adds annotation and inline notes to this document by selecting some rich text and adding a note
    And prints this document with Table of contents and annotations checkbox ON
    Then document should be appropriately printed
    And the user verifies the delivered document for the following
      | Annotations have to be delivered with the formatted text                                                          |
      | And  the links (download, e-mail) will direct the user to the hyperlinked URL by opening a new tab in the browser |

  @e2e
  Scenario: verify delivery of a document with annotations and inline notes by download
    Given PL+ user is logged in
    When adds annotation and inline notes to this document by selecting some rich text and adding a note
    And downloads this document with Table of contents and annotations checkbox ON
    And select the document Format as RTF/PDF/Microsoft Word
    Then document should be appropriately emailed/downloaded/printed as selected format
    And the user verifies the delivered document for the following
      | Annotations have to be delivered with the formatted text                                                          |
      | And  the links (download, e-mail) will direct the user to the hyperlinked URL by opening a new tab in the browser |

  @AnnotationsSmokeTests
  Scenario: verify delivery of a document with annotations and inline notes by email/download/print
    When adds annotation and inline notes to this document by selecting some rich text and adding a note
    And delivery this document with Table of contents and annotations checkbox ON
    And select the document any Format(RTF/PDF/Microsoft Word/Resource Link Only)
    Then document should be appropriately delivered as selected format
    And verify that all (owner & reviewer) annoations/inline notes are delivered with metadata
    And no html/xml tags should be delivered along with notes text
    And Date should be delivered according to uk date format
    And Annotations and inline notes have to be delivered with proper timestamp

    @bug
  Scenario: Bug 809093: verify timestamp of delivered document with annotations and inline notes by email/download/print
    When adds annotation and inline notes to this document by selecting some rich text and adding a note
    And delivery this document with Table of contents and annotations checkbox ON
    And select the document any Format(RTF/PDF/Microsoft Word/Resource Link Only)
    Then document should be appropriately delivered as selected format
    And Annotations and inline notes have to be delivered with proper timestamp

  Scenario: verify delivery of a results list with notes added icons by email/download/print
    Given user logged into PL+
    When search with "children" in search box
    And open the first result from the list
    And adds annotation and inline notes to this document by selecting some rich text and adding a note
    When search with "children" in search box
    And select and couple of document in the search list including first result
    And delivery this results with List of items checkbox ON
    And select the document any Format(RTF/PDF/Microsoft Word/Resource Link Only)
    Then document should be appropriately delivered as selected format
    And the user verifies the delivered document with list of selected results
    And user should be able to see the "notes added" icon for the first result

  Scenario: verify delivery of a document with notes by email/download/print from results list
      Given user logged into PL+
      When search with "children" in search box
      And open the first result from the list
      And adds annotation and inline notes to this document by selecting some rich text and adding a note
      When search with "children" in search box
      And select document in the results list
      And delivery this selected item with annotations checkbox ON
      And select the document any Format(RTF/PDF/Microsoft Word/Resource Link Only)
      Then document should be appropriately delivered as selected format
      And verify that all (owner & reviewer) annoations/inline notes are delivered with metadata

  Scenario: Annotations delivery to verify PVA
    Given WLN user is logged in with following details
      | userName         | librarian1      |
      | product          | WLN             |
      | routing          | WLN_ANNOTATIONS |
      | mandatoryRouting | YES             |
      | newSession       | TRUE            |
    And user navigates directly to WLN document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
    And user added WLN new Inline Notes and doc level annotations
    Then verify saved Inline Notes and doc level annotations will be displayed with metadata in WLN
    Given PL+ user is logged in with following details
      | userName         | librarian1  |
    When user navigates directly to document with guid "Ic9d547982b9f11e598dc8b09b4f043e0"
    Then user should not be able to see the Inline Notes created in WLN site
    When user added WLN new Inline Notes and doc level annotations
    Then verify saved Inline Notes and doc level annotations will be displayed with metadata in WLN
    When delivery this document with Table of contents and annotations checkbox ON
    Then delivered document should display only Pl+ specific doc level annotations & inline notes
    And make sure wln annotations are not delivered as part of this document delivery
