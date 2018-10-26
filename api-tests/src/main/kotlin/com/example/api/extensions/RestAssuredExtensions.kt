package com.example.api.extensions

import io.restassured.RestAssured
import io.restassured.RestAssured.*
import io.restassured.specification.RequestSender
import io.restassured.specification.RequestSpecification


fun RestAssured.Given(): RequestSpecification = RestAssured.given()

fun RestAssured.When(): RequestSender = RestAssured.`when`()

fun Given(): RequestSpecification = RestAssured.given()

fun When(): RequestSender = RestAssured.`when`()

fun RequestSpecification.Given(): RequestSpecification = this.given()

fun RequestSpecification.When(): RequestSpecification = this.`when`()