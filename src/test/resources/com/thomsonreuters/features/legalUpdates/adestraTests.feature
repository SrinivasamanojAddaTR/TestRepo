Feature: [848639] As a PLAU User I want to unsubscribe from all services

  # do not remove this tag. this tag is to clean all users subcriptions.
  @smoke @gold @UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption
  Scenario Outline: 
    Given ANZ user is logged in with following details
      | routing          | ANZ_IAC      |
      | mandatoryRouting | YES          |
    Given a user is viewing the email preference page
    Given a user creates subscription to the "<region>" "<corporate>" email service with "<frequency>"
    Given a user "AUtestuser1@mailinator.com" has already subscribed to the "<corporate>" "<frequency>" email service "<region>"
    When the user checks the 'Yes' box in the Unsubscribe All section
    And the user clicks the 'Save preference' button
    Then the user "AUtestuser1@mailinator.com" should be unsubscribed from all email services
    And all email service check boxes on the preference page should be unchecked

    Examples: 
      | region | corporate  | frequency |
      | AU     | Employment | W         |

  # do not remove this tag. this tag is to clean all users subcriptions.
  @UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption
  @gold
  Scenario Outline: [848639] As user i want to cancel specified subscription
    Given ANZ user is logged in with following details
      | routing          | ANZ_IAC      |
      | mandatoryRouting | YES          |
    Given a user is viewing the email preference page
    Given a user creates subscription to the "<region>" "<corporate>" email service with "<frequency>"
    Given a user "AUtestuser1@mailinator.com" has already subscribed to the "<corporate>" "<frequency>" email service "<region>"
    When the user unchecks "<region>" the "<unsubscribe_frequency>" "<corporate>" email check box
    And the user clicks the 'Save preference' button
    Then the users "AUtestuser1@mailinator.com" saved subscription preferences should be saved in Adestra
    And the user should be unsubscribed from the "<region>" email service "<corporate>" "<unsubscribe_frequency>"

    Examples: 
      | region | corporate  | frequency | unsubscribe_frequency |
      | AU     | Employment | W         | W                     |

  @gold
  Scenario Outline: [848639] As user i want to see services and relevant checkboxes the page.
    Given ANZ user is logged in with following details
      | routing          | ANZ_IAC      |
      | mandatoryRouting | YES          |
    Given a user is viewing the email preference page
    When the user has opened the "<region>" Services tab
    Then the user should be presented with each "<region>" service
    And each "<region>" service should have the relevant check box options

    Examples: 
      | region |
      | AU     |

  Scenario: [848639] As user i want to see HTML and Text Only radiobuttons and 'Receive an email even if there are no new items' checkbox
    Given ANZ user is logged in
    Given a user is viewing the email preference page
    Then the user should be presented with two radio buttons as email options
    And the options should include HTML and Text Only
    And the user should be presented with a checkbox for 'Receive an email even if there are no new items'

  @gold
  Scenario: [848639] As a user i want to see Save and Cancel buttons on preference page
    Given ANZ user is logged in
    Given a user is viewing the email preference page
    Then the user should be presented with two buttons to save their preferences or cancel their changes
    And one button should be labelled 'Save'
    And one button should be labelled 'Cancel'

  # do not remove this tag. this tag is to clean all users subcriptions.
  @gold @UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption
  Scenario Outline: [848639] As user i want to see saved subscription
    Given ANZ user is logged in with following details
      | routing          | ANZ_IAC      |
      | mandatoryRouting | YES          |
    Given a user is viewing the email preference page
    Given a user creates subscription to the "<region>" "<corporate>" email service with "<frequency>"
    Given a user "AUtestuser1@mailinator.com" is has subscription to the "<region>" "<corporate>" email service with "<frequency>"
    And the user has opened the "<region>" Services tab
    Then the user should be presented with "<region>" "<corporate>" services row
    And the "<region>" "<corporate>" services row will display the appropriate "<frequency>" check boxes
    And the "<region>" "<corporate>" check boxes "<frequency>" should be selectable

    Examples: 
      | region | corporate  | frequency |
      | AU     | Employment | W         |

  # do not remove this tag. this tag is to clean all users subcriptions.
  @UsubscribeUserFromAllSubscriptionsAndRemoveUnsubscribeOption
  @gold
  Scenario: [848639] As a user i want to have ability to unsubcribe from update for out of plan subscription.
    Given a user "AUtestuser1@mailinator.com" has subscription for "PLAUEMPWKLY" out of plan email service
    Given ANZ user is logged in with following details
      | userName         | AUtestuser1                |
      | routing          | ANZ_ADESTRA_AU_EMPLOYMENT  |
      | mandatoryRouting | YES                        |
    Given a user is viewing the email preference page
    Then the user should be presented with their Employment email service row
    And the weekly check box should be ticked
    When the user unchecks the weekly check box
    Then the weekly check box becomes uncheckable
