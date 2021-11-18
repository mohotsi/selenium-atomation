package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

@Page
public class LoginPage extends SharedComponents {
    @Value("${default.timeout:30}")
    private int timeout;
    @FindBy(id="user-name")
    private WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="login-button")
    private WebElement loginButton;

    @FindBy(className = "cart_quantity_label")
    WebElement cart_quantity_label;
    @FindBy(className = "cart_desc_label")
    WebElement description;

    public String getQuantityLabel(){
      return   cart_quantity_label.getText();
    }
    public String getDescriptionLabel(){
        return   cart_quantity_label.getText();
    }

    public void loginWith(String username){
        waitPageToDisplay(this.username).sendKeys(username);
        password.sendKeys("secret_sauce");
        clickRetry(loginButton);

    }


    @Override
    public boolean pageIsLoaded() {
        return true;
    }
}
