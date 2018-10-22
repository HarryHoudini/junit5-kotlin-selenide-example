package com.exmple.electric_flow

import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

open class ElementExtension {

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




}

