package com.example.junit

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration.*
import com.codeborne.selenide.WebDriverRunner
import com.exmple.gepur.models.pages.LoginPage
import com.exmple.gepur.models.stuff.User
import com.natpryce.hamkrest.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import com.natpryce.hamkrest.assertion.*

class LoginTests: TestBase() {

    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy", email = "genchevskiy.gepur@gmail.com", password = "S.genchevskiy19021992")


    @Test
    fun `Login test`(){
        val mainPage = LoginPage().open().loginAs(user)
        mainPage.accountButton.should.be.visible
        mainPage.accountButton.highlight().should.have.text(user.username)
        assert.that(
                WebDriverRunner.getWebDriver().currentUrl, equalTo("${baseUrl}${mainPage.url}")
                and startsWith(baseUrl)
                and endsWith(mainPage.url)
                and containsSubstring(mainPage.url)
                and !containsSubstring("test")
        )

        loginPage.open().loginWith(user)
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