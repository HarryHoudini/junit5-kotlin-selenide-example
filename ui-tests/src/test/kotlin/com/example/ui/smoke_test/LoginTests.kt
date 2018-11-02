package com.example.ui.smoke_test

import com.codeborne.selenide.CollectionCondition.*
import com.codeborne.selenide.Condition.*
import org.junit.jupiter.api.Test
import com.example.api.models.User
import com.example.ui.BaseTest
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.refresh
import com.example.ui.extensions.kotlin_extensions.shouldBeOpened
import com.example.ui.extensions.kotlin_extensions.shouldHaveUrl
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import com.example.ui.models.patterns.elements_pattern.shouldBe
import com.example.ui.models.patterns.elements_pattern.shouldHave
import com.example.ui.models.patterns.elements_pattern.shouldHaveSize
import io.kotlintest.should
import org.junit.jupiter.api.BeforeEach


class LoginTests: BaseTest(){

    @BeforeEach
    fun setUp(){

    }


    @Test
    fun `login test`(){
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
        CatalogPage().open().refresh()
                .products.shouldHave(sizeGreaterThan(1))
                .filterBy(visible).shouldHaveSize(6)
    }




}