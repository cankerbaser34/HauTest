@purchasing
Feature: Purchase some items from the web page.

  Background:
    When customer selects different items with different quantities
    And the customer clicks on the cart

  @endtoend
  Scenario: Customer can purchase different items with different quantities
    And customer  clicks checkout button
    Then Prices on the page and cart should be the same
    And customer clicks  place order button
    And customer selects country for order
    And customer clicks Agree to Terms & Conditions
    And customer click Proceed button
    Then customer should receive order confirmation message and be directed to the homepage.

  @deleteitem
  Scenario:Customer can edit shopping list from the cart by deleting some items
    And the customer  can delete some items for the cart
    Then deleted item should be removed from the cart

  @invalidprome
  Scenario: Customer can continue with my purchase even after applying an invalid promo code
    And customer  clicks checkout button
    And customer enters invalid prome code
    And customer clicks  place order button
    And customer selects country for order
    And customer clicks Agree to Terms & Conditions
    And customer click Proceed button
    Then customer should receive order confirmation message and be directed to the homepage.










