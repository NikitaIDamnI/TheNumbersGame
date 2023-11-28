package com.example.appthenumbersgame.data

import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.domain.entities.Questions
import com.example.appthenumbersgame.domain.repository.GameRepository

object GameRepositoryImpl:GameRepository  {
    override fun generateQuestionsUseCase(maxSumValue: Int, countOfOptions: Int): Questions {
        TODO("Not yet implemented")
    }

    override fun getGameSettingsUseCase(level: Level): GameSettings {
        TODO("Not yet implemented")
    }
}