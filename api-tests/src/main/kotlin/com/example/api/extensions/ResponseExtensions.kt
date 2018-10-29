package com.example.api.extensions

import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matcher


// Implementation from value

val ValidatableResponse.shouldHave: AssertableResponse
    get() {
        return AssertableResponse(this)
    }



class AssertableResponse(val response: ValidatableResponse){

    fun statusCode(statusCode: Int): ValidatableResponse {
        return response.assertThat().statusCode(statusCode)
    }
}




// Implementation from  Condition object


fun ValidatableResponse.shouldHave(condition: Condition): ValidatableResponse {
    condition.check(this)
    return this
}


abstract class Condition {
    abstract fun check(validatableResponse: ValidatableResponse)
}


fun statusCode(statusCode: Int) = StatusCodeCondition(statusCode)

fun contentType(contentType: ContentType) = ContentTypeCondition(contentType)

fun body(matcher: Matcher<Any>) = BodyCondition(matcher)

fun body(path: String, matcher: Matcher<Any>) = BodyPathCondition(path, matcher)




class StatusCodeCondition(val statusCode: Int): Condition() {
    override fun check(validatableResponse: ValidatableResponse) {
        validatableResponse.assertThat().statusCode(statusCode)
    }
}

class ContentTypeCondition(val contentType: ContentType): Condition(){
    override fun check(validatableResponse: ValidatableResponse) {
        validatableResponse.assertThat().contentType(contentType)
    }

}

class BodyCondition(val matcher: Matcher<Any>): Condition(){
    override fun check(validatableResponse: ValidatableResponse) {
        validatableResponse.assertThat().body(matcher)
    }
}

class BodyPathCondition(val path: String, val matcher: Matcher<Any>): Condition(){
    override fun check(validatableResponse: ValidatableResponse) {
        validatableResponse.assertThat().body(path, matcher)
    }
}
