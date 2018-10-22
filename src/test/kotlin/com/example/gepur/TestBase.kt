package com.example.gepur

import com.codeborne.selenide.Configuration.*
import com.exmple.gepur.extensions.Extensions
import com.exmple.gepur.models.pages.LoginPage
import com.exmple.gepur.models.pages.MainPage
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll


open class TestBase: Extensions(){

    var mainPage: MainPage = MainPage()
    var loginPage: LoginPage = LoginPage()

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
            println("Runs once before all test cases.")
            baseUrl = "http://gepur.com"
            browser = "chrome"
            startMaximized = true



        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            println("Runs once after all test cases.")
        }
    }

}