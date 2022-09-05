
Feature: test quick start
  @debug
  Scenario:  web side load2
    Given application URL isready
    When user enters the URL
    Then application page loads