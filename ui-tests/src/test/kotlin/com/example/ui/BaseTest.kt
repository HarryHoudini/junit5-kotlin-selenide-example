package com.example.ui



open class BaseTest {


    fun <T> at(tClass: Class<T>): T = tClass.newInstance()



}