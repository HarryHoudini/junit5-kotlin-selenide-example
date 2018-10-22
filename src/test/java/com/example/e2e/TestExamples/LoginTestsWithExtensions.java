package com.example.e2e.TestExamples;

import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.example.e2e.TestBase;
import com.example.annotations.tags.Fast;
import com.example.extensions.AccountExtension;
import com.example.extensions.AllureReportExtension;
import com.example.extensions.RandomParametersExtension;
import com.example.listeners.TestLifecycleLogger;
import com.example.objects.pages.AccountPage.AccountPage;
import com.example.objects.pages.LoginPage.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.example.extensions.RandomParametersExtension.*;
import static org.junit.jupiter.api.Assertions.*;



// The default is PER_METHOD
// Per_class - is recomended because it's yeasy to use @BeforeAll and @AfterAll in Kotlin and other tests with Interfaces and @Nested test
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //creates a new instance of each test class before executing each test method
@ExtendWith({ScreenShooterExtension.class, TextReportExtension.class, SoftAssertsExtension.class})
@ExtendWith(BrowserStrategyExtension.class)
@ExtendWith(AllureReportExtension.class)
public class LoginTestsWithExtensions extends TestBase implements TestLifecycleLogger {


    // Extensions Registration in this case is used when:
    // 1. We have Some server object, and this server should be killed and started before and after all tests, and this serverObject can be used in all tests.
    @RegisterExtension
    static AccountExtension admin = new AccountExtension.AccountBuilder(accountEmail).withName(accountName)
            .withPassword(accountPassword).build();


    @BeforeEach
    void setUpTest(){
    }


    @AfterEach
    void tearDownTest(){
        if (new AccountPage().accountButton.is(visible)){
            open("https://accounts.gepur.com/Logout");
            getWebDriver().manage().deleteAllCookies();
        }
    }


    @Test
    @DisplayName("Login to account using Next Buttons. ╯°□°）╯")
    @Fast
    void loginWitRegisteredAccountExension(){
        AccountPage accountPage = new LoginPage().open().loginWith(admin);
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.shouldHave(size(4));
        accountPage.navigationList.forEach((element)-> element.shouldBe(visible));
    }


    @Test
    @DisplayName("Login to account using Next Buttons. ╯°□°）╯")
    @Fast
    @DisabledOnOs(OS.SOLARIS)
    @EnabledOnOs({OS.WINDOWS, OS.LINUX, OS.MAC})
    void loginWithNextButtons(){
        AccountPage accountPage = new LoginPage().open().loginAs(account);
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.shouldHave(size(4));
        accountPage.navigationList.forEach((element)-> element.shouldBe(visible));
        assertAll( "Url and title should be correct",
               () -> assertTrue(getWebDriver().getCurrentUrl().contains(accountPage.url)),
               () -> assertEquals(accountPage.title ,getWebDriver().getTitle())
       );
    }

    @Test
    @DisplayName("Login to account using Enters. \uD83D\uDE31")
    @Fast
    @EnabledOnOs({OS.WINDOWS, OS.LINUX, OS.MAC})
    @EnabledIfEnvironmentVariable(named = "ACCOUNT_NAME", matches = "Savva Genchevskiy")
    void loginWithEnters(){
        loginPage.open().emailField.val(admin.email).pressEnter();
        loginPage.passwordField.val(admin.password).pressEnter();
        accountPage.accountButton.should(exist);
        assertAll( "Check Account page is opened.",
                () -> accountPage.navigationList.forEach((element)-> assertTrue(element.isDisplayed())),
                () -> assertTrue(accountPage.accountButton.isDisplayed()),
                () -> assertTrue(getWebDriver().getCurrentUrl().contains(accountPage.url)),
                () -> assertEquals(accountPage.title ,getWebDriver().getTitle())
        );
    }

    @Test
    @Fast
    @DisplayName("Login with random username")
    @ExtendWith(RandomParametersExtension.class)
    void invalidEmailLogin(@Random String email){
        loginPage.open()
                .setEmail(email)
                .nextButton.click();
        loginPage.errorMessage.shouldBe(visible);
        loginPage.errorMessage.lastChild().shouldHave(text("Couldn't find your Google Account"));
    }




    /** Disabled tests */

    @Test
    @Fast
    @DisplayName("Disabled Anytime test.")
    @Disabled
    @DisabledOnOs(OS.LINUX)
    void loginTest1(){
        loginPage.open().emailField.val(account.email).submit();
        loginPage.passwordField.val(account.password).submit();
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.forEach((element)-> element.shouldBe(visible));
    }

    @Test
    @Fast
    @DisplayName("Disabled with current Account test.")
    @DisabledIfEnvironmentVariable(named = "ACCOUNT_NAME", matches = "Savva Genchevskiy")
    void loginTest2(){
        loginPage.open().emailField.val(account.email).submit();
        loginPage.passwordField.val(account.password).submit();
        accountPage.accountButton.shouldBe(visible);
        accountPage.navigationList.forEach((element)-> element.shouldBe(visible));
    }








}
