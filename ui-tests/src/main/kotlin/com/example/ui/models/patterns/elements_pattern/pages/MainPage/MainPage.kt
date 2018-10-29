package com.example.ui.models.patterns.elements_pattern.pages.MainPage


import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.example.ui.extensions.kotlin_extensions.*
import com.example.ui.models.WebPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements.LoginModal
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements.RegistrationModal
import io.qameta.allure.Step

class MainPage: WebPage() {

    override val url: String = "/"
    override val title: String = "\n        WeaveSocks\n    "


    val loginModal = LoginModal(s("#login-modal"))
    val registerModal = RegistrationModal(s("#register-modal"))

    val accountButton = s("#howdy > a")
    val loginButton = s("#login")
    var registerButton = s("#register")
    var logoutButton = s("#logout > a")




    @Step
    fun login(): LoginModal {
        loginButton.shouldBeVisible().click()
        loginModal.getSelf().shouldBeVisible()
        return loginModal
    }

    @Step
    fun register(): RegistrationModal {
        registerButton.shouldBeVisible().click()
        registerModal.getSelf().shouldBe(visible)
        return registerModal
    }

    fun logout(): MainPage {
        logoutButton.shouldBe(visible).click()
        accountButton.shouldBe(disappear)
        loginButton.shouldBe(visible)
        return this
    }

}