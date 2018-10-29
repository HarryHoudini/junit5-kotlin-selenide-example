package com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.SelenideElement
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.extensions.kotlin_extensions.ss
import com.example.ui.models.parameters.Filters
import com.example.ui.models.patterns.elements_pattern.ElementContainer
import com.example.ui.models.parameters.Filters.*
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage

class Filters(override val element: SelenideElement): ElementContainer() {


    val colorFilter = this.element.ss("#filters input[type='checkbox']")
    val applyButton = this.element.s("a[onclick^='setNewTags']")

    fun filterBy(color: ColorFilter): CatalogPage {
        val checkBox = colorFilter.filterBy(value(color.value)).first()
        checkBox.click()
        applyButton.click()
        checkBox.shouldBe(checked)
        return CatalogPage()
    }


}