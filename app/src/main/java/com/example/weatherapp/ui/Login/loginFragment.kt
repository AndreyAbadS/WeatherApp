package com.example.weatherapp.ui.Login

import android.content.ContentValues
import android.content.Intent
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
import com.example.weatherapp.databinding.FragmentLoginBinding
import com.example.weatherapp.ui.bodyApp.bodyApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            reload()
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                val intent = Intent(activity, bodyApp::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            } else {
                Toast.makeText(requireContext(), "Porfavor verifica tu correo", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "Autenticacion fallida", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun reload() {
        if (binding.edEmail.text.toString().isEmpty()) {
            binding.edEmail.error = "Porfavor introduce un correo electronico"
            binding.edEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString()).matches()) {
            binding.edEmail.error = "Porfavor introduce un email valido"
            return
        }
        if (binding.edPassword.text.toString().isEmpty()) {
            binding.edPassword.error = "Porfavor introduce una contraseña"
            binding.edPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(
            binding.edEmail.text.toString(),
            binding.edPassword.text.toString()
        )
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Inicio de sesion exitoso")
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }
}