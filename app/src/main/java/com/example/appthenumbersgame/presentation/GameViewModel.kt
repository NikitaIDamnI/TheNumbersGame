package com.example.appthenumbersgame.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.data.GameRepositoryImpl
import com.example.appthenumbersgame.domain.entities.GameResult
import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.domain.entities.Questions
import com.example.appthenumbersgame.domain.useCases.GenerateQuestionsUseCase
import com.example.appthenumbersgame.domain.useCases.GetGameSettingsUseCase


class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GameRepositoryImpl

    private val generateQuestionsUseCase = GenerateQuestionsUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private val context = application

    private lateinit var level: Level
    lateinit var gameSettings: GameSettings
//ФОРМАТ ВРЕМЕНИ
    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var timer: CountDownTimer? = null

    private var _question = MutableLiveData<Questions>()
    val question: LiveData<Questions>
        get() = _question

    private var _percentOfRightAnswer =  MutableLiveData<Int>()
    val percentOfRightAnswer: LiveData<Int>
        get() = _percentOfRightAnswer

    private var _progressAnswers =  MutableLiveData<String>()
    val progressAnswer: LiveData<String>
        get() = _progressAnswers


    private var _enoughCount =  MutableLiveData<Boolean>()
    val enoughCount: LiveData<Boolean>
        get() = _enoughCount


    private var _enoughPercent =  MutableLiveData<Boolean>()
    val enoughPercent: LiveData<Boolean>
        get() = _enoughPercent

    private var _gameResult=  MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private var _minPercent=  MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent




    private var countOfRightAnswers = 0
    private var countOfQuestions= 0


    // Начинаем игру
    fun startGame(level: Level) {
        getGameSettings(level)
        updateProgress()
        startTimer()
        generateQuestions()
    }
    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswer
    }

    //Запуск таймера
    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSecond * MILLIS_IN_SECONDS, MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finish()
            }
        }
        timer?.start()
    }

    private fun generateQuestions(){
       _question.value = generateQuestionsUseCase(gameSettings.maxSumValue)
    }

     fun choseAnswer(number: Int){
       checkAnswer(number)
        updateProgress()
        generateQuestions()
    }

    private fun updateProgress(){
        val percent = calculatePercentOfRightAnswer()
        _percentOfRightAnswer.value = percent
        _progressAnswers.value = String.format(
            context.getString(R.string.right_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswer
        )
        _enoughCount.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswer
        _enoughPercent.value = percent >= gameSettings.minPercentOfRightAnswer
    }

    private fun calculatePercentOfRightAnswer():Int{
        if(countOfQuestions == 0) {
            return 0
        }
        return ((countOfRightAnswers/countOfQuestions.toDouble())*100).toInt()
    }

    private fun checkAnswer(number: Int){
        val rightAnswer = _question.value?.rightAnswer
        if(number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }



    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minute = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minute * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minute, leftSeconds)



    }

    private fun finish() {
        _gameResult.value = GameResult(
            winners = _enoughCount.value == true && _enoughPercent.value == true,
            countOfRightAnswer = countOfRightAnswers,
            countOfQuestions = countOfQuestions ,
            percentOfRightAnswer.value?: 0 ,
            gameSettings = gameSettings
        )

    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60

    }

}