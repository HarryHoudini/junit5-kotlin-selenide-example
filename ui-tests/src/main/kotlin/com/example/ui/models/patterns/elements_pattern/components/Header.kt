package com.example.ui.models.patterns.elements_pattern.components


import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.SelenideElement
import com.example.ui.extensions.Extensions
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.extensions.kotlin_extensions.ss
import com.example.ui.models.patterns.elements_pattern.ElementContainer

class Header(override val element: SelenideElement) : ElementContainer(){

    val catalogButton = this.element.s(byText("Каталог"))
    val catalogList = this.element.ss(".h-menu__section div[class^='h-menu-c__item'] a")


}