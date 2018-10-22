package com.example.e2e.SearchTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.e2e.TestBase;
import com.example.extensions.AllureReportExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AllureReportExtension.class,
        ScreenShooterExtension.class,
        TextReportExtension.class
})
public class SearchTests extends TestBase {

    @Test
    @ResourceLock(value = "ACCOUNT_PASSWORD", mode = ResourceAccessMode.READ)
    void searchForSelenium(){
        searchPage.open().search("Selenium Webdriver");
        searchPage.searchResults.shouldHave(CollectionCondition.sizeGreaterThan(5));
        searchPage.searchResults.forEach((el)-> el.shouldBe(visible));
        searchPage.searchResults.texts().forEach((text)-> assertTrue(text.contains("Selenium")));
    }



}
