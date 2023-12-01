package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.databinding.FragmentGameBinding
import com.example.appthenumbersgame.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {

    private var _binding: FragmentGameFinishBinding? = null
    private val binding : FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException ("FragmentGameFinishBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = GameFinishFragment()
    }
}