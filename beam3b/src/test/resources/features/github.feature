################################
# Author: Durgadas Shenoy
################################

Feature: Github website tests

  @Scenario1 @All
  Scenario Outline: Search repository by name, verify search results
    Given User launches the browser and enters desired URL
    When User enters "<repo>" name to be searched
    Then User verifies the search output and matches with expected "<count>"
    Examples:
      | repo      | count |
      | bootstrap | 15    |
      | bootstrap | 16    |
      |           |       |
      | dummy     |       |


  @Scenario2 @All
  Scenario Outline: Star, unstar repository
    Given User launches the browser and enters desired URL
    When User enters "<repo>" name to be searched
    And User clicks on the "<repo>" link
    And User signs in with "<userName>" and "<password>"
    And User clicks "<actionOnStar>" and verifies star count
    And User signs out

    Examples:
      | repo           | userName   | password     | actionOnStar |
      | bootstrap-sass | testbeam45 | Beamtest123$ | star         |
      | bootstrap-sass | testbeam45 | Beamtest123$ | unstar       |


  @Scenario3 @All
  Scenario Outline: Search files in the repository
    Given User launches the browser and enters desired URL
    When User enters "<repo>" name to be searched
    And User clicks on the "<repo>" link
    And User searches for the file "<fileName>" and its "<occurrence>" in the repo

    Examples:
      | repo      | fileName    | occurrence |
      | bootstrap | .babelrc.js | 1          |
      | icons     | icons/file  | 50         |
      | icons     | sample.data | 0          |
      | icons     | sample.data | 1          |