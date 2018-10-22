package com.exmple.gepur.models.pages

import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.WebDriverRunner.*
import com.exmple.gepur.extensions.Extensions
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions.*


abstract class WebPage: Extensions() {

    abstract val url: String
    abstract val title: String

    val header = Header()



}