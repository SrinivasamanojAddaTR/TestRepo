Feature: [850119] View Document Open Web

  Background:
    Given ANZ user is not logged in


  Scenario Outline: [850119] As a PL AU open web user I want to view document metadata-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    Then resource status "<resource status>" is displayed on the document right hand panel
    And resource type is displayed as "<documentType>" on right hand panel
    And following jurisdictions are displayed on the document right hand panel
      | <jurisdictions> |
    And maintained icon is <maintained> on the document right hand panel
    And 'Related Content' link is <related content> on the right hand panel
    Examples:
      | guid                              | resource status              | jurisdictions      | related content | documentType          | maintained    | id |
      | I7b0defd8cac011e598dc8b09b4f043e0 | Law stated as at 17-Dec-2015 | Australia,Victoria | Not displayed   | Legal update: archive | Not displayed | 1  |
      | Ibd42469eb7e111e598dc8b09b4f043e0 | Maintained                   | Australia,Federal  | displayed       | Standard documents    | displayed     | 2  |


  Scenario Outline: [850119] As a PL AU open web user I want to browse to related content-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    Then document title is displayed as "<title>"
    When the user clicks on "Related Content" link
    Then the user should see the related content section displayed
    And link in related content is present with title "<relatedtitle>" and status "<relatedstatus>"
    When the user clicks on link in related content with title "<relatedtitle>"
    Then document title is displayed as "<relatedtitle>"
    Examples:
      | guid                              | title                       | relatedtitle                                           | relatedstatus | id |
      | Ifb5c26cc995811e598dc8b09b4f043e0 | Due diligence: acquisitions | Legal due diligence review template: corporate records | Maintained    | 1  |

#bug/story  868506, 871345 - for trial link
  @gold
  Scenario Outline: [850119] As a PL AU open web user I want to view open web version of document-<id>
    Given ANZ user navigates directly to document with guid "<guid>"
    Then document title is displayed as "<title>"
    And author name "<author>" is displayed underneath the document title
    And document summary contains "<summary>"
    And document body does not contain text "<body>"
    And document body contains lines
      | Sign up for a free trial                                                                                                                                                  |
      | A free trial will give you:                                                                                                                                               |
      | Unlimited access to our online legal know-how services during the trial period.                                                                                           |
      | Training and support.                                                                                                                                                     |
      | The full text of this resource is available by logging in or by requesting a trial. If you have any questions, please contact us or your Practical Law Account Executive. |
      | Contact us                                                                                                                                                                |
      | From Australia: 1300 304 195                                                                                                                                              |
      | International: +61 2 8587 7980                                                                                                                                            |
      | Email: LTA.Care@thomsonreuters.com                                                                                                                                        |
      | Mon-Fri, 8am-6pm AEST                                                                                                                                                     |
    And "Register now" button is present in document body
    When the user clicks on button with title "Register now"
    Then the source document with guid "<guid>" remains open and new tab opens
    And the user is taken to "https://legal.thomsonreuters.com.au/products/practical-law/contact-us.aspx?utm_source=practical-law-product-site&utm_medium=banner&utm_campaign=practical-law" resource
    Examples:
      | guid                              | title                       | author                     | summary                         | body                | id |
      | Ifb5c26cc995811e598dc8b09b4f043e0 | Due diligence: acquisitions | by Practical Law Corporate | This note considers the purpose | The potential buyer | 1  |

  Scenario Outline: [850119] As a PL AU open web user I want to view full document if it is free to view-<id>
    When ANZ user navigates directly to document with guid "<guid>"
    Then document title is displayed as "<title>"
    And the full text document will be displayed including "<body>"
  @sanity
    Examples:
      | guid                              | title                                    | body                      | id |
      | I5bef34d7226611e698dc8b09b4f043e0 | Acquisition finance in Austria: overview | To compare answers across | 1  |
  @smoke @gold
    Examples:
      | guid                              | title   | body                           | id |
      | Idb567852df4611e598dc8b09b4f043e0 | Control | In some cases control is given | 2  |
	


	