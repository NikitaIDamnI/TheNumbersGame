package com.example.appthenumbersgame.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.databinding.FragmentGameBinding
import com.example.appthenumbersgame.domain.entities.GameResult
import com.example.appthenumbersgame.domain.entities.GameSettings
import com.example.appthenumbersgame.domain.entities.Level
import java.io.Serializable

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException ("FragmentGameBinding == null")

    private lateinit var level: Level



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArg()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSum.setOnClickListener {
            launchGameFinishResult()
        }
    }


    private fun  parseArg(){
        level = requireArguments().serializable<Level>(KEY_LEVEL)!!

    }
    private inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

    private fun launchGameFinishResult(){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFinishFragment.newInstance(GameResult(false,12,32,
                GameSettings(21,32,43,65)
            )))
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_LEVEL = "LEVEL"
        const val NAME= "GameFragment"


        fun newInstance(level: Level):GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,level)
                }
            }
        }
    }
}