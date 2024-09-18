// Entity.kt
package com.example.assigment1

import java.io.Serializable

data class Entity(
    val property1: String,
    val property2: String,
    val description: String
) : Serializable // Serializable so we can pass it to the Details screen
