package com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.example.ui.models.patterns.elements_pattern.ElementsContainer


class ProductList(override val elements: ElementsCollection) : ElementsContainer() {

    fun get(index: Int): Product = Product(this.elements.get(index))

    fun first(): Product = Product(this.elements.first())

    fun last(): Product = Product(this.elements.first())

    fun first(range: Int): ProductList = ProductList(this.elements.first(range))

    fun last(range: Int): ProductList = ProductList(this.elements.last(range))

    fun filterBy(condition: Condition) = ProductList(this.elements.filterBy(condition))

    fun filter(condition: Condition) = ProductList(this.elements.filter(condition))


}

