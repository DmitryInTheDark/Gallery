package com.example.data.repository_implementation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data.remote_storage.RetrofitClient
import com.example.data.remote_storage.models.photo.GetPhotoResponse
import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.repository.PhotoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream


class PhotoRepositoryImplementation(context: Context): PhotoRepository {

    private val retrofitClient = RetrofitClient(context)
    private var api = retrofitClient.configureRetrofit()

    override suspend fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): MyResult<List<PhotoModel>> {
        try {
            val response = api.getPhotos(page, isNew, isPopular)
            if (response.isSuccessful){
                val responseBody = response.body()
                return if (responseBody != null) MyResult.Success(getPhotoResponseToPhotoModel(responseBody))
                else MyResult.Error(emptyList())
            }else{
                Log.i("my", response.code().toString())
                Log.i("my", response.message())
                return MyResult.Error(emptyList())
            }
        }catch (e: Exception){
            Log.i("my", e.message ?: "Незнакомая ошибка в getPhotos")
            return MyResult.Error(emptyList())
        }
    }


    private fun getPhotoResponseToPhotoModel(getPhotoResponse: GetPhotoResponse): List<PhotoModel>{
        var isLoading = true
        val photoList = mutableListOf<PhotoModel>()

        CoroutineScope(Dispatchers.IO).launch {
            getPhotoResponse.member.forEach {
                val inputStream = api.getFile(it.file.path).execute().body()?.byteStream()
                if (inputStream != null) {
                    val photoModel = PhotoModel(
                        it.id,
                        inputStream,
                        it.new,
                        it.popular
                    )
                }
            }
            Log.i("my", photoList.joinToString())
            isLoading = false
        }

        while (isLoading){
            true
        }

        return photoList
    }

}