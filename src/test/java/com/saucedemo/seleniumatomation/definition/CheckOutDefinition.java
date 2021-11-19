package com.saucedemo.seleniumatomation.definition;

import com.saucedemo.seleniumatomation.page.CheckoutStepOnePage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckOutDefinition {
    @Autowired
    @Lazy
    CheckoutStepOnePage checkoutStepOnePage;
    @Then("checkout form shows error {string}")
    public void checkoutFormShowsError(String error) {
        assertThat(checkoutStepOnePage.error().getText(),equalTo(error));
    }
}
