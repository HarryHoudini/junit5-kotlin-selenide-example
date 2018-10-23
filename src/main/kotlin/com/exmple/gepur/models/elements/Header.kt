package com.exmple.gepur.models.elements

import com.exmple.gepur.extensions.Extensions
import com.codeborne.selenide.Selectors.*

class Header: Extensions(){

    val catalogButton = s(byText("Каталог"))
    val catalogList = ss(".h-menu__section div[class^='h-menu-c__item'] a")


}