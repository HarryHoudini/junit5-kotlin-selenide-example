package com.example.ui.spec_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import io.kotlintest.Description
import io.kotlintest.TestResult
import io.kotlintest.data.forall
import io.kotlintest.extensions.TestListener
import io.kotlintest.inspectors.forAtLeast
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import org.apache.commons.lang3.SystemUtils.IS_OS_LINUX
import org.junit.jupiter.api.extension.RegisterExtension



class LoginSpecs: StringSpec(), TestListener {


    private val customerEmail = System.getProperty("EMAIL")!!
    private val customerPass = System.getProperty("PASS")!!

    @JvmField
    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy", username = "savva.gench",
                        email = customerEmail, password =  customerPass, id = "")


    override fun beforeTest(description: Description) {
        Configuration.baseUrl = "http://35.232.243.253"
    }

    override fun afterTest(description: Description, result: TestResult) {
    }


    init {

        "should login user".config(enabled = IS_OS_LINUX){
            MainPage().run {
                open().login()
                        .loginWith(user)
                        .accountButton.shouldBe(visible)
                        .shouldHave(text("Logged in as ${user.firstName} ${user.lastName}"))
            }
        }

        "should not login with invalid data"{
            forall(
                    row(User(firstName = "", lastName = "", username = "savva", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy@gmail.com", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials."),
                    row(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials.")
            ){ user: User, message: String ->
                MainPage().open()
                        .login()
                        .loginWith(user)
                        .loginModal.message.shouldBe(visible).shouldHave(text(message))
            }

        }


        "should open catalog page"{
            CatalogPage().run {
                    open()
                    productList.forAtLeast(4) {
                        it.shouldBe(visible)
                    }

            }
        }

    }



}

