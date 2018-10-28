package com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements

import com.codeborne.selenide.SelenideElement
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.patterns.elements_pattern.ElementContainer
import io.qameta.allure.Step
import com.codeborne.selenide.Selectors.*
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage

class LoginModal(override val element: SelenideElement) : ElementContainer() {

    val loginModal = this
    var title = element.s("h4.modal-title")
    var closeButton = element.s("*[data-dismiss='modal']")
    var usernameField = element.s("#username-modal")
    var passwordField = element.s("#password-modal")
    var loginButton = element.s("button[onclick='return login()']")
    var successMessage = element.s(byText("Login successful."))


    @Step
    fun loginWith(user: User): MainPage {
        usernameField.value = user.username
        passwordField.value = user.password
        loginButton.click()
        return MainPage()
    }

    @Step
    fun dismiss(): MainPage {
        closeButton.click()
        return MainPage()
    }


}