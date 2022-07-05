package com.example.proyectoab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyectoab.LoginFragment.Companion.usersList
import com.example.proyectoab.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    private var _binding : RegisterFragmentBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {
            val username = binding.username.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            val cpassword = binding.confirmPassword.editText?.text.toString()
            val usersNames = mutableListOf<String>()
            for (user in usersList) {
                usersNames.add(user.name)
            }

            if(username in usersNames){
                Toast.makeText(requireContext(), "Name already used", Toast.LENGTH_SHORT)
                    .show()
            }else{
                if(password == cpassword){
                    usersList.add(User(username,password))
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,LoginFragment())
                        .commit()
                }else{
                    Toast.makeText(requireContext(),
                        "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}