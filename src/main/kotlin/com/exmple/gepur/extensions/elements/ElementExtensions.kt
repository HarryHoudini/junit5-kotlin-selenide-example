package com.exmple.gepur.extensions.elements

import com.codeborne.selenide.*
import org.openqa.selenium.By


interface ElementExtensions {

    fun s(cssLocator: String): SelenideElement {
        return Selenide.`$`(cssLocator)
    }

    fun s(locator: By): SelenideElement {
        return Selenide.`$`(locator)
    }

    fun ss(cssLocator: String): ElementsCollection {
        return Selenide.`$$`(cssLocator)
    }

    fun ss(locator: By): ElementsCollection {
        return Selenide.`$$`(locator)
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


    fun SelenideElement.shouldBeVisible(): SelenideElement = this.shouldBe(Condition.visible)

    fun SelenideElement.vaL(text: String): SelenideElement = this.`val`(text)

    fun SelenideElement.s(cssLocator: String): SelenideElement = s(cssLocator)

    fun SelenideElement.ss(cssLocator: String): SelenideElement = ss(cssLocator)

    fun SelenideElement.s(locator: By): SelenideElement = s(locator)

    fun SelenideElement.ss(locator: By): SelenideElement = ss(locator)


    fun SelenideElement.highlight(color: String = "red"): SelenideElement {
        fun highLightElement(col: String, i: Int) = Selenide.executeJavaScript<String>(
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


}