package com.example.ui.models.patterns.elements_pattern


import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.ElementsCollection


abstract class ElementsContainer {

    abstract val elements: ElementsCollection

    fun getSelf(): ElementsCollection = this.elements

}

fun <T: ElementsContainer> T.shouldHaveSize(size: Int): T {
    this.elements.shouldHaveSize(size)
    return this
}

fun <T: ElementsContainer> T.shouldBe(condition: CollectionCondition): T {
    this.elements.shouldBe(condition)
    return this
}

fun <T: ElementsContainer> T.shouldHave(condition: CollectionCondition): T {
    this.elements.shouldHave(condition)
    return this
}



