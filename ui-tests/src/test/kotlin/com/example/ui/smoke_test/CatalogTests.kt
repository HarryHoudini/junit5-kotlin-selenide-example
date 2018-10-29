package com.example.ui.smoke_test

import com.example.ui.BaseTest
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.shouldBeOpened
import org.junit.jupiter.api.Test

class CatalogTests: BaseTest(){

    @Test
    fun setUpTest(){
        catalogPage.open().shouldBeOpened()

    }


}