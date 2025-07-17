package com.example.gallery.fragments.GalleryFragments.home_fragments.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.use_case.GetPhotosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    private val _newPhotoLiveData = MutableLiveData<List<PhotoModel>>(emptyList())
    val newPhotoLiveData = _newPhotoLiveData

    private val _popularPhotoLiveData = MutableLiveData<List<PhotoModel>>(emptyList())
    val popularPhotoLiveData = _newPhotoLiveData

    private val currentPage = 1
    private var isLoading = false

    init {
        isLoading = true
        CoroutineScope(Dispatchers.IO).launch{
            val newPhotoResponse = getPhotosUseCase.execute(currentPage, isNew = true, isPopular = false)

            withContext(Dispatchers.Main){
                when(newPhotoResponse){
                    is MyResult.Success -> _newPhotoLiveData.value = newPhotoResponse.data
                    is MyResult.Error -> _newPhotoLiveData.value = emptyList()
                }
            }


            val popularPhotoResponse = getPhotosUseCase.execute(currentPage, isNew = false, isPopular = true)

            withContext(Dispatchers.Main){
                when(popularPhotoResponse){
                    is MyResult.Success -> _popularPhotoLiveData.value = popularPhotoResponse.data
                    is MyResult.Error -> _popularPhotoLiveData.value = emptyList()
                }
            }

            isLoading = false
        }
    }

    fun getNewPhoto(){
        if (isLoading) return
        isLoading = true

        CoroutineScope(Dispatchers.IO).launch {
            val newPhotoResponse = getPhotosUseCase.execute(currentPage, isNew = true, isPopular = false)

            withContext(Dispatchers.IO){
                when(newPhotoResponse){
                    is MyResult.Success -> _newPhotoLiveData.value = newPhotoResponse.data
                    is MyResult.Error -> _newPhotoLiveData.value = emptyList()
                }
            }

            isLoading = false
        }
    }

    fun getPopularPhoto(){
        if (isLoading) return
        isLoading = true

        CoroutineScope(Dispatchers.IO).launch {
            val popularPhotoResponse = getPhotosUseCase.execute(currentPage, isNew = false, isPopular = true)

            withContext(Dispatchers.Main){
                when(popularPhotoResponse){
                    is MyResult.Success -> _newPhotoLiveData.value = popularPhotoResponse.data
                    is MyResult.Error -> _newPhotoLiveData.value = emptyList()
                }
            }

            isLoading = false
        }
    }
}