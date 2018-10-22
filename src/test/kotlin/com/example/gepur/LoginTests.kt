package com.example.gepur

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Configuration.*
import com.example.flow_tests.api.main
import com.exmple.gepur.extensions.Extensions
import com.exmple.gepur.models.pages.LoginPage
import com.exmple.gepur.models.pages.MainPage
import com.exmple.gepur.models.stuff.User
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class LoginTests: Extensions() {


    var mainPage: MainPage = MainPage()
    var loginPage: LoginPage = LoginPage()

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
            println("Runs once before all test cases.")
            baseUrl = "http://gepur.com"
            browser = "chrome"
            startMaximized = true



        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            println("Runs once after all test cases.")
        }
    }



    @Test
    fun positiveLogin(){
        val user = User(firstName = "Savva", lastName = "Genchevskiy",
                email = "genchevskiy.gepur@gmail.com",
                password = "S.genchevskiy19021992")
        loginPage.open().loginAs(user)
        mainPage.accountButton.shouldBeVisible()
        mainPage.accountButton.should.have.text(user.lastName)


        mainPage.shouldBeOpened()
                .shouldHaveTitle(mainPage.title)
                .shouldHaveUrl(mainPage.url)

        mainPage.header.catalogButton.hover()
        mainPage.header.catalogList
                .filterBy(visible)
                .shouldHaveSize(27)
                .filter { it.text == "Новинки" }.last().click()

    }


}