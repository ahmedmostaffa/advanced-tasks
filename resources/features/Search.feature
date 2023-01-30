@search-feature
Feature: As a user I want to do searching for specific lesson
          so that I can access its worksheet.
  @search
  Scenario Outline: check the search functionality
    Given user open Nagwa home page
    And user choose "<language>" language
    When user search for "<specific>" lesson on search bar
    And  a list with all lessons that match the "<input>" will appear
    And user click on link 2 in the search results
    And user go to the worksheet section
    Then "<worksheet>" home page will open
    And all the questions are displayed

    Examples:
      | language | specific | input    | worksheet        |
      | English  | addition | Addition | Lesson Worksheet |
      | العربية  | جمع      | جمع      | ورقة تدريب الدرس |

