package com.example.data.repository_implementation

import android.content.Context
import android.util.Log
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.domain.models.RegisterUserModel
import com.example.domain.repository.PhotoRepository
import com.example.domain.repository.UserRepository
import androidx.core.content.edit
import com.example.data.remote_storage.models.ErrorBodyResponse
import com.example.data.remote_storage.models.photo.GetPhotoResponse
import com.example.domain.models.PhotoModel
import com.example.domain.models.MyResult
import com.example.domain.models.SignInUserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class UserRepositoryImplementation(context: Context): UserRepository {

    private val retrofitClient = RetrofitClient(context)
    private var api = retrofitClient.configureRetrofit()
    private val sharedPreferences = context.getSharedPreferences(RetrofitClient.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    override suspend fun signUp(registerUserModel: RegisterUserModel): MyResult<String> {
            val requestBody = registerUserModelToRegisterUserBody(registerUserModel)
        try {
            val response = api.registerUser(requestBody)
            if (response.isSuccessful){

                api = retrofitClient.configureRetrofit()

                val data  = response.message()
                return MyResult.Success(data)
            }else{
                val error = response.errorBody()?.string() ?: "Неизвестная ошибка"
                val errorDescription = try {
                    val json = Json{ ignoreUnknownKeys = true }
                    json.decodeFromString<ErrorBodyResponse>(error).description
                }catch (e: Exception){
                    Log.i("my", e.message.toString())
                    "Неизвестная ошибка"
                }
                return MyResult.Error(errorDescription)
            }
        }catch (e: Exception){
            return MyResult.Error(e.message.toString())
        }
    }

    override suspend fun signIn(signInUserModel: SignInUserModel): MyResult<String>{
        try {
            val tokenResponse = api.getToken(signInUserModelToGetTokenBody(signInUserModel))
            val tokenBody = tokenResponse.body()
            if (tokenResponse.isSuccessful){
                Log.i("sh", sharedPreferences.getString(RetrofitClient.TOKEN_NAME, "No") ?: "Ничего")
                if (tokenBody != null){
                    sharedPreferences.edit(commit = true){
                        putString(RetrofitClient.TOKEN_NAME, tokenBody.token)
                        putString(RetrofitClient.REFRESH_TOKEN_NAME, tokenBody.refreshToken)
                    }
                    Log.i("sh", sharedPreferences.getString(RetrofitClient.TOKEN_NAME, "No") ?: "Ничего")
                }
                api = retrofitClient.configureRetrofit()
                return MyResult.Success(tokenResponse.code().toString())
            }else {
                return MyResult.Error(tokenResponse.errorBody()?.string() ?: "Неизвестная ошибка")
            }
        }catch (e: Exception){
            return MyResult.Error(e.message ?: "Неизвестная ошибка")
        }
    }



    private fun registerUserModelToRegisterUserBody(registerUserModel: RegisterUserModel): RegisterUserBody{
        return RegisterUserBody(registerUserModel.email, registerUserModel.birthday,
            registerUserModel.userName, registerUserModel.phoneNumber, registerUserModel.password)
    }

    private fun signInUserModelToGetTokenBody(signInUserModel: SignInUserModel): GetTokenBody{
        return GetTokenBody(
            grantType = "password",
            username = signInUserModel.email,
            password = signInUserModel.password,
            clientID = "123",
            clientSecret = "123"
        )
    }

}