package com.example.data.repository_implementation

import android.content.Context
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.domain.models.RegisterUserModel
import com.example.domain.repository.PhotoRepository
import com.example.domain.repository.UserRepository
import java.io.InputStream
import androidx.core.content.edit
import com.example.domain.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserRepositoryImplementation(context: Context): UserRepository, PhotoRepository {

    val retrofitClient = RetrofitClient(context)
    var api = retrofitClient.configureRetrofit()
    val sharedPreferences = retrofitClient.sharedPreferences

    override suspend fun signUp(registerUserModel: RegisterUserModel): Result<String> {
            val requestBody = registerUserModelToRegisterUserBody(registerUserModel)
        try {
            val response = api.registerUser(requestBody)
            if (response.isSuccessful){
                val tokenResponse = api.getToken(GetTokenBody(username = requestBody.displayName, password = requestBody.plainPassword))
                sharedPreferences.edit {
                    putString(
                        RetrofitClient.TOKEN_NAME,
                        tokenResponse.body()?.token
                    )
                    putString(
                        RetrofitClient.REFRESH_TOKEN_NAME,
                        tokenResponse.body()?.refresh_token
                    )
                }
                api = retrofitClient.configureRetrofit()

                val data  = response.message()
                return Result.Success(data)
            }else{
                return Result.Error(response.errorBody()!!.string())

            }
        }catch (e: Exception){
            return Result.Error(e.message.toString())
        }
    }

    override fun signIn(): String {
        TODO("Not yet implemented")
    }

    override fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): List<InputStream> {
        TODO("Not yet implemented")
    }

    private fun registerUserModelToRegisterUserBody(registerUserModel: RegisterUserModel): RegisterUserBody{
        return RegisterUserBody(registerUserModel.email, registerUserModel.birthday,
            registerUserModel.userName, registerUserModel.phoneNumber, registerUserModel.password)
    }
}