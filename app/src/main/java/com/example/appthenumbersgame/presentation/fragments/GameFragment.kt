package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appthenumbersgame.databinding.FragmentGameBinding
import com.example.appthenumbersgame.domain.entities.GameResult
import com.example.appthenumbersgame.presentation.viewModel.GameViewModel
import com.example.appthenumbersgame.presentation.viewModel.GameViewModelFactory

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application, args.level)
    }

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dataGameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
    }


    private fun observeViewModel() {

        gameViewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishResult(it)
        }
    }


    private fun launchGameFinishResult(result: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishFragment(result)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}