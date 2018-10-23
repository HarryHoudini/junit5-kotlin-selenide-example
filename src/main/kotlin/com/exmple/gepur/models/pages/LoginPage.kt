package com.exmple.gepur.models.pages

import com.exmple.gepur.extensions.kotlin_extensions.*
import com.exmple.gepur.extensions.kotlin_extensions.step

import com.exmple.gepur.models.stuff.User
import io.qameta.allure.Step


class LoginPage : WebPage(){


    override val url: String = "/auth/login"
    override val title: String = "Введите свой логин и пароль на Gepur"

    val emailField = s("#loginform-email")
    val passwordFiled = s("#loginform-password")


    @Step
    fun open(): LoginPage{
        return open(this.url)
    }

    @Step
    fun loginAs(user: User): MainPage {
        emailField.value = user.email
        passwordFiled.setValue(user.password).submit()
        return MainPage()
    }

    fun loginWith(user: User): LoginPage {
        return step("login with user: $user"){
            emailField.value = user.email
            passwordFiled.vaL(user.password).submit()
        }
    }





}
