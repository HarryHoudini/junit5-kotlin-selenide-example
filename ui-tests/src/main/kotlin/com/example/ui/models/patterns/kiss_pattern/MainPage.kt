package com.example.ui.models.patterns.kiss_pattern

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.WebPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements.RegistrationModal
import com.example.ui.models.patterns.kiss_pattern.components.LoginModal

class MainPage: WebPage() {

    override val url: String = "/"
    override val title: String = "\n        WeaveSocks\n    "

    var loginModal = LoginModal()
    var usernameButton = s("#howdy")
    var loginButton = s("#login")
    var registerButton = s("#register")
    var logoutButton = s("#logout > a")
    var accountButton = s("#tabAccount > a")


    fun login(): LoginModal{
        loginButton.click()
        loginModal.self.shouldBe(visible)
        return loginModal
    }

    fun logout(): MainPage{
        logoutButton.shouldBe(visible).click()
        accountButton.shouldBe(disappear)
        loginButton.shouldBe(visible)
        return this
    }






}