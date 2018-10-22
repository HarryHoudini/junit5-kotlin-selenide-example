package com.example.flow_tests.api




data class Animal(val name: String){

    fun run() = println("$name is running.")

}





fun main(args: Array<String>) {

    val animal = Animal(name = "dog")

    animal.run()

}


