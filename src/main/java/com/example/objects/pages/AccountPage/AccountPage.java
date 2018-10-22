package com.example.objects.pages.AccountPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.objects.pages.WebPage;

import static com.codeborne.selenide.Selenide.*;

public class AccountPage extends WebPage {

    public AccountPage(){
        this.url = "myaccount.gepur.com";
        this.title = "Аккаунт Google";
    }

    public SelenideElement accountButton = $("a[aria-label*='Savva Genchevskiy']");
    public ElementsCollection navigationList = $$("div[role='navigation'] a[data-track-as]");

}
