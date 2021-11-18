package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Page
public abstract class SharedComponents {
    @Value("${default.timeout:30}")
    private int timeout;
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait waitForElement;

    @FindBy(className = "shopping_cart_link")
    WebElement shopping_cart_link;
    @FindBy(className = "cart_item")
    List<WebElement> items;


    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver,this);
    }
    public void quit(){driver.quit();}

    public void clickRetry(WebElement webElement){
        val  wait= new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
    public void navigateToShoppingCart(){
        clickRetry(shopping_cart_link);
    }
    public List<ItemPageComponent> getItems() {
        return items.stream().map(item -> new ItemPageComponent(item)).collect(Collectors.toList());
    }

    public WebElement waitPageToDisplay(WebElement webElement){
        waitForElement.until((e)->webElement.isDisplayed());
       return webElement;
    }
    public void select(WebElement menu,String itemText){
        val select= new Select(menu);
        select.selectByVisibleText(itemText);
    }
    public void highlight(WebElement element){
        String javascript = "document.getElementById('email').style.border='2px solid red'";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(javascript);
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: GreenYellow; border: GreenYellow;color:black;');", element);
    }
    public abstract boolean pageIsLoaded();





}
