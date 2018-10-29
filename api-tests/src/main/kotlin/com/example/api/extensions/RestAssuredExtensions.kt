package com.example.api.extensions

import com.example.api.models.User
import com.fasterxml.jackson.core.type.TypeReference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.restassured.RestAssured
import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSender
import io.restassured.specification.RequestSpecification
import java.io.FileReader


fun RestAssured.Given(): RequestSpecification = RestAssured.given()

fun RestAssured.When(): RequestSender = RestAssured.`when`()

fun Given(): RequestSpecification = RestAssured.given()

fun When(): RequestSender = RestAssured.`when`()

fun RequestSpecification.Given(): RequestSpecification = this.given()

fun RequestSpecification.When(): RequestSpecification = this.`when`()



fun ValidatableResponse.extractAsUsers(path: String): List<User> {
    val gson = Gson()
    return gson.fromJson<List<User>>(this.extract().body().jsonPath().getString(path) , object: TypeToken<List<User>>(){}.type)
}



fun usersFromJSONFile(filename: String): List<User> {
    val gson = Gson()
    return gson.fromJson<List<User>>(FileReader(filename), object: TypeToken<List<User>>(){}.type)
}
