Feature: As a user, I want to add, delete and edit labels, so that I can label my emails

  Background: User is logged in
    Given ProtonMail website is opened
    And user logs in with his ProtonMail credentials
    And user navigates to Folders/labels


  Scenario: User creates a deletes a label
    When user creates a label with name: "ProtonMailRules"
    Then the label is created and present in the list of labels
    When user deletes the label
    Then the label is removed from list of labels

  Scenario: User can edit a label
    When user creates a label with name: "ProtonMailRules"
    Then the label is created and present in the list of labels
    When user edits the label with: "Changed" suffix
    Then the label is created and present in the list of labels
    When user deletes the label
    Then the label is removed from list of labels

