package com.exmple.electric_flow.extensions

import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

interface ElementExtension {


    fun s(locator: String): SelenideElement {
        return Selenide.`$`(locator)
    }
    fun s(locator: By): SelenideElement {
        return Selenide.`$`(locator)
    }


    fun ss(locator: String): ElementsCollection {
        return Selenide.`$$`(locator)
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

        }

        inner class Be {
            val visible: Unit get() { actual.shouldBe(Condition.visible) }
        }


    }


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

