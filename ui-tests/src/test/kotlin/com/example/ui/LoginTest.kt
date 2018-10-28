package com.example.ui

import com.codeborne.selenide.CollectionCondition.*
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration
import org.junit.jupiter.api.Test
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.shouldBeOpened
import com.example.ui.extensions.kotlin_extensions.shouldHaveUrl
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import com.example.ui.models.patterns.elements_pattern.shouldBe
import com.example.ui.models.patterns.elements_pattern.shouldHave
import com.example.ui.models.patterns.elements_pattern.shouldHaveSize
import org.junit.jupiter.api.BeforeEach


class LoginTest: BaseTest(){


    @BeforeEach
    fun setUp(){
        Configuration.baseUrl = "http://35.232.243.253"
    }


    @Test
    fun `login test`(){
        val user = User(firstName = "Savva", lastName = "Genchevskiy",
                username = "savva.genchevskiy",
                email = "savva.genchevskiy@gmail.com",
                password =  "s.g19021992", id = "")
        val mainPage = MainPage()
                .open()
                .login()
                .loginModal.shouldBe(visible)
                .loginWith(user)
        mainPage.loginModal.successMessage.shouldBe(visible)
        mainPage.accountButton.shouldBe(visible)
        mainPage.shouldBeOpened().shouldHaveUrl(mainPage.url)
    }


    @Test
    fun `catalog test`(){
        CatalogPage().open().products.shouldHave(sizeGreaterThan(1))
                .filterBy(visible).shouldHaveSize(6)
    }


}