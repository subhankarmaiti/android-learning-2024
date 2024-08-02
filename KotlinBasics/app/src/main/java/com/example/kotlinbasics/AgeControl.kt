package com.example.kotlinbasics

fun main() {
    print("Enter your age as a whole number: ")
    var age: Int = readln().toInt()
    if(age in 18..39) {
        print("You can enter the club.")
    } else if(age>=40) {
        print("You cannot go into the club, please go home.")
    }  else {
        print("Age not verified. Please contact support.")
    }
}