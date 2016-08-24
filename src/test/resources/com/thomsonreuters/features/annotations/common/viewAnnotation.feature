Feature: [825681] View annotation

  @deletionAnnotations
  Scenario: Verify that logged in user can see annotation at the top of document during the subsequent sessions
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I2c856ddc3fae11e698dc8b09b4f043e0" on PL AU website
    And user added new annotation
    Then verify saved annotations text will be displayed with metadata
    Then check that annotations at the top are expanded
    When user relogs in
    And user navigates directly to document with guid "I2c856ddc3fae11e698dc8b09b4f043e0" on PL AU website
    Then check that annotations at the top are expanded

  @deletionAnnotations
  Scenario: Verify that logged in user can collapse or expand annotation at the top of document
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "I2c856ddc3fae11e698dc8b09b4f043e0" on PL AU website
    And user added new annotation
    Then check that annotations at the top are expanded
    And user collaps annotations at the top
    Then check that annotations at the top are collapsed
    And user expands annotations at the top
    Then check that annotations at the top are expanded

  @deletionAnnotations
  Scenario Outline: Verify that user can see annotations at the top in different type of documents
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser1 |
    When user navigates directly to document with guid "<guid>" on PL AU website
    And user added new annotation
    Then check that annotations at the top are expanded
    Examples:
      | document_type                         | guid                              |
      | Practice Notes                        | I2436c8a5e0c011e598dc8b09b4f043e0 |
      | Standard documents and drafting notes | I1be9b44ae13311e598dc8b09b4f043e0 |
      | Standard clauses and drafting notes   | I2c856dbe3fae11e698dc8b09b4f043e0 |
      | Checklists                            | Ib2269c0eb8af11e598dc8b09b4f043e0 |
      | International Documents (Country Q&A) | Ieb49d7971cb511e38578f7ccc38dcbee |
      #| Articles                              |                                   |

  @deletionAnnotations
  Scenario: Verify that logged in user can see  inline annotation during the subsequent sessions
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "Ifbf497fd995811e598dc8b09b4f043e0" on PL AU website
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata
    And user relogs in
    And user navigates directly to document with guid "Ifbf497fd995811e598dc8b09b4f043e0" on PL AU website
    Then check that inline annotations is collapsed

  @deletionAnnotations
  Scenario Outline: Verify that user can see inline annotation in different type of documents
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "<guid>" on PL AU website
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata
    And user refreshes page
    Then check that inline annotations is collapsed
    Examples:
      | document_type                         | guid                              |
      | Practice Notes                        | I2436c8a5e0c011e598dc8b09b4f043e0 |
      | Standard documents and drafting notes | I1be9b44ae13311e598dc8b09b4f043e0 |
      | Standard clauses and drafting notes   | I2c856dbe3fae11e698dc8b09b4f043e0 |
      | Checklists                            | Ib2269c0eb8af11e598dc8b09b4f043e0 |
      | International Documents (Country Q&A) | Ieb49d7971cb511e38578f7ccc38dcbee |
     # | Articles                              |                                   |

  @deletionAnnotations
  Scenario: Verify that logged in user can collapse or expand inline annotation
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When user navigates directly to document with guid "Ifbf497fd995811e598dc8b09b4f043e0" on PL AU website
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    Then verify saved annotations text will be displayed with metadata
    And user refreshes page
    Then check that inline annotations is collapsed
    When the user clicks on the inline annotation icon
    Then the inline annotation is expanded
    When the user clicks on minimize option inline annotation icon
    Then check that inline annotations is collapsed

  @deletionAnnotations
  Scenario: User should be able to see notes added icon in search results list if user added annotation at the top
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When the user runs a free text search for the query "Children"
    And the user opens '1' link in the search result and store its title and guid
    Then user added new annotation
    When the user runs a free text search for the query "Children"
    Then the search result "1" document will be displayed along with nodes added link

  @deletionAnnotations
  Scenario: User should be able to see notes added icon in search results list if user added  inline annotation
    Given ANZ user is logged in with following details
      | userName | auAnnotationUser3 |
    When the user runs a free text search for the query "Test"
    And the user opens '1' link in the search result and store its title and guid
    And user looks through the body of the document and select text with colour "blueBox"
    And user added new inline annotation
    When the user runs a free text search for the query "Test"
    Then the search result "1" document will be displayed along with nodes added link
