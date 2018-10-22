package com.example.objects.pages;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.*;

public class WebPage {

    public String accountPage;
    public String accouuntEmail;

    public String url;
    public String title;

    public WebPage shouldHaveUrl(String url){
        assertEquals(url, getWebDriver().getCurrentUrl(), "Page Url should be:" + url);
        return this;
    }

    public WebPage shouldContainUrl(String url){
        assertTrue(getWebDriver().getCurrentUrl().contains(url), "Page should containt url" + url);
        return this;
    }

    public WebPage shouldHaveTitle(String title){
        assertEquals(title, getWebDriver().getTitle(), "Page Title should be: "+ title);
        return this;
    }

    public WebPage shouldContainTitle(String title){
        assertTrue(getWebDriver().getTitle().contains(title), "Page should containt title" + title);
        return this;
    }
}
