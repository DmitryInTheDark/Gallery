package com.example.data.repository_implementation

import android.content.Context
import android.util.Log
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.domain.models.RegisterUserModel
import com.example.domain.repository.UserRepository
import androidx.core.content.edit
import com.example.data.remote_storage.models.ErrorBodyResponse
import com.example.data.remote_storage.models.token.RefreshTokenBody
import com.example.data.remote_storage.models.user.UpdateUserBody
import com.example.domain.models.CurrentUserModel
import com.example.domain.models.MyResult
import com.example.domain.models.SignInUserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val retrofitClient: RetrofitClient,
    @ApplicationContext context: Context
): UserRepository {

    private var api = retrofitClient.configureRetrofit()
    private val registerApi = retrofitClient.configureRetrofitForRegistration()
    private val sharedPreferences = context.getSharedPreferences(RetrofitClient.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object{
        const val USERNAME_KEY = "Username"
        const val PASSWORD = "Password"
    }


    override suspend fun signUp(registerUserModel: RegisterUserModel): MyResult<String> {
            val requestBody = registerUserModelToRegisterUserBody(registerUserModel)
        try {
            val response = registerApi.registerUser(requestBody)
            if (response.isSuccessful){

                api = retrofitClient.configureRetrofitForAuthorization()

                val data = response.message()
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
                if (tokenBody != null){
                    sharedPreferences.edit(commit = true){
                        putString(RetrofitClient.TOKEN_NAME, tokenBody.token)
                        putString(RetrofitClient.REFRESH_TOKEN_NAME, tokenBody.refreshToken)
                        putString(USERNAME_KEY, signInUserModel.email)
                        putString(PASSWORD, signInUserModel.password)
                    }
                    Log.i("sh", sharedPreferences.getString(USERNAME_KEY, "No") ?: "Ничего")
                    Log.i("sh", sharedPreferences.getString(PASSWORD, "No") ?: "Ничего")
                }
                return MyResult.Success(tokenResponse.code().toString())
            }else {
                return MyResult.Error(tokenResponse.errorBody()?.string() ?: "Неизвестная ошибка")
            }
        }catch (e: Exception){
            return MyResult.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getCurrentUser(): MyResult<CurrentUserModel> {
        var response = api.getCurrentUser()
        if(!authorize(response.code())) return MyResult.Error(
            CurrentUserModel(
            "", "", "", "", 0, "")
        )else response = api.getCurrentUser()
        val body = response.body()
        if (response.isSuccessful && body != null){
            return MyResult.Success(
                CurrentUserModel(
                    body.email,
                    body.birthday,
                    body.displayName,
                    body.phone,
                    body.id,
                    body.dateCreate
                    )
            )
        }else return MyResult.Error(
            CurrentUserModel("", "", "", "", 0, "")
        )
    }

    override suspend fun updateUser(
        username: String,
        birthday: String,
        phone: String,
        email: String,
        id: String,
        oldPassword: String,
        newPassword: String
    ): Boolean {
        val response = api.updateUser(
            id,
            UpdateUserBody(
                email, oldPassword, birthday, username, phone, newPassword
            )
        )
        if(!authorize(response.code())) return false
        return response.isSuccessful
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

    private suspend fun authorize(code: Int): Boolean{
        if (code == 401){
            val refresh = api.refreshToken(
                RefreshTokenBody(
                    grantType = "refresh_token",
                    refreshToken = sharedPreferences.getString(RetrofitClient.REFRESH_TOKEN_NAME, "") ?: "",
                    clientID = "123",
                    clientSecret = "123"
                )
            )
            if (refresh.isSuccessful){
                sharedPreferences.edit(commit = true){
                    putString(RetrofitClient.TOKEN_NAME, refresh.body()!!.token)
                    putString(RetrofitClient.REFRESH_TOKEN_NAME, refresh.body()!!.refreshToken)
                }
                api = retrofitClient.configureRetrofit()
            }
            return true
        }else if (code in 200..300) return true else return false
    }

}