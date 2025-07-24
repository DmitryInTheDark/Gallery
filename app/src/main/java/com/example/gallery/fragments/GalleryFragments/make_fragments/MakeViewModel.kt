package com.example.gallery.fragments.GalleryFragments.make_fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MyResult
import com.example.domain.use_case.GetPhotosUseCase
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    private val _photoList = MutableLiveData<List<PhotoItem>>()
    val photoList = _photoList

    private var isLoading = false
    private val currentPage = 1

    init {
        getNewPhoto()
    }

    fun getNewPhoto(){
        if (isLoading) return
        isLoading = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = getPhotosUseCase.execute(page = currentPage, isNew = true, isPopular = true)
            when(result){
                is MyResult.Success -> {
                    val newPhoto = mutableListOf<PhotoItem>()
                    result.data.forEach {
                        val bitmap = BitmapFactory.decodeStream(it.image)
                        val photoItem = PhotoItem(bitmap)
                        newPhoto.add(photoItem)
                    }
                    _photoList.postValue(newPhoto)
                    isLoading = false
                }
                is MyResult.Error -> {
                    Log.i("Ошибка в MakeViewModel", "Пришла ошибка")
                    isLoading = false
                }
            }
        }
    }
}