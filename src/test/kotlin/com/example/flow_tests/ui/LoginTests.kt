package com.example.flow_tests.ui


import com.codeborne.selenide.Configuration.baseUrl
import com.codeborne.selenide.WebDriverRunner.*
import com.example.flow_tests.api.main
import com.exmple.electric_flow.pages.LoginPage
import com.exmple.electric_flow.User
import com.natpryce.hamkrest.*
import org.junit.jupiter.api.Test
import com.natpryce.hamkrest.assertion.assert


class LoginTests: TestBase() {


    @Test
    fun `Login test`(){
        val admin = User(username = "admin", pasword = "changeme")
        val mainPage = LoginPage().open().loginAs(admin)
        mainPage.usernameButton.should.be.visible
        mainPage.usernameButton.highlight().should.have.text(admin.username)
        assert.that(
                getWebDriver().currentUrl, equalTo("${baseUrl}${mainPage.url}")
                and startsWith(baseUrl)
                and endsWith(mainPage.url)
                and containsSubstring(mainPage.url)
                and !containsSubstring("test")
        )
    }



}

