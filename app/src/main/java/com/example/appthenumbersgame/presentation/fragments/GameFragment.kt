package com.example.appthenumbersgame.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.data.Parcel.parcelable
import com.example.appthenumbersgame.databinding.FragmentGameBinding
import com.example.appthenumbersgame.domain.entities.GameResult
import com.example.appthenumbersgame.domain.entities.Level
import com.example.appthenumbersgame.presentation.GameViewModel

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var level: Level

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    private val tvAnswers by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvAnswer1)
            add(binding.tvAnswer2)
            add(binding.tvAnswer3)
            add(binding.tvAnswer4)
            add(binding.tvAnswer5)
            add(binding.tvAnswer6)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArg()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModel.startGame(level)
        observeViewModel()
        setClickListenersOnAnswers()

    }


    private fun observeViewModel() {
        gameViewModel.question.observe(viewLifecycleOwner) {
            with(binding) {
                tvSum.text = it.sum.toString()
                tvVisible.text = it.visibleNumber.toString()
                for (i in 0 until  tvAnswers.size){
                    tvAnswers[i].text = it.options[i].toString()
                }
            }
        }
        gameViewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        gameViewModel.percentOfRightAnswer.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it,true)
        }
        gameViewModel.progressAnswer.observe(viewLifecycleOwner) {
            binding.tvProgressAnswers.text = it
        }

        gameViewModel.enoughCount.observe(viewLifecycleOwner){
            binding.tvProgressAnswers.setTextColor(getColorByState(it))
        }
        gameViewModel.enoughPercent.observe(viewLifecycleOwner){
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        gameViewModel.minPercent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress = it
        }
        gameViewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishResult(it)
        }
    }

    private fun setClickListenersOnAnswers(){
        for (onAnswer in tvAnswers){
            onAnswer.setOnClickListener {
                gameViewModel.choseAnswer(onAnswer.text.toString().toInt())
            }
        }
    }

    private fun getColorByState(goodState:Boolean):Int{
        val colorResId = if (goodState){
            android.R.color.holo_green_light
        }else{
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(),colorResId)
    }



    private fun parseArg() {
        requireArguments().parcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }

    }


    private fun launchGameFinishResult(result: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_LEVEL = "LEVEL"
        const val NAME = "GameFragment"


        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}