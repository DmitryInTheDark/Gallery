package com.example.gallery

import android.app.Application
import com.example.data.remote_storage.RetrofitClient
import com.example.data.repository_implementation.PhotoRepositoryImplementation
import com.example.data.repository_implementation.UserRepositoryImplementation
import com.example.domain.repository.UserRepository
import com.example.domain.use_case.DeleteUserUseCase
import com.example.domain.use_case.GetCurrentUserUseCase
import com.example.domain.use_case.GetPhotoByIDUseCase
import com.example.domain.use_case.GetPhotosUseCase
import com.example.domain.use_case.SignInUseCase
import com.example.domain.use_case.SignUpUseCase
import com.example.domain.use_case.UpdateUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideUserRepository(application: Application, retrofitClient: RetrofitClient): UserRepositoryImplementation{
        return UserRepositoryImplementation(retrofitClient, application)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(userRepositoryImplementation: UserRepositoryImplementation): SignInUseCase{
        return SignInUseCase(userRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(userRepositoryImplementation: UserRepositoryImplementation): SignUpUseCase{
        return SignUpUseCase(userRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideGetPhotoUseCase(photoRepositoryImplementation: PhotoRepositoryImplementation): GetPhotosUseCase{
        return GetPhotosUseCase(photoRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(application: Application): RetrofitClient{
        return RetrofitClient(application)
    }

    @Provides
    @Singleton
    fun provideGetPhotoByIDUseCase(photoRepositoryImplementation: PhotoRepositoryImplementation): GetPhotoByIDUseCase{
        return GetPhotoByIDUseCase(photoRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(userRepositoryImplementation: UserRepositoryImplementation): GetCurrentUserUseCase{
        return GetCurrentUserUseCase(userRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideUpdateUserDataUseCase(userRepositoryImplementation: UserRepositoryImplementation): UpdateUserDataUseCase{
        return UpdateUserDataUseCase(userRepositoryImplementation)
    }

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(userRepositoryImplementation: UserRepositoryImplementation): DeleteUserUseCase{
        return DeleteUserUseCase((userRepositoryImplementation))
    }
}