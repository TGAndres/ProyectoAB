package com.example.proyectoab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyectoab.LoginFragment.Companion.usersList
import com.example.proyectoab.databinding.PasswordFragmentBinding

class PasswordFragment : Fragment(){

    private var _binding : PasswordFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var username : String
    var user_position : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PasswordFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersNames = mutableListOf<String>()
        for (user in usersList) {
            usersNames.add(user.name)
        }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, usersNames)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                username = usersNames[position]
                user_position = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.confirm.setOnClickListener {
            val password = binding.password.editText?.text.toString()
            val cpassword = binding.confirmPassword.editText?.text.toString()

            if (password == cpassword) {
                usersList.removeAt(user_position)
                usersList.add(User(username, password))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment())
                    .commit()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Passwords don't match", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}