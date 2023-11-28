package com.example.appthenumbersgame.domain.useCases

import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.domain.repository.GameRepository

class GetGameSettingsUseCase(private val gameRepository: GameRepository) {
    operator fun invoke (level: Level): GameSettings{
       return gameRepository.getGameSettingsUseCase(level)
   }
}