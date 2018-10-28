package com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements

import com.codeborne.selenide.SelenideElement
import com.example.ui.models.patterns.elements_pattern.ElementContainer
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.CatalogPage
import io.qameta.allure.Step

class Product(override val element: SelenideElement) : ElementContainer(){

    var title = this.element.`$`(".text h3 a")
    var price = this.element.`$`(".price")
    var cartButton = this.element.`$`("a[onclick^='addToCart']")

    @Step
    fun addToCart(): CatalogPage {
        cartButton.click()
        return CatalogPage()
    }
}