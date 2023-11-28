package com.example.appthenumbersgame.data

import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.domain.entities.Questions
import com.example.appthenumbersgame.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl:GameRepository  {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestionsUseCase(maxSumValue: Int, countOfOptions: Int): Questions {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibility = Random.nextInt(MIN_SUM_VALUE, sum)
        val option = HashSet<Int>()
        val rightAnswer = sum - visibility
        option.add(rightAnswer)
        val form = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (option.size < countOfOptions) {
            option.add(Random.nextInt(form, to))
        }
        return Questions(sum,visibility,option.toList())
    }

    override fun getGameSettingsUseCase(level: Level): GameSettings {
       return when(level){
           Level.TEST -> GameSettings(
               10,
               3,
               50,
               8
           )
           Level.EASY -> GameSettings(
               10,
               10,
               70,
               60
           )
           Level.NORMAL -> GameSettings(
               20,
               20,
               80,
               50
           )
           Level.HARD -> GameSettings(
               30,
               30,
               90,
               40
           )
       }
    }
}