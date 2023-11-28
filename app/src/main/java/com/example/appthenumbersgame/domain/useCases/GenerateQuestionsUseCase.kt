package com.example.appthenumbersgame.domain.useCases

import com.example.appthenumbersgame.domain.entities.Questions
import com.example.appthenumbersgame.domain.repository.GameRepository

class GenerateQuestionsUseCase (private val gameRepository: GameRepository){

    operator fun invoke (maxSumValue: Int): Questions{
        return gameRepository.generateQuestionsUseCase(maxSumValue,COUNT_OF_OPTIONS)
    }



    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }

}