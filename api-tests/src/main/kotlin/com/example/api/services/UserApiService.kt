package com.example.api.services

import com.example.api.extensions.When
import com.example.api.models.User
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.RestAssured.*
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification

class UserApiService {


    var requestSpec: RequestSpecification
    val defaultContentType = "application/json; charset=utf-8"


    init {
        this.requestSpec = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .filters(RequestLoggingFilter(), ResponseLoggingFilter())
                .filter(AllureRestAssured())
                .basePath("/")
    }

    val login: ValidatableResponse
        get() = requestSpec.When().get("login").then()

    val getCustomers: ValidatableResponse
        get() = requestSpec.When().get("customers").then()

    fun getCustomer(id: String): ValidatableResponse
            = requestSpec.When().get("customers/$id").then()

    fun deleteCustomer(id: String): ValidatableResponse
            = requestSpec.When().delete("customers/$id").then()

    fun registerCustomer(user: User): ValidatableResponse
            = requestSpec.When().body(user).post("register").then()


}