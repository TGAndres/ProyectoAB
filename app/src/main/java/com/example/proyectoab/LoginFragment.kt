package com.example.proyectoab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyectoab.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment())
                .commit()
        }
        binding.forgotPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PasswordFragment())
                .commit()
        }
        binding.login.setOnClickListener {
            val usersNames = mutableListOf<String>()
            for (user in usersList) {
                usersNames.add(user.name)
            }
            val username = binding.username.editText?.text.toString()
            val password = binding.password.editText?.text.toString()

            val user = User(username,password)

            if(user in usersList){
                login_user = user.name
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ProfileFragment())
                    .commit()
            }else{
                Toast.makeText(requireContext(), "wrong username and/or password",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val usersList = mutableListOf<User>(
            User("Andres", "123"),
            User("Servando", "123"),
            User("Gerardo", "123"),
            User("Emanuel", "123"),
            User("Carmen", "123")
        )

        var login_user = ""
    }
}