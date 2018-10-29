package com.example.ui.smoke_test

import com.codeborne.selenide.Condition.*
import com.example.api.models.User
import com.example.ui.BaseTest
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.shouldBeOpened
import com.example.ui.extensions.kotlin_extensions.shouldBeVisible
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
                .registerModal.messageField.shouldBeVisible()
                .shouldHave(text("Registration and login successful."))
                .shouldHave(attribute("class", "alert alert-success"))
        mainPage.accountButton.shouldBe(visible)
                .shouldHave(text("Logged in as ${randomUser.firstName} ${randomUser.lastName}"))
    }





}