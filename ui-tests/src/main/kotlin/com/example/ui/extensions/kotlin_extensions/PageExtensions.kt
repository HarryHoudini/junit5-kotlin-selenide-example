package com.example.ui.extensions.kotlin_extensions

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.WebDriverRunner.*
import com.example.ui.models.WebPage
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*




fun WebPage.haveUrl(url:String = this.url): WebPage {
    assertEquals(url, getWebDriver().currentUrl)
    return this
}

fun WebPage.containsUrl(url:String = this.url): WebPage {
    assertTrue(getWebDriver().currentUrl.contains(url))
    return this
}

fun WebPage.haveTitle(title:String = this.url): WebPage {
    assertEquals(title, getWebDriver().title)
    return this
}

fun WebPage.containsTitle(title:String = this.url): WebPage {
    assertTrue(getWebDriver().title.contains(url))
    return this
}

fun WebPage.openPage(url: String): WebPage {
    Selenide.open(url)
    return this
}


fun <T: WebPage> T.open(url: String): T{
    return Selenide.open(url, this.javaClass)
}

fun <T: WebPage> T.open(): T{
    step("Open Page: ${this.javaClass.name}"){
        Selenide.open(this.url, this.javaClass).shouldBeOpened()
    }
    return this
}

fun <T: WebPage> T.shouldBeOpened(): T{
    assertTrue(getWebDriver().currentUrl.contains(this.url))
    return this
}

fun <T: WebPage> T.shouldHaveUrl(url: String): T{
    assertEquals(url, getWebDriver().currentUrl)
    return this
}

fun <T: WebPage> T.shouldHaveTitle(title: String): T{
    assertEquals(title, getWebDriver().title)
    return this
}

fun <T: WebPage> T.refresh(): T {
    Selenide.refresh()
    return this
}



@Step("{0}")
fun step(title: String, code: ()-> Any) = code()


@Step("{0}")
fun <T> T.step(title: String, code: ()-> Any): T {
    code()
    return this
}
