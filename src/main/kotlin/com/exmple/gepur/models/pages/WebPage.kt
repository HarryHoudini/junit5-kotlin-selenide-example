package com.exmple.gepur.models.pages

import com.exmple.gepur.models.elements.Header


abstract class WebPage {

    abstract val url: String
    abstract val title: String

    val header = Header()


}