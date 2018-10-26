package com.example.api.extensions

import io.restassured.response.ValidatableResponse


class Condition(val valResponse: ValidatableResponse) {

    fun check(response: ValidatableResponse) {}

}



fun ValidatableResponse.shouldHave(condition: Condition): ValidatableResponse {
    condition.check(this)
    return this
}