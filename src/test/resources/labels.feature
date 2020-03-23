Feature: As a user, I want to add, delete and edit labels, so that I can label my emails

  Background: User is logged in
    Given ProtonMail website is opened
    And user logs in with credentials as username: "vladanvac" and password "Proton123"


  Scenario: User creates a deletes a label
    When user navigates to Folders/labels
    And user creates a label with name: "ProtonMailRules"
    Then the label is created and present in the list of labels

    When user deletes the label
    Then the label is removed from list of labels


