package com.saucedemo.seleniumatomation.driver.beans.config;

import com.saucedemo.seleniumatomation.annotation.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@Browser
@Profile("remote")
 abstract class BrowserCommonShare {


    @Value("${default.timeout:30}")
    private int timeout;

    @Bean
    public WebDriverWait webDriverWait(WebDriver driver){
        return  new WebDriverWait(driver,timeout);
    }
}
