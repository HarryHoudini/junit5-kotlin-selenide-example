package com.example.ui.models.patterns.kiss_pattern.components

import com.codeborne.selenide.Selectors
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.patterns.kiss_pattern.MainPage
import io.qameta.allure.Step


class LoginModal {

    val self = s(".modal-content")
    var loginModalTitle = s("h4.modal-title")
    var closeButton = s("*[data-dismiss='modal']")
    var usernameField = s("#username-modal")
    var passwordField = s("#password-modal")
    var loginButton = s("button[onclick='return login()']")
    var successMessage = s(Selectors.byText("Login successful."))
    var errorMessage = s("#login-message > div")


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