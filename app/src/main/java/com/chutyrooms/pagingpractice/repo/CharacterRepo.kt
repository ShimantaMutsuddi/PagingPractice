package com.chutyrooms.pagingpractice.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chutyrooms.pagingpractice.db.AppDatabase
import com.chutyrooms.pagingpractice.paging.CharacterPagingSource
import com.chutyrooms.pagingpractice.paging.CharacterRemoteMediator
import com.chutyrooms.pagingpractice.retrofit.CharacterService
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRepo @Inject constructor(
    private val characterService: CharacterService,
    private val appDatabase: AppDatabase
    ) {


    fun getCharacters()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = CharacterRemoteMediator(characterService,appDatabase),
        pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }

    ).liveData
    fun getCharacter()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = CharacterRemoteMediator(characterService,appDatabase),
        pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }

    ).liveData
}