package com.saucedemo.seleniumatomation.definition;

import com.saucedemo.seleniumatomation.page.CheckoutStepOnePage;
import com.saucedemo.seleniumatomation.page.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserDefinition {
    @Autowired
    @Lazy
    LoginPage loginPage;


    @When("login with username {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        loginPage.loginWith(username, password);
    }
}
