package com.example.e2e;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.objects.pages.AccountPage.AccountPage;
import com.example.objects.pages.LoginPage.LoginPage;
import com.example.objects.pages.GmailPage.GMailPage;
import com.example.objects.pages.SearchPage.SearchPage;
import com.example.objects.account.Account;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Configuration.*;
import static java.lang.System.*;

public class TestBase {

    public static final String accountName = getenv("ACCOUNT_NAME");
    public static final String accountEmail = getenv("ACCOUNT_EMAIL");
    public static final String accountPassword = getenv("ACCOUNT_PASSWORD");

    public static LoginPage loginPage;
    public static SearchPage mainPage;
    public static GMailPage mailPage;
    public static SearchPage searchPage;
    public static AccountPage accountPage;
    public static Account account;


    @BeforeAll
    static void initAll() throws Exception {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());
        timeout = 4000;
        baseUrl = "https://";
        browser = "chrome";

        /*DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setBrowserName("chrome");
        dc.setVersion("67.0");
        dc.setPlatform(Platform.LINUX);
        dc.setCapability("enableVNC", true);
        dc.setCapability("enableVideo", true);
        dc.setCapability("screenResolution", "1960x1280x24");
        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        dc.setCapability("videoName", "my-cool-video.mp4");
        dc.setCapability("videoScreenSize", "1960x1280");
        dc.setJavascriptEnabled(true);
        RemoteWebDriver selenoidDriver = new RemoteWebDriver(new URL("http://moon.example.com:4444/wd/hub"), dc);
        setWebDriver(selenoidDriver);
        getWebDriver().manage().window().setSize(new Dimension(1920, 1080));*/

        account = new Account(accountName,accountEmail, accountPassword);
        loginPage = new LoginPage();
        mainPage = new SearchPage();
        mailPage = new GMailPage();
        accountPage = new AccountPage();
        searchPage = new SearchPage();

    }

    @BeforeEach
    void init(){

    }

    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allureSelenide");
    }



}
