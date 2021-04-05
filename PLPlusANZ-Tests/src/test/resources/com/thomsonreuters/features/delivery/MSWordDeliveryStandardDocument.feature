@robot
Feature: [830655] Download document in Microsoft Word
  As a PLAU User I want to download a standard document or clause in Microsoft Word in the PL house style
  So that I can work with my document off line


  Background:
    Given ANZ user is logged in

  @smoke @gold
  Scenario Outline: [830655] Download standard document - MS Word format-<id>
    Given the user opens "<plc ref>" url on PL AU website
    When the user clicks on "Open in Word" delivery option for the document
    Then the document is downloaded with name "<name>" and extension ".doc"
    Examples:
      | plc ref     | name                                                    | id |
      | /w-001-1772 | Lettertomedicalpractitionerseekingreportonfitnesstowork | 1  |
      | /w-001-2798 | Deedofreleaseforsettlingadiscriminationclaim            | 2  |
      | /w-001-1409 | Governinglawandjurisdiction                             | 3  |