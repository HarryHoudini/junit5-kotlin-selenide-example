package com.example.ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.models.patterns.kiss_pattern.MainPage
import io.kotlintest.AbstractProjectConfig
import io.kotlintest.Description
import io.kotlintest.TestResult
import io.kotlintest.data.forall
import io.kotlintest.extensions.ProjectExtension
import io.kotlintest.extensions.TestListener
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row


class LoginSpecs: StringSpec(), TestListener {


    override fun beforeTest(description: Description) {
        Configuration.baseUrl = "http://35.232.243.253"
    }

    override fun afterTest(description: Description, result: TestResult) {
    }



    init {

        "should not login with invalid data"{
            forall(
                    row(User(firstName = "", lastName = "", username = "savva", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy@gmail.com", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials!."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials.")
            ){ user: User, message: String ->
                MainPage().open().login()
                        .loginWith(user)
                        .loginModal.errorMessage.shouldBe(visible).shouldHave(text(message))
            }

        }


    }



}