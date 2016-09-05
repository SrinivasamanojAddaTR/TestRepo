Feature: [813599] As a PLAU user I want my search terms to be highlighted So that I can easily view where the search term hit was in the document

#bug 863546:Bug [PLAU & PLUK] Search: Less, More and Most Detail icon titles should be in sentence case

  @bug 
  Scenario Outline: [813599] As a PLAU user I want my search terms to be highlighted So that I can easily view where the search term hit was in the document
    Given ANZ user is logged in
    When the user runs a free text search for the query "<query>"
    And the user can select the option to show most detail
    Then for each search result "<rank>" the matching search term is highlighted "<highlightedTerm>" in snippet

    Examples:
      | query                | rank | highlightedTerm       |
      | checklist summarises | 1    | checklist, summarises |
      | document             | 1    | document              |
