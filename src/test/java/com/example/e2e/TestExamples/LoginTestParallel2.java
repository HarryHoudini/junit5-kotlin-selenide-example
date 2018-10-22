package com.example.e2e.TestExamples;

import com.example.e2e.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Execution(ExecutionMode.CONCURRENT)
public class LoginTestParallel2 extends TestBase {


    @BeforeEach
    void setUpTest(){
        loginPage.open();
    }

    @AfterEach
    void tearDownTest(){
        if (accountPage.accountButton.is(visible)){
            open("https://accounts.gepur.com/Logout");
            getWebDriver().manage().deleteAllCookies(); }
    }


    @Test
    @Tag("parallel")
    @ResourceLock(value = "ACCOUNT_PASSWORD", mode = ResourceAccessMode.READ)
    void positiveLogin(){
        loginPage.loginAs(account);
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.forEach((el)-> el.shouldBe(visible).shouldHave(attribute("data-track-as")));
        accountPage.shouldHaveTitle(accountPage.title).shouldContainUrl(accountPage.url);
    }

    @Test
    @Tag("parallel")
    @ResourceLock(value = "ACCOUNT_PASSWORD", mode = ResourceAccessMode.READ)
    void positiveLoginWithGroupAssertions(){
        loginPage.loginAs(account).navigationList.first().shouldBe(visible);
        assertThat(accountPage.accountButton.isDisplayed(), is(true));
        assertAll( "Check Account page is opened.",
                () -> accountPage.navigationList.forEach((element)-> assertTrue(element.isDisplayed())),
                () -> assertTrue(accountPage.accountButton.isDisplayed()),
                () -> assertTrue(getWebDriver().getCurrentUrl().contains(accountPage.url)),
                () -> assertEquals(accountPage.title ,getWebDriver().getTitle())
        );
    }

}
