package com.example.ui.smoke_test

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.*
import com.example.ui.models.patterns.kiss_pattern.MainPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

import java.util.stream.Stream



class LoginTests2 {


    private val customerEmail = System.getProperty("EMAIL")!!
    private val customerPass = System.getProperty("PASS")!!

    @RegisterExtension
    val mainPage = MainPage()

    @JvmField
    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy",
            username = "savva.genchevskiy",
            email = customerEmail,
            password = customerPass, id = "")


    @BeforeEach
    fun setUp(){
        Configuration.baseUrl = "http://35.232.243.253"
    }

    @AfterEach
    fun tearDown(){
        if (mainPage.logoutButton.Is(visible)){
            mainPage.logout()
        }
    }

    @Test
    fun `should login to system`(){
        mainPage.open()
                .login()
                .loginWith(user)
        mainPage.loginModal.successMessage.shouldBe(visible)
        mainPage.accountButton.shouldBe(visible).shouldHave(text(""))
    }

    @ParameterizedTest
    @MethodSource("loginData")
    // @ArgumentsSource(PersonProvider::class)
    // @ValueSource(strings = arrayOf("development", "test", "production"))
    fun `should not login with invalid data`(user: User, message: String){
        mainPage.open().login()
                .loginWith(user)
        mainPage.loginModal.errorMessage.shouldBe(visible).shouldHave(text(message))
    }



    internal class PersonProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> = Stream.of(
                arguments(User(firstName = "", lastName = "", username = "savva", email = "", password =  "123", id = ""), "Invalid login credentials."),
                arguments(User(firstName = "", lastName = "", username = "savva", email = "", password =  "123", id = ""), "Invalid login credentials.")
        ).map { Arguments.of(it) }
    }

    companion object {
        @JvmStatic
        fun loginData(): Stream<Arguments> = Stream.of(
                arguments(User(firstName = "", lastName = "", username = "savva", email = "", password = "123", id = ""), "Invalid login credentials."),
                arguments(User(firstName = "", lastName = "", username = "", email = "", password = "123", id = ""), "Invalid login credentials."),
                arguments(User(firstName = "", lastName = "", username = "savva.genchevskiy@gmail.com", email = "", password = "123", id = ""), "Invalid login credentials."),
                arguments(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials."),
                arguments(User(firstName = "", lastName = "", username = "savva.genchevskiy", email = "", password = "123", id = ""), "Invalid login credentials.")
        )

    }
}

