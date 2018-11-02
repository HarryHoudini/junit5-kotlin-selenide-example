package com.example.api.services

import com.example.api.extensions.When
import com.example.api.extensions.shouldHave
import com.example.api.extensions.statusCode
import com.example.api.models.JavaUser
import com.example.api.models.User
import io.qameta.allure.Step
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.RestAssured.*
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification



class UserApiService {


    var requestSpec: RequestSpecification = given()
            .relaxedHTTPSValidation()
            .contentType("application/json; charset=utf-8")
            .filters(RequestLoggingFilter(), ResponseLoggingFilter())
            .filter(AllureRestAssured())
            .basePath("/")


    fun login(): ValidatableResponse
            = step("Make login"){ requestSpec.When().get("login").then() }

    fun registerJavaUser(javaUser: JavaUser): ValidatableResponse
            = step("Register user: $javaUser"){ requestSpec.When().body(javaUser).post("register").then() }

    @Step
    fun getCustomers(): ValidatableResponse
            = requestSpec.When().get("customers").then()

    @Step
    fun getCustomer(id: String): ValidatableResponse
            = requestSpec.When().get("customers/$id").then()

    @Step
    fun deleteCustomer(id: String): ValidatableResponse
            = requestSpec.When().delete("customers/$id").then()

    @Step
    fun registerCustomer(user: User): ValidatableResponse
            = requestSpec.When().body(user).post("register").then()
}



@Step("{0}")
fun step(title: String, code: ()-> ValidatableResponse) = code()
