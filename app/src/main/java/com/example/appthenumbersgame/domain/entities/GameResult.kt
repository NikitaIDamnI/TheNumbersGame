package com.example.appthenumbersgame.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class GameResult(
    val winners : Boolean,
    val countOfRightAnswer : Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
): Parcelable