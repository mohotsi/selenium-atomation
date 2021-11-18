package com.saucedemo.seleniumatomation.definition;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.val;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

public class CucumberHooks {


    @Lazy
    @Autowired
    ApplicationContext applicationContext;

    @Lazy
    @Autowired
    WebDriver driver;
    @Value("${application.url}")
    private String url;





    @AfterStep
    public void afterTest(Scenario scenario) {

        if(scenario.isFailed())
            scenario.attach(applicationContext.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
    }

    @After()
    public void afterscenari() throws InterruptedException {
        val driver=this.applicationContext.getBean(WebDriver.class);
       // driver.manage().deleteAllCookies();
       // driver.quit();
    }
}
