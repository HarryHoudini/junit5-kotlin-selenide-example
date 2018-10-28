package com.example.ui.models.patterns.elements_pattern

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement


abstract class ElementContainer {

    abstract val element: SelenideElement

    fun getSelf(): SelenideElement = this.element

}


fun <T: ElementContainer> T.shouldBe(condition: Condition): T {
    this.element.shouldBe(condition)
    return this
}

fun <T: ElementContainer> T.should(condition: Condition): T {
    this.element.should(condition)
    return this
}

fun <T: ElementContainer> T.shouldNotBe(condition: Condition): T {
    this.element.shouldNotBe(condition)
    return this
}


fun <T: ElementContainer> T.shouldHave(condition: Condition): T {
    this.element.shouldHave(condition)
    return this
}

fun <T: ElementContainer> T.shouldNotHave(condition: Condition): T {
    this.element.shouldNotHave(condition)
    return this
}
