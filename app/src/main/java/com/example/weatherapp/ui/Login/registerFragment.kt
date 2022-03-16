package com.example.weatherapp.ui.Login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class registerFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        auth = Firebase.auth
        binding.btnRegisterUser.setOnClickListener {
            singUpUser()
        }
    }

    private fun singUpUser() {
        if (binding.edEmailRegister.text.toString().isEmpty()) {
            binding.edEmailRegister.error = "Porfavor introduce un correo electronico"
            binding.edEmailRegister.requestFocus()
            return
        }
        if (binding.edPasswordRegister.text.toString().isEmpty()) {
            binding.edPasswordRegister.error = "Porfavor introduce una contraseña"
            binding.edPasswordRegister.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edEmailRegister.text.toString()).matches()) {
            binding.edEmailRegister.error = "Porfavor introduce un correo valido"
            return
        }
        auth.createUserWithEmailAndPassword(
            binding.edEmailRegister.text.toString(),
            binding.edPasswordRegister.text.toString()
        ).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, "Usuario creado")
                val user = auth.currentUser
                user!!.sendEmailVerification().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Fallo algo en el registro, intente despues",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}