package com.example.specs

import io.kotlintest.matchers.string.shouldStartWith
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec


class HelloSpec : StringSpec({
    "length should return size of string" {
        "hello".length.shouldBe(5)
    }
    "startsWith should test for a prefix" {
        "world".shouldStartWith("wor")
    }
})