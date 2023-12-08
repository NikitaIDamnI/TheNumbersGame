package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.data.Parcel.parcelable
import com.example.appthenumbersgame.databinding.FragmentGameFinishBinding
import com.example.appthenumbersgame.domain.entities.GameResult

class GameFinishFragment : Fragment() {

    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding == null")

    private lateinit var gameResult: GameResult


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArg()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingGameResult()
        onBackPressedDispatcher()
    }

    private fun bindingGameResult() {
        with(binding) {
            if (gameResult.winners) {
                imageResult.setImageResource(R.drawable.image_win)
            }else{
                imageResult.setImageResource(R.drawable.image_lose)
            }

            val tvRequiredScoreForm = String.format(
                tvRequiredScore.text.toString(),
                gameResult.gameSettings.minCountOfRightAnswer.toString()
            )
            val tvScoreAnswersForm =
                String.format(tvScoreAnswers.text.toString(), gameResult.countOfRightAnswer)
            val tvRequiredPercentageForm = String.format(
                tvRequiredPercentage.text.toString(),
                gameResult.gameSettings.minPercentOfRightAnswer
            )
            val tvScorePercentageForm =
                String.format(tvScorePercentage.text.toString(), gameResult.countOfRightAnswer)
            tvRequiredScore.text = tvRequiredScoreForm
            tvScoreAnswers.text = tvScoreAnswersForm
            tvRequiredPercentage.text = tvRequiredPercentageForm
            tvScorePercentage.text = tvScorePercentageForm

            bRetry.setOnClickListener {retryGame()}
        }
    }

    private fun onBackPressedDispatcher(){
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
    }


    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun parseArg() {
        requireArguments().parcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult= it
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





    companion object {
        private const val KEY_GAME_RESULT = "GameResult"
        fun newInstance(gameResult: GameResult): GameFinishFragment {
            return GameFinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }

        }

    }
}