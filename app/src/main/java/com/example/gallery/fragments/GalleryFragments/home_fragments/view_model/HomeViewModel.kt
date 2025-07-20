package com.example.gallery.fragments.GalleryFragments.home_fragments.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.models.SinglePhotoModel
import com.example.domain.use_case.GetPhotoByIDUseCase
import com.example.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getPhotoByIDUseCase: GetPhotoByIDUseCase
): ViewModel() {

    private val _newPhotoLiveData = MutableLiveData<List<PhotoModel>>(emptyList())
    val newPhotoLiveData = _newPhotoLiveData

    private val _popularPhotoLiveData = MutableLiveData<List<PhotoModel>>(emptyList())
    val popularPhotoLiveData = _popularPhotoLiveData

    private val _currentPhotoLiveData = MutableLiveData<SinglePhotoModel>()
    val currentPhotoLiveData = _currentPhotoLiveData

    private val currentPageNew = 1
    private val currentPagePopular = 1
    private var isLoading = false

    init {
        isLoading = true
        CoroutineScope(Dispatchers.IO).launch{
            val newPhotoResponse = getPhotosUseCase.execute(currentPageNew, isNew = true, isPopular = false)
            val popularPhotoResponse = getPhotosUseCase.execute(currentPagePopular, isNew =  false, isPopular =  true)

            withContext(Dispatchers.Main){
                when(newPhotoResponse){
                    is MyResult.Success -> _newPhotoLiveData.value = newPhotoResponse.data
                    is MyResult.Error -> _newPhotoLiveData.value = emptyList()
                }
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
            val newPhotoResponse = getPhotosUseCase.execute(currentPageNew, isNew = true, isPopular = false)

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

        CoroutineScope(Dispatchers.IO).launch {
            if (isLoading) return@launch
            val popularPhotoResponse = getPhotosUseCase.execute(currentPageNew, isNew = false, isPopular = true)

            withContext(Dispatchers.Main){
                when(popularPhotoResponse){
                    is MyResult.Success -> {
                        _popularPhotoLiveData.value = popularPhotoResponse.data
                    }
                    is MyResult.Error -> {
                        _popularPhotoLiveData.value = emptyList()
                    }

                }
            }

            isLoading = false
        }
    }

}