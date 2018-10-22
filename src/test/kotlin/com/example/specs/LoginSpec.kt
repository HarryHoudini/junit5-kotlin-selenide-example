package com.example.specs

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Configuration.baseUrl
import com.codeborne.selenide.logevents.SelenideLogger
import com.exmple.gepur.models.pages.LoginPage
import com.exmple.gepur.models.pages.MainPage
import com.exmple.gepur.models.stuff.User
import io.kotlintest.*
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension



class LoginSpec: StringSpec(), TestListener {

    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy", email = "genchevskiy.gepur@gmail.com", password = "S.genchevskiy19021992")
    val loginPage = LoginPage()
    val mainPage = MainPage()
    override fun listeners(): List<TestListener> = listOf(TimerListener)


    override fun beforeSpec(description: Description, spec: Spec) {
        baseUrl = "https://gepur.com"
    }


    init {
        "Should Login user"{
            loginPage.run {
                open().loginAs(user)
                        .shouldBeOpened()
                        .shouldHaveTitle(title)
                        .accountButton.shouldBeVisible()
                        .shouldHave(text(user.lastName))
            }

        }
    }


}







class LoginSpecAbnotated: AnnotationSpec() {

    @RegisterExtension
    val user = User(firstName = "Savva", lastName = "Genchevskiy", email = "genchevskiy.gepur@gmail.com", password = "S.genchevskiy19021992")
    val loginPage = LoginPage()
    val mainPage = MainPage()


    @BeforeAll
    fun setUp() {
        baseUrl = "https://gepur.com"
    }

    @Test
    fun shouldLogin() {
        loginPage.run {
            open().loginAs(user)
                    .shouldBeOpened()
                    .shouldHaveTitle(title)
                    .accountButton.shouldBeVisible()
                    .shouldHave(text(user.lastName))
        }
    }
}











object TimerListener : TestListener {


    var started = 0L

    override fun beforeTest(description: Description) {
        started = System.currentTimeMillis()
    }

    override fun afterTest(description: Description, result: TestResult) {
        println("Duration of $description = " + (System.currentTimeMillis() - started))
    }

}
