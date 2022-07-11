package com.saucedemo.seleniumatomation.driver.beans.config;


import com.saucedemo.seleniumatomation.annotation.Browser;
import com.saucedemo.seleniumatomation.annotation.ThreadBrowsing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import java.net.URL;

@Browser
@Profile("remote")
public class RemoteWebDriverBean extends BrowserCommonShare {

    @Value("${selenium.grid.url}")
    private URL url;

    @ConditionalOnProperty(name = "browser",havingValue = "firefox")
    @ThreadBrowsing
    public WebDriver fireDriver(){
        return new RemoteWebDriver(url, DesiredCapabilities.firefox());
    }

    @ConditionalOnProperty(name = "browser",havingValue = "edge")
    @ThreadBrowsing
    public WebDriver edgeDriver(){
        return new RemoteWebDriver(url, DesiredCapabilities.edge());
    }

    @ConditionalOnProperty(name = "browser",havingValue = "opera")
    @ThreadBrowsing
    public WebDriver operaDriver(){
        return new RemoteWebDriver(url, DesiredCapabilities.opera());
    }

    @ThreadBrowsing
    @ConditionalOnMissingBean
    public WebDriver chromeDriver(){
//        DesiredCapabilities cap= new DesiredCapabilities();
//        cap.setBrowserName(BrowserType.CHROME);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized"); // open Browser in maximized mode
//        options.addArguments("disable-infobars"); // disabling infobars
//        options.addArguments("--disable-extensions"); // disabling extensions
//        options.addArguments("--disable-gpu"); // applicable to windows os only
//        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        options.addArguments("--no-sandbox"); // Bypass OS security model
//        options.addArguments("--privileged");
//        options.addArguments("--headless");
////        options.addArguments("--no-sandbox");
////        options.addArguments("--disable-gpu");
////        options.addArguments("--remote-debugin-port=9222");
////        options.addArguments("--screen-size=1200x800");
//        cap.setCapability(ChromeOptions.CAPABILITY, options);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME,BrowserType.CHROME);
        caps.setCapability("zal:name","Thapelo");
        caps.setCapability("zal.build","mohotsi100");
        caps.setCapability("zal:tz","Africa/Blantyre");
        caps.setCapability("zal:screenResolution","1280x720");
        caps.setCapability("zal:idleTimeout",10000);
        caps.setCapability("zal:recordVideo",true);
        return new RemoteWebDriver(url, caps);
    }

}

