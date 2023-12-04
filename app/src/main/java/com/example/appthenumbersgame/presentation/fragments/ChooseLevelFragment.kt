package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.databinding.FragmentChooseLevelBinding
import com.example.appthenumbersgame.domain.entities.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException ("FragmentLevelBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingButton()

    }


    private fun bindingButton()= with(binding){
        bTest.setOnClickListener{
            launchGameFragment(Level.TEST)
        }
        bEasy.setOnClickListener{
            launchGameFragment(Level.EASY)
        }
        bNormal.setOnClickListener{
            launchGameFragment(Level.NORMAL)
        }
        bHard.setOnClickListener{
           launchGameFragment(Level.HARD)
        }
    }

    private fun launchGameFragment(level:Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

       const val NAME = "ChooseLevelFragment"
        fun newInstance() = ChooseLevelFragment()
    }
}