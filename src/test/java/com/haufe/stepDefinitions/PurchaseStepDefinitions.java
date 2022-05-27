package com.haufe.stepDefinitions;

import com.haufe.uiPages.Base;
import com.haufe.uiPages.Purchase_Home;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseStepDefinitions {
    Purchase_Home purchase_home = new Purchase_Home();


    @When("customer selects different items with different quantities")
    public void customerSelectsDifferentItemsWithDifferentQuantities() {


        // Multiple items with more than one quantity
        String[] items_increased = {"Cucumber", "Brocolli", "Beetroot"};
        purchase_home.addMore(items_increased, 3);


//        Base.waitFor(5);
    }


    @When("the customer clicks on the cart")
    public void CustomerClicksOnTheCart() {

        purchase_home.clickCart();

    }

    @And("customer  clicks checkout button")
    public void customerClicksCheckoutButton() {

        purchase_home.proceedToCheckout();
    }

    @And("customer clicks  place order button")
    public void customerClicksPlaceOrderButton() {

        purchase_home.placeOrderClick();
    }


    @And("customer enters invalid prome code")
    public void customerEntersInvalidPromeCode() {

        purchase_home.promecodeApply("3ghe353");

    }


    @And("customer selects country for order")
    public void customerSelectsCountryForOrder() {

        purchase_home.selectCountry();
    }

    @And("the customer  can delete some items for the cart")
    public void theCustomerCanDeleteSomeItemsForTheCart() {

        String[] itemsdeleted = {"Cucumber"};
        purchase_home.deleteItems2(itemsdeleted);
        Base.waitFor(2);


    }

    @And("customer clicks Agree to Terms & Conditions")
    public void customerClicksAgreeToTermsConditions() {

        purchase_home.clickAgreeCheckbox();
    }


    @And("customer click Proceed button")
    public void customerClickProceedButton() {

        purchase_home.clickAgreedButton();
    }

    @Then("customer should receive order confirmation message and be directed to the homepage.")
    public void customerShouldReceiveOrderConfirmationMessageAndBeDirectedToTheHomepage() {

        purchase_home.confirmation_message();

    }


    @Then("deleted item should be removed from the cart")
    public void deletedItemShouldBeRemovedFromTheCart() {

        purchase_home.AssertDeletedItems("Cucumber");


    }

    @Then("Prices on the page and cart should be the same")
    public void pricesOnThePageAndCartShouldBeTheSame() {

        purchase_home.assertPrices();

    }


}
