package com.example.ui

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.logevents.SelenideLogger
import com.example.api.models.User
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage
import com.example.ui.models.patterns.elements_pattern.pages.MainPage.MainPage
import com.github.javafaker.Faker
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.RegisterExtension
import java.util.*


open class BaseTest {

    val faker: Faker = Faker(Locale("en-US"))
    val customerEmail = System.getProperty("EMAIL")
    val customerPass = System.getProperty("PASS")

    @JvmField
    @RegisterExtension
    val mainPage = MainPage()

    @RegisterExtension
    val catalogPage = CatalogPage()

    @JvmField
    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy",
            username = "savva.gench",
            email = customerEmail ,
            password =  customerPass, id = "")




    companion object {


        @JvmStatic
        @BeforeAll
        fun setUpTests(){
            Configuration.baseUrl = "http://35.232.243.253"
            SelenideLogger.addListener("allure", AllureSelenide())
        }


        @JvmStatic
        @AfterAll
        fun tearDownTests(){
            SelenideLogger.removeListener<AllureSelenide>("allure")
        }

    }

    fun <T> at(tClass: Class<T>): T = tClass.newInstance()



}