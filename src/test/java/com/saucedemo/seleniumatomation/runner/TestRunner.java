package com.saucedemo.seleniumatomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features="src/test/java/com/saucedemo/seleniumatomation/feature",glue={"com.saucedemo.seleniumatomation.definition","com.saucedemo.seleniumatomation.config"},
        monochrome = true,
        plugin={"pretty",
                "json:target/output/HtmlReports.json","html:target/output/HtmlReports.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
