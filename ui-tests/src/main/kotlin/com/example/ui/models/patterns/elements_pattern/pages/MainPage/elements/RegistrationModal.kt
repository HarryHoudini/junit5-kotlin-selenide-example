package com.example.ui.models.patterns.elements_pattern.pages.MainPage.elements

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.SelenideElement
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.patterns.elements_pattern.ElementContainer
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import io.qameta.allure.Step

class RegistrationModal(override val element: SelenideElement) : ElementContainer(){

    val loginModal = this
    var title = element.s("h4.modal-title")
    var closeButton = element.s("*[data-dismiss='modal']")
    var usernameField = element.s("#register-username-modal")
    var firstNameField = element.s("#register-first-modal")
    var lastNameField = element.s("#register-last-modal")
    var emailField = element.s("#register-email-modal")
    var passwordField = element.s("#register-password-modal")
    var registerButton = element.s("button[onclick='return register()']")
    var messageField = element.s("#registration-message > div")


    @Step
    fun registerWith(user: User): MainPage {
        usernameField.value = user.username
        firstNameField.value = user.firstName
        lastNameField.value = user.lastName
        emailField.value = user.email
        passwordField.value = user.password
        registerButton.click()
        return MainPage()
    }

    @Step
    fun dismiss(): MainPage {
        closeButton.click()
        return MainPage()
    }



}