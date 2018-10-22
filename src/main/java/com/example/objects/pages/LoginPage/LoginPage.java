package com.example.objects.pages.LoginPage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.extensions.AccountExtension;
import com.example.objects.account.Account;
import com.example.objects.pages.AccountPage.AccountPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;


public class LoginPage {

    public static final String url = "/accounts.gepur.com/signin";


    public SelenideElement emailField = $("#identifierId"),
                            nextButton = $("#identifierNext"),
                            passwordField = $(byName("password")),
                            loginButton = $("#passwordNext"),
                            errorMessage = $("div[aria-live='assertive']");


    @Step
    public LoginPage open(){
        Selenide.open(url);
        return this;
    }

    @Step("Login with account: {user.name} / {user.email}")
    public AccountPage loginAs(Account account){
        emailField.shouldBe(visible).setValue(account.getEmail());
        nextButton.click();
        passwordField.shouldBe(visible).setValue(account.getPassword());
        loginButton.click();
        return new AccountPage();
    }


    @Step("Login with account: {user.name} {user.email}")
    public AccountPage loginWith(AccountExtension admin){
        emailField.shouldBe(visible).setValue(admin.email).pressEnter();
        passwordField.shouldBe(visible).setValue(admin.password).pressEnter();
        return new AccountPage();
    }


    @Step
    public LoginPage setEmail(String email){
        emailField.shouldBe(visible).val(email);
        return this;
    }

    @Step
    public LoginPage setPassword(String email){
        emailField.shouldBe(visible).val(email);
        return this;
    }



}
