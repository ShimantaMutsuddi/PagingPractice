package com.chutyrooms.pagingpractice.di

import android.content.Context
import androidx.room.Room
import com.chutyrooms.pagingpractice.db.AppDatabase
import com.chutyrooms.pagingpractice.retrofit.CharacterService
import com.chutyrooms.pagingpractice.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun getQuoteApi(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    //database
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "characters")
            .build()
    }

}