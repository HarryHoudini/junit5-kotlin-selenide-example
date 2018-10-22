package com.exmple.gepur.models.pages

import com.exmple.gepur.models.stuff.User
import io.qameta.allure.Step


class LoginPage : WebPage(){

    override val url: String = "/auth/login"
    override val title: String = "Введите свой логин и пароль на Gepur"

    val emailField = s("#loginform-email")
    val passwordFiled = s("#loginform-password")


    @Step
    fun open(): LoginPage{
        open(this.url)
        return this
    }

    @Step
    fun loginAs(user: User): MainPage {
        emailField.value = user.email
        passwordFiled.setValue(user.password).submit()
        return MainPage()
    }




}
