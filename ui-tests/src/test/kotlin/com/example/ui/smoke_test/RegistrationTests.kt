package com.example.ui.smoke_test

import com.codeborne.selenide.Condition.*
import com.example.api.models.User
import com.example.ui.BaseTest
import com.example.ui.extensions.kotlin_extensions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RegistrationTests: BaseTest() {

    @RegisterExtension
    val randomUser = User(username = faker.name().username(),
                        firstName = faker.name().firstName(),
                        lastName = faker.name().lastName(),
                        email = faker.internet().emailAddress(),
                        password = faker.internet().password(3, 6, true, true),
                        id = "")

    @BeforeEach
    fun setUpTest(){
        mainPage.open().shouldBeOpened()
    }


    @Test
    fun `register user with correct creds`(){
        mainPage.register()
                .registerWith(randomUser)
        mainPage.registerModal.messageField.should.be.visible
        mainPage.registerModal.messageField.should.have.text("Registration and login successful.")
                .shouldHaveClass("alert alert-success")
        mainPage.accountButton.shouldBeVisible()
                .shouldHave(text("Logged in as ${randomUser.firstName} ${randomUser.lastName}"))
        mainPage.shouldBeOpened().shouldHaveTitle(mainPage.title)
    }





}