package com.example.kotlinbasics

fun main() {
    var myAge: Byte = 28
    println(myAge)
    myAge = 29
    println(myAge)

    //    Floating Numbers
    var pi = 3.14f
    println(pi)
    pi = 3.1415738379f
    println(pi)

    // Unsigned
    val age: UShort = 28u
    println(age)

    // Boolean
    val myTrue: Boolean = true
    val myFalse: Boolean = false

    println(myTrue || myFalse) // true
    println(myTrue && myFalse) // false
    println(!myTrue) // false

    // Characters
    val myChar = 'a'
    println(myChar)
    val copyrightChar = '\u00AE'
    println(copyrightChar)

    // String
    var name = "Subhankar"
    name = "Frank"
    println(name)
    println(name.lowercase())
    println(name + " Maiti")
}