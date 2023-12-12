package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appthenumbersgame.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {

    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding == null")

    private val args by navArgs<GameFinishFragmentArgs>()

    private val gameResult by lazy {
        args.gameResult
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dataGameResult = gameResult
        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        binding.bRetry.setOnClickListener { retryGame() }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}