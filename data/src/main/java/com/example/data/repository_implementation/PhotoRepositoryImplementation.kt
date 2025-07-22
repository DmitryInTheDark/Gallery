package com.example.data.repository_implementation

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.content.edit
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.photo.GetPhotoResponse
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.data.remote_storage.models.token.RefreshTokenBody
import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.models.SinglePhotoModel
import com.example.domain.repository.PhotoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject


class PhotoRepositoryImplementation @Inject constructor(
    private val retrofitClient: RetrofitClient,
    @ApplicationContext context: Context
): PhotoRepository {

    private var api = retrofitClient.configureRetrofit()
    private val sharedPreferences = context.getSharedPreferences(RetrofitClient.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): MyResult<List<PhotoModel>> {
        try {
            val response = api.getPhotos(page, isNew, isPopular)

            if (!authorize(response.code())) return MyResult.Error(emptyList())

            if (response.isSuccessful){
                val responseBody = response.body()
                return if (responseBody != null) MyResult.Success(getPhotoResponseToPhotoModel(responseBody))
                else {
                    Log.i("photoResponse1", "1")
                    MyResult.Error(emptyList())
                }
            }else{
                Log.i("photoResponse2", "2")
                return MyResult.Error(emptyList())
            }
        }catch (e: Exception){
            Log.i("photoResponse3", e.message.toString())
            return MyResult.Error(emptyList())
        }
    }

    override suspend fun getPhotoByID(id: String): MyResult<SinglePhotoModel> {
        return try {
            val response = api.getPhotoByID(id)
            val responseBody = response.body()

            if (!authorize(response.code())) return MyResult.Error(
                SinglePhotoModel(null, "", "", false, false, "", "")
            )

            if (response.isSuccessful && responseBody != null) {
                val inputStream = api.getFile(responseBody.file.path).body()?.byteStream()
                val photoModel = SinglePhotoModel(
                    inputStream,
                    responseBody.description,
                    responseBody.name,
                    responseBody.new,
                    responseBody.popular,
                    responseBody.name,
                    responseBody.dateCreate
                )
                MyResult.Success(photoModel)
            } else {
                MyResult.Error(
                    SinglePhotoModel(null, "", "", false, false, "", "")
                )
            }
        } catch (e: Exception) {
            Log.e("PhotoRepo", "Error in getPhotoByID: ${e.message}")
            MyResult.Error(
                SinglePhotoModel(null, "", "", false, false, "", "")
            )
        }
    }

    override suspend fun updatePhotoByID(
        name: String,
        description: String,
        isNew: Boolean,
        isPopular: Boolean,
        id: String
    ): MyResult<SinglePhotoModel> {
//        try {
//            val result = api.u
//        }
        TODO()
    }


    private suspend fun getPhotoResponseToPhotoModel(getPhotoResponse: GetPhotoResponse): List<PhotoModel> {
        val photoList = mutableListOf<PhotoModel>()

        for (photo in getPhotoResponse.member) {
            val inputStream = api.getFile(photo.file.path).body()?.byteStream()
            if (inputStream != null) {
                val photoModel = PhotoModel(
                    photo.id,
                    photo.file.path,
                    inputStream,
                    photo.new,
                    photo.popular
                )
                photoList.add(photoModel)
            }
        }

        return photoList
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