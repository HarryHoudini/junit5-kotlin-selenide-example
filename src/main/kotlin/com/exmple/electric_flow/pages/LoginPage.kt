package com.exmple.electric_flow.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byId
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide
import com.exmple.electric_flow.User
import com.exmple.electric_flow.WebPage


class LoginPage: WebPage() {

    override val url: String = "/flow/#login"
    override val title: String = ""

    val usernameField = s("#username")
    val passwordField = s(byId("password"))
    val loginButton = s(byText("Login"))
    val errorMessage = s("span[class='ec-validation-popover__message']")


    fun open(): LoginPage {
        Selenide.open(url)
        return this
    }

    fun loginAs(user: User): MainPage {
        usernameField.shouldBe(visible).value = user.username
        passwordField.value = user.pasword
        loginButton.click()
        return MainPage()
    }




}