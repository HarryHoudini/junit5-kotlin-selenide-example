package com.example.ui.models.patterns.elements_pattern.pages.CatalogPage

import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.ss
import com.example.ui.models.WebPage
import com.example.ui.models.patterns.elements_pattern.pages.CatalogPage.elements.ProductList
import io.qameta.allure.Step

class CatalogPage: WebPage(){

    override val url: String = "/category.html"
    override val title: String = "\n        WeaveSocks\n    "

    val products = ProductList(ss("#products .product"))
    val productList = ss("#products .product")


    @Step
    fun open(): CatalogPage {
        return open(this.url)
    }



}