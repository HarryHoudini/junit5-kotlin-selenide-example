package com.exmple.gepur.extensions.kotlin_extensions

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.exmple.gepur.models.pages.WebPage
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions


fun WebPage.haveUrl(url:String = this.url): WebPage {
    Assertions.assertEquals(url, WebDriverRunner.getWebDriver().currentUrl)
    return this
}

fun WebPage.containsUrl(url:String = this.url): WebPage {
    Assertions.assertTrue(WebDriverRunner.getWebDriver().currentUrl.contains(url))
    return this
}

fun WebPage.haveTitle(title:String = this.url): WebPage {
    Assertions.assertEquals(title, WebDriverRunner.getWebDriver().title)
    return this
}

fun WebPage.containsTitle(title:String = this.url): WebPage {
    Assertions.assertTrue(WebDriverRunner.getWebDriver().title.contains(url))
    return this
}

fun WebPage.openPage(url: String): WebPage {
    Selenide.open(url)
    return this
}

fun <T: WebPage> T.open(url: String): T{
    return Selenide.open(url, this.javaClass)
}



fun <T: WebPage> T.shouldBeOpened(): T{
    Assertions.assertTrue(WebDriverRunner.getWebDriver().currentUrl.contains(this.url))
    return this
}

fun <T: WebPage> T.shouldHaveUrl(url: String): T{
    Assertions.assertEquals(url, WebDriverRunner.getWebDriver().currentUrl)
    return this
}

fun <T: WebPage> T.shouldHaveTitle(title: String): T{
    Assertions.assertEquals(title, WebDriverRunner.getWebDriver().title)
    return this
}

@Step("{0}")
fun step(title: String, code: ()-> Any) = code()


@Step("{0}")
fun <T> T.step(title: String, code: ()-> Any): T {
    code()
    return this
}
