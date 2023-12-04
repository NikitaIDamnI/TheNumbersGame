package com.example.appthenumbersgame.domain.entities

import java.io.Serializable

data class GameResult(
    val winners : Boolean,
    val countOfRightAnswer : Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
): Serializable