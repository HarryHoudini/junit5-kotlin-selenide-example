package com.exmple.gepur.models.pages

import com.codeborne.selenide.Selectors.*

class MainPage: WebPage() {
    override val url: String = "/"
    override val title: String = "GEPUR: Женская одежда оптом и в розницу от производителя, официальный сайт интернет магазина Гепюр (Гипюр), продажа в Европе и странах СНГ, в России, Украине, Казахстане, Беларуси, Литве, Латвии"

    val accountButton = s(byClassName("h-account"))


}