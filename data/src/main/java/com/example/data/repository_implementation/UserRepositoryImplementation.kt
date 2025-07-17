package com.example.data.repository_implementation

import android.content.Context
import android.util.Log
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.domain.models.RegisterUserModel
import com.example.domain.repository.PhotoRepository
import com.example.domain.repository.UserRepository
import java.io.InputStream
import androidx.core.content.edit
import com.example.data.remote_storage.models.ErrorBodyResponse
import com.example.domain.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class UserRepositoryImplementation(context: Context): UserRepository, PhotoRepository {

    private val retrofitClient = RetrofitClient(context)
    private var api = retrofitClient.configureRetrofit()
    private val sharedPreferences = retrofitClient.sharedPreferences

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
                val error = response.errorBody()?.string() ?: "Неизвестная ошибка"
                val errorDescription = try {
                    Json.decodeFromString<ErrorBodyResponse>(error).description
                }catch (e: Exception){
                    Log.i("my", e.message.toString())
                    "Неизвестная ошибка"
                }
                return Result.Error(errorDescription)
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