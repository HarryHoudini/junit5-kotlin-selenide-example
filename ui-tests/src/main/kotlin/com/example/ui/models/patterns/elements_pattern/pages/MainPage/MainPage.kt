package com.example.ui.models.patterns.elements_pattern.pages.MainPage


import com.example.ui.extensions.kotlin_extensions.*
import com.example.ui.models.WebPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements.LoginModal
import io.qameta.allure.Step

class MainPage: WebPage() {

    override val url: String = "/"
    override val title: String = "\n        WeaveSocks\n    "


    val loginModal = LoginModal(s(".modal-content"))


    val accountButton = s("#howdy")
    val loginButton = s("#login")
    var registerButton = s("#register")

    @Step
    fun login(): LoginModal {
        loginButton.click()
        return LoginModal(loginModal.element)
    }

}