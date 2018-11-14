package com.example.ui.dsl_tests

import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.example.api.models.User
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.s
import com.example.ui.models.WebPage
import io.qameta.allure.Step


// https://www.youtube.com/watch?v=Y5_s8hNROBQ&t=1837s
// https://github.com/ivan-osipov/kotlin-dsl-example


val lambda: (String) -> Unit = { param ->
    println(param)
}

val lambda2: (String) -> Unit = {
    println(it)
}


fun exec(someCode: () -> Unit){
    someCode()
}

// The same
fun exec(someCode: ()-> Any) = someCode()



// Lambda with context
val out: String.() -> Unit = {
    println(this)
}


// Next Step
class MainPage: WebPage() {
    override val url: String = "/"
    override val title: String = "\n        WeaveSocks\n    "
    var usernameButton = s("#howdy")
    var loginButton = s("#login")
    var registerButton = s("#register")
    var accountButton = s("#tabAccount > a")
    var usernameField = s("#username-modal")
    var passwordField = s("#password-modal")
    var submitButton = s("button[onclick='return login()']")
    var successMessage = s(Selectors.byText("Login successful."))
    var errorMessage = s("#login-message > div")
    fun loginWith(user: User): MainPage {
        loginButton.click()
        usernameField.value = user.username
        passwordField.value = user.password
        submitButton.click()
        return MainPage()
    }
}


val open: MainPage.() -> MainPage = {
    Selenide.open(this.url, this.javaClass)
}



fun mainPage(init: MainPage.()-> Unit){
    val mainPage = MainPage()
    mainPage.init()
}



val user = User(firstName = "Savva",
        lastName = "Genchevskiy",
        username = "savva.gench",
        email = "" ,
        password =  "s.g19021992", id = "")

fun main(args: Array<String>) {
    lambda("someting")

    exec { println("test") }
    exec { println("Hello!") }

    "Hello, World".out()

    MainPage().open()

    mainPage {
        open()
        loginWith(user)
    }

    page {
        open
        loginWith()

    }


}



// Singleton
object MyObject {
    fun printText(text: String){
        println(text)
    }
}





// Next Step
class Page: WebPage() {
    override val url: String get() = "/login"
    override val title: String get() = "Test"
    val emailField = s("#email-login")
    fun loginWith(): Page { return this }
}


object page {
    operator fun invoke(init: Page.() -> Unit) {
        Page().init()
    }
}







object schedule {
    operator fun invoke(init: SchedulingContext.()-> Unit) = SchedulingContext().init()
}

const val ERROR_MSG = "Так не надо! \uD83D\uDE31"


@MyCustomDslMarker
class SchedulingContext {

    fun website(init: WebsiteContext.() -> Unit): WebsiteContext = WebsiteContext().apply(init)

}


@MyCustomDslMarker
class WebsiteContext {

    val accounts = ArrayList<User>()

    fun account(init: User.()-> Unit)
            = User("", "", "",  "" ,  "", "").apply {
        username = "savva.gench"
        init()
        accounts.add(this)
    }

    fun mainPage(init: MainPage.()-> Unit) = MainPage().init()

    fun page(init: Page.() -> Unit) = Page().init()

    fun assertions(init: AssertionsContext.() -> Unit) = AssertionsContext(this).init()

}




@MyCustomDslMarker
class AssertionsContext(website: WebsiteContext) {

    fun website(init: WebsiteContext.() -> Unit): WebsiteContext = this.website(init)

}



infix fun WebsiteContext.assertions(init: WebsiteContext.() -> Unit) = this.init()

// https://www.youtube.com/watch?v=Y5_s8hNROBQ&t=1837s
// https://github.com/ivan-osipov/kotlin-dsl-example
