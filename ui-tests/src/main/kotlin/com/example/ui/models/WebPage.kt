package com.example.ui.models

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.patterns.elements_pattern.components.Footer
import com.example.ui.models.patterns.elements_pattern.components.Header

abstract class WebPage {

    abstract val url: String
    abstract val title: String

    val header = Header(s("#topbar"))
    val footer = Footer(s("#footer"))

}

