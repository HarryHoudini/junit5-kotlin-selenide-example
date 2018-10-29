package com.example.ui.models.patterns.elements_pattern.pages.CatalogPage

import com.codeborne.selenide.Condition
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.extensions.kotlin_extensions.ss
import com.example.ui.models.WebPage
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements.Filters
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements.ProductList
import io.qameta.allure.Step
import com.example.ui.models.parameters.Filters.*
import com.codeborne.selenide.Selectors.*
import com.example.ui.models.parameters.Sorting.*
import io.kotlintest.shouldBe

class CatalogPage: WebPage(){

    override val url: String = "/category.html"
    override val title: String = "\n        WeaveSocks\n    "


    val products = ProductList(ss("#products .product"))
    val productList = ss("#products .product")
    val filter = Filters(s("#filters-form"))
    val sortSelect = s(byName("sort-by"))


    @Step
    fun filterBy(color: ColorFilter): CatalogPage {
        return filter.filterBy(color)
    }

    @Step
    fun sortBy(sortBy: Sorter){
        with(sortSelect) {
            selectOptionByValue(sortBy.value)
            shouldBe(Condition.selected)
        }

    }


}