package com.exmple.electric_flow.pages

import com.exmple.electric_flow.WebPage

class MainPage : WebPage() {

    override val url: String = "/flow/#index"
    override val title: String = ""

    var usernameButton = s(".username")
    var logoutButton = s("div[class*='logout']")
    var homeMenu = ss(".home-menu a")


}