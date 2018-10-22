package com.exmple.gepur.extensions.webpage

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.exmple.gepur.models.pages.WebPage
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions.*


interface WebPageExtensions {

    fun WebPage.haveUrl(url:String = this.url): WebPage {
        assertEquals(url, WebDriverRunner.getWebDriver().currentUrl)
        return this
    }

    fun WebPage.containsUrl(url:String = this.url): WebPage {
        assertTrue(WebDriverRunner.getWebDriver().currentUrl.contains(url))
        return this
    }

    fun WebPage.haveTitle(title:String = this.url): WebPage {
        assertEquals(title, WebDriverRunner.getWebDriver().title)
        return this
    }

    fun WebPage.containsTitle(title:String = this.url): WebPage {
        assertTrue(WebDriverRunner.getWebDriver().title.contains(url))
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
        assertTrue(WebDriverRunner.getWebDriver().currentUrl.contains(this.url))
        return this
    }

    fun <T: WebPage> T.shouldHaveUrl(url: String): T{
        assertEquals(url, WebDriverRunner.getWebDriver().currentUrl)
        return this
    }

    fun <T: WebPage> T.shouldHaveTitle(title: String): T{
        assertEquals(title, WebDriverRunner.getWebDriver().title)
        return this
    }


    @Step("{0}")
    fun step(title: String, code: ()-> Any) = code()


}