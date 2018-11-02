package com.example.ui.extensions.kotlin_extensions

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import io.kotlintest.shouldHave
import org.openqa.selenium.By


fun s(cssLocator: String): SelenideElement {
    return `$`(cssLocator)
}


fun s(locator: By): SelenideElement {
    return `$`(locator)
}

fun ss(cssLocator: String): ElementsCollection {
    return `$$`(cssLocator)
}

fun ss(locator: By): ElementsCollection {
    return `$$`(locator)
}


val SelenideElement.should: ExpectElement
    get() {
        return ExpectElement(this)
    }

class ExpectElement(private val actual: SelenideElement){

    val have: Have = Have()
    val be: Be = Be()

    inner class Have {
        fun text(text: String) = actual.shouldHave(Condition.text(text))
        fun exactText(text: String?) = actual.shouldHave(Condition.exactText(text))
        fun attribute(attr: String, value: String) = actual.shouldHave(Condition.attribute(attr, value))
        fun value(expectedValue: String) = actual.shouldHave(Condition.value(expectedValue))
    }

    inner class Be {
        val visible: Unit get() { actual.shouldBe(Condition.visible) }
        val enabled: Unit get() { actual.shouldBe(Condition.enabled) }
        val exist: Unit get() { actual.shouldBe(Condition.exist) }
        val disabled: Unit get() { actual.shouldBe(Condition.disabled) }
        val appear: Unit get() { actual.shouldBe(Condition.appear) }
    }
}


fun SelenideElement.shouldBeVisible(): SelenideElement = this.shouldBe(visible)

fun SelenideElement.vaL(text: String): SelenideElement = this.`val`(text)

fun SelenideElement.s(cssLocator: String): SelenideElement = `$`(cssLocator)

fun SelenideElement.ss(cssLocator: String): ElementsCollection = `$$`(cssLocator)

fun SelenideElement.s(locator: By): SelenideElement = `$`(locator)

fun SelenideElement.ss(locator: By): ElementsCollection = `$$`(locator)

fun SelenideElement.Is(condition: Condition): Boolean = this.`is`(condition)

fun SelenideElement.shouldHaveClass(classValue: String): SelenideElement = this.shouldHave(attribute("class", classValue))


fun SelenideElement.highlight(color: String = "red"): SelenideElement {
    fun highLightElement(col: String, i: Int) = executeJavaScript<String>(
            "arguments[0].setAttribute('__selenideHighlighting', 'done'); " + "arguments[0].setAttribute(arguments[1], arguments[2])",
            this,
            "style",
            "border: " + i + "px solid " + col + "; border-style: dotted; " + "transform: scale(1, 1." + i + ");")
    if (this.getAttribute("__selenideHighlighting") == null) {
        for (i in 1..3) {
            highLightElement(color, i)
            Thread.sleep(30)
        }
        for (i in 4 downTo 1) {
            highLightElement(color, i)
            Thread.sleep(30)
        }
    }
    return this
}