package com.example.gallery.fragments.GalleryFragments.profile_fragments.view_model

import android.util.Log
import androidx.collection.intSetOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository_implementation.UserRepositoryImplementation
import com.example.domain.models.CurrentUserModel
import com.example.domain.models.MyResult
import com.example.domain.use_case.GetCurrentUserUseCase
import com.example.domain.use_case.UpdateUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepositoryImplementation: UserRepositoryImplementation,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase
): ViewModel() {

    private val _currentUserLiveData = MutableLiveData<CurrentUserModel>()
    val currentUserLiveData = _currentUserLiveData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getCurrentUserUseCase.execute()
            Log.i("result viewModel", "${result is MyResult.Success}")
            when(result){
                is MyResult.Success -> {
                    _currentUserLiveData.postValue(result.data)
                }
                is MyResult.Error -> {
                    Log.e("profileViewModel", "Error")
                }
            }
        }
    }

    fun updateUser(
        username: String,
        birthday: String,
        phone: String,
        email: String,
        oldPassword: String,
        newPassword: String,
        id: String
    ): Boolean{
        var result: Boolean? = null
        CoroutineScope(Dispatchers.IO).launch {
            if(updateUserDataUseCase.execute(
                username, birthday, phone, email, id, oldPassword, newPassword
            )){
                when(val response = getCurrentUserUseCase.execute()){
                    is MyResult.Success -> {
                        _currentUserLiveData.postValue(response.data)
                        result = true
                    }
                    is MyResult.Error -> {
                        result = false
                    }
                }
            }
        }
        while (result == null){
            true
        }
        return result == true
    }
}