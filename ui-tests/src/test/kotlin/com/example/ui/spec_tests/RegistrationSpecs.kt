package com.example.ui.spec_tests

import com.codeborne.selenide.Condition
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.shouldBeVisible
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import com.github.javafaker.Faker
import io.kotlintest.TestType
import io.kotlintest.specs.AbstractBehaviorSpec
import io.kotlintest.specs.BehaviorSpec
import org.junit.jupiter.api.extension.RegisterExtension
import java.util.*


class RegistrationSpecs: BehaviorSpec({

    val faker: Faker = Faker(Locale("en-US"))
    val randomUser = User(username = faker.name().username(),
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(3, 6, true, true),
            id = "")

    given("should register user"){
        given("open registration modal"){
            val regModal = MainPage().run {
                open().register()
            }
            `when`("register user"){
                regModal.registerWith(randomUser)

                then(""){
                    MainPage().registerModal
                            .messageField.shouldBeVisible()
                            .shouldHave(Condition.text("Registration and login successful."))
                            .shouldHave(Condition.attribute("class", "alert alert-success"))
                    MainPage().accountButton.shouldBe(Condition.visible)
                            .shouldHave(Condition.text("Logged in as ${randomUser.firstName} ${randomUser.lastName}"))

                }
            }
        }

    }


})