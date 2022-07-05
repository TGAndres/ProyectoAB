package com.example.proyectoab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyectoab.LoginFragment.Companion.login_user
import com.example.proyectoab.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(){

    private var _binding : ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = login_user
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}