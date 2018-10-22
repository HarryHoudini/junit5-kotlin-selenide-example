package com.example.e2e.TestExamples;


import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.e2e.TestBase;
import com.example.annotations.tags.TestOnLinux;
import com.example.objects.account.Account;
import com.example.objects.pages.AccountPage.AccountPage;
import com.example.objects.pages.LoginPage.LoginPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.time.Duration.*;
//Simple Assertions
import static org.junit.jupiter.api.Assertions.*;
// Hamcrest assertions are injected.
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
// Assumptions are got from Junit4
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Feature("Login Functionality")
@DisplayName("Login Tests")
@ExtendWith({ScreenShooterExtension.class, TextReportExtension.class})
public class LoginTestsWithAssertions extends TestBase {


    @BeforeEach
    void setUpTest(){
        loginPage.open();
    }

    @AfterEach
    void tearDownTest(){
        if (accountPage.accountButton.is(visible)){
            open("https://accounts.gepur.com/Logout");
            getWebDriver().manage().deleteAllCookies();
        }

    }


    @Test
    @TestOnLinux
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledIf("'genchevskiy.test@gmail.com' == systemEnvironment.get('ACCOUNT_EMAIL')")
    void positiveLoginWithSimpleAssertions(){
        loginPage.loginAs(account);
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.forEach((el)-> el.shouldBe(visible).shouldHave(attribute("data-track-as")));
        accountPage.shouldHaveTitle(accountPage.title).shouldContainUrl(accountPage.url);

    }

    @Test
    @EnabledIf(value = {
            "load('nashorn:mozilla_compat.js')",
            "importPackage(java.time)",
            "",
            "var today = LocalDate.now()",
            "var tomorrow = today.plusDays(1)",
            "tomorrow.isAfter(today)"
    },
            engine = "nashorn",
            reason = "Self-fulfilling: {result}")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10})
    void positiveLoginWithGroupAssertions(){
        loginPage.loginAs(account).navigationList.first().shouldBe(visible);
        assertThat(accountPage.accountButton.isDisplayed(), is(true));
        assertThat(getWebDriver().getTitle(), equalTo(accountPage.title));
        assertThat(getWebDriver().getCurrentUrl(), containsString(accountPage.url));
        assertAll( "Check Account page is opened.",
                () -> accountPage.navigationList.forEach((element)-> assertTrue(element.isDisplayed())),
                () -> assertTrue(accountPage.accountButton.isDisplayed()),
                () -> assertTrue(getWebDriver().getCurrentUrl().contains(accountPage.url)),
                () -> assertEquals(accountPage.title ,getWebDriver().getTitle())
        );
    }

    @Test
    @EnabledIfSystemProperty(named = "ACCOUNT_NAME", matches = "Savva Genchevskiy")
    @EnabledIfEnvironmentVariable(named = "ACCOUNT_EMAIL", matches = "genchevskiy.test@gmail.com")
    @DisabledIfSystemProperty(named = "ACCOUNT_EMAIL", matches = "savva.genchevskiy@gmail.com")
    void positiveLoginWithStructuredGroupAssertions(){
        loginPage.loginAs(account);
        accountPage.accountButton.shouldBe(visible);
        assertThat(accountPage.navigationList.size(), equalTo(4));
        assertAll( "Check Several Conditions.",
                () -> {
                    assertAll("Check page elements",
                            () -> assertTrue(accountPage.accountButton.isDisplayed()),
                            () -> accountPage.navigationList.forEach((element)-> assertTrue(element.isDisplayed()))

                    );
                },
                () -> {
                    String actualUrl = getWebDriver().getCurrentUrl();
                    String actualTitle = getWebDriver().getTitle();
                    assertNotNull(actualUrl);
                    assertAll("Check Page url and title",
                            () -> assertTrue(actualUrl.contains(accountPage.url)),
                            () -> assertEquals(accountPage.title , actualTitle)
                    );
                }
        );
    }


    @Test
    @DisplayName("Test with timeout assertions")
    void positiveLoginWithTimeoutAssertion(){
        LoginPage loginPage = assertTimeout(ofSeconds(1), LoginPage::new);
        AccountPage page = assertTimeout(ofSeconds(1), AccountPage::new);
        assertTimeout(ofSeconds(10), ()-> {
            loginPage.open().loginAs(account);
            page.accountButton.shouldBe(visible);
            page.navigationList.forEach((el)-> el.shouldBe(visible).shouldHave(attribute("data-track-as")));
        });
    }


    @Test
    @DisplayName("Test that chaks exception")
    void loginWithException(){
        Throwable exception = assertThrows(ElementNotFound.class, () -> {
            loginPage.loginAs(new Account(accountName, accountEmail, "1234"));
            accountPage.accountButton.shouldBe(visible);
            //throw new IllegalArgumentException("a message");
        });
        assertEquals("Element not found {a[aria-label*='Аккаунт Google: Savva Genchevskiy']}\n" +
                "Expected: visible", exception.getMessage());
    }


    @Test
    @DisabledIf("2 * 3 == 6")
    @EnabledIf("/Savva Genchevskiy/.test(systemProperty.get('ACCOUNT_NAME'))")
    void positiveLoginWithAssumptions(){
        loginPage.loginAs(account);
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.forEach((el)-> el.shouldBe(visible).shouldHave(attribute("data-track-as")));
        assumeTrue("Savva Genchevskiy".equals(System.getenv("ACCOUNT_NAME")), () -> "Aborting test: not on developer workstation");
        assumingThat(accountPage.accountButton.isDisplayed(),
                () -> {
                    // perform these assertions only on Account Page
                    assertEquals(accountPage.navigationList.size(), 4);
                });

    }






}
