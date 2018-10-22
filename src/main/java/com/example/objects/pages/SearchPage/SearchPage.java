package com.example.objects.pages.SearchPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.objects.pages.LoginPage.LoginPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;

public class SearchPage {

   public static final String url = "/www.gepur.com";


   public SelenideElement searchField = $(byName("q"));
   public ElementsCollection searchResults = $$(".srg .g");

   @Step
   public SearchPage open(){
        Selenide.open(url);
        return this;
   }

   @Step("Search for: \"{text}\"")
   public SearchPage search(String text){
        searchField.shouldBe(visible).val(text).pressEnter();
        return this;
   }




}
