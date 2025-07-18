package com.example.gallery.fragments.registration_fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.data.repository_implementation.UserRepositoryImplementation
import com.example.domain.models.RegisterUserModel
import com.example.domain.models.MyResult
import com.example.domain.use_case.SignUpUseCase
import com.example.gallery.R
import com.example.gallery.databinding.SignUpFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpFragment : Fragment() {

    lateinit var binding: SignUpFragmentBinding

    private val userRepositoryImplementation: UserRepositoryImplementation by lazy {
        UserRepositoryImplementation(requireContext())
    }
    private val signUpUseCase: SignUpUseCase by lazy {
        SignUpUseCase(userRepositoryImplementation)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.birthdayEditText.addTextChangedListener(object : TextWatcher{
            var isEditing = false
            var textArray = mutableListOf<Char>()

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isEditing) return
                isEditing = true
                val digits = s?.filter { it.isDigit() } ?: ""
                val formatted = when {
                    digits.length <= 2 -> digits
                    digits.length <= 4 -> "${digits.substring(0, 2)}.${digits.substring(2)}"
                    else -> "${digits.substring(0, 2)}.${digits.substring(2, 4)}.${digits.substring(4)}"
                }

                binding.birthdayEditText.setText(formatted)
                binding.birthdayEditText.setSelection(formatted.length)
                isEditing = false
            }

        })

        binding.signInButton.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.signUpButton.setOnClickListener{
            val registerUser = RegisterUserModel(
                binding.userNameEditText.text.toString(),
                binding.birthdayEditText.text.toString(),
                binding.phoneNumberEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.confirmPasswordEditText.text.toString()
                )
            CoroutineScope(Dispatchers.IO).launch {
                val result = signUpUseCase.execute(registerUser)
                if (result is MyResult.Success){
                    withContext(Dispatchers.Main){
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                }else if (result is MyResult.Error){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                    }
                    Log.i("my", result.message)
                }
            }
        }
    }
}