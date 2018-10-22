package com.exmple.electric_flow

import com.codeborne.selenide.WebDriverRunner
import com.exmple.electric_flow.extensions.Extensions
import com.exmple.gepur.models.pages.WebPage
import org.junit.jupiter.api.Assertions


abstract class WebPage: Extensions() {

    abstract val url: String
    abstract val title: String



}