package com.example.gallery.fragments.registration_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.data.repository_implementation.UserRepositoryImplementation
import com.example.domain.models.MyResult
import com.example.domain.models.SignInUserModel
import com.example.domain.use_case.SignInUseCase
import com.example.domain.use_case.SignUpUseCase
import com.example.gallery.R
import com.example.gallery.databinding.SignInFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    lateinit var binding: SignInFragmentBinding
    @Inject
    lateinit var userRepositoryImplementation: UserRepositoryImplementation

    private val signInUseCase: SignInUseCase by lazy {
        SignInUseCase(userRepositoryImplementation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.backSignInButton.setOnClickListener {
            navController.navigateUp()
        }

        binding.SignInButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val result: MyResult<String> = signInUseCase.execute( SignInUserModel(
                    binding.editEmailSignIn.text.toString(),
                    binding.passwordSignInEditText.text.toString()
                )
                )
                when(result){
                    is MyResult.Success -> withContext(Dispatchers.Main){navController.navigate(R.id.action_signInFragment_to_mainFragment)}
                    is MyResult.Error -> withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.SignUpButton.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}