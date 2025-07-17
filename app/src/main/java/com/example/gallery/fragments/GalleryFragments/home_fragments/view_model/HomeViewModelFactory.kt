package com.example.gallery.fragments.GalleryFragments.home_fragments.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository_implementation.PhotoRepositoryImplementation
import com.example.domain.use_case.GetPhotosUseCase

class HomeViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val photoRepositoryImplementation = PhotoRepositoryImplementation(context)
    private val getPhotosUseCase = GetPhotosUseCase(photoRepositoryImplementation)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getPhotosUseCase) as T
    }
}