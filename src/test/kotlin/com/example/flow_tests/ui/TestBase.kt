package com.example.flow_tests.ui

import com.codeborne.selenide.Configuration.*
import com.exmple.electric_flow.extensions.Extensions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll


open class TestBase: Extensions(){


    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAllTestCases() {
            println("Runs once before all test cases.")
            baseUrl = "https://10.200.1.156"
            browser = "chrome"
            startMaximized = true
        }

        @AfterAll
        @JvmStatic
        fun afterAllTestCases() {
            println("Runs once after all test cases.")
        }
    }

}