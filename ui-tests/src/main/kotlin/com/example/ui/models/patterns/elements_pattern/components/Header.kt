package com.example.ui.models.patterns.elements_pattern.components


import com.codeborne.selenide.Selectors.*
import com.example.ui.extensions.Extensions

class Header: Extensions(){

    val catalogButton = s(byText("Каталог"))
    val catalogList = ss(".h-menu__section div[class^='h-menu-c__item'] a")


}