package com.piksel.cellies.bookstore.module

import android.content.Context
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.BookDao
import com.piksel.cellies.bookstore.BaseApplication
import com.piksel.cellies.bookstore.network.BookStoreDataSource
import com.piksel.cellies.bookstore.network.BookStoreService
import com.piksel.cellies.bookstore.model.repository.BookRepository
import com.piksel.cellies.bookstore.utils.BASE_PATH_BOOKSTOREAPI_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideApplication(@ApplicationContext app: Context): BaseApplication{
//        return app as BaseApplication
//    }

    @Provides
    @Reusable
    internal fun provideRetrofit(): Retrofit =  Retrofit.Builder()
            .baseUrl(BASE_PATH_BOOKSTOREAPI_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideBookStoreService(retrofit: Retrofit): BookStoreService = retrofit.create(BookStoreService::class.java)

    @Singleton
    @Provides
    fun provideBookRemoteDataSource(BookStoreService: BookStoreService) = BookStoreDataSource(BookStoreService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBookDao(db: AppDatabase) = db.bookDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: BookStoreDataSource,
                          localDataSource: BookDao
    ) =
        BookRepository(remoteDataSource, localDataSource)

}