Feature: [809395] I want to view the "Advisory Boards"

  Scenario: [809395] As a PLAU user I want to view the "Advisory Boards" pages. So that I can find out who the professional advisor are who advise Practical Law
    Given ANZ user is logged in with following details
      | userName | ANZtestuser2 |
     When user clicks on "Advisory Board" link
    Then the user verifies that the current PageTitle contains 'Advisory board'
    And the table of contents on Advisory Boards page is hidden
    And the user verifies that following text is present on Advisory Boards page
      """
      The Advisory Board advises on the direction of the service and ensures that it stays at the leading edge of practice. The combined experience of Practical Law's own legal writers and the board enables us to ensure that our resources are of the highest quality, are of practical use to those working in the field and reflect state-of-the-art methods of knowledge management.
      """
    And the table on Advisory Boards page includes following people
      | Peter Butler OAM RFD – Partner, Herbert Smith Freehills                         |
      | The Hon Reginald Barrett – NSW Supreme Court and Court of Appeal (retired)      |
      | Carolyn Austin – Special Counsel and Precedents Manager, K&L Gates              |
      | Rachel Launders – General Counsel and Company Secretary, Nine Entertainment Co. |
      | James Lonie – Partner, HWL Ebsworth, Sydney                                     |
