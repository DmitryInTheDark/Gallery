package com.example.gallery

import com.example.gallery.fragments.GalleryFragments.recycler_adapters.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//interface ApiInterface {
//
//    @GET("photos")
//    suspend fun getNewPhotos(@Query("page") page: Int = 1,
//                             @Query("itemsPerPage") itemsPerPage: Int = 30,
//                             @Query("order[id]") orderById: String? = null,
//                             @Query("new") new: Boolean = true,
//                             @Query("popular") popular: Boolean = false
//    ): Response<Photo>
//}