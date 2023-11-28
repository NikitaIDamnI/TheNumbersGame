package com.example.appthenumbersgame.domain.entities

data class Questions(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
)
