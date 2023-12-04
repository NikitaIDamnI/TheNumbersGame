package com.example.appthenumbersgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appthenumbersgame.R
import com.example.appthenumbersgame.databinding.FragmentWelcomeBinding
import com.example.appthenumbersgame.presentation.MainActivity

class WelcomeFragment : Fragment() {

    private  var _binding: FragmentWelcomeBinding? = null
    private  val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
       _binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bUnderstand.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    private fun launchChooseLevelFragment(){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,ChooseLevelFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {

        fun newInstance() = WelcomeFragment()
    }
}