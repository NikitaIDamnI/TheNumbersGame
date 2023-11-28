package com.example.appthenumbersgame.domain.repository

import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.domain.entities.Questions

interface GameRepository {
    fun generateQuestionsUseCase(
        maxSumValue: Int,
        countOfOptions:Int
    ): Questions
    fun getGameSettingsUseCase(level: Level): GameSettings

}