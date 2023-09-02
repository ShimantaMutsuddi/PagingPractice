package com.chutyrooms.pagingpractice.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chutyrooms.pagingpractice.paging.CharacterPagingSource
import com.chutyrooms.pagingpractice.retrofit.CharacterService
import javax.inject.Inject

class CharacterRepo @Inject constructor(val characterService: CharacterService) {
    fun getQuotes()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { CharacterPagingSource(characterService) }
    ).liveData
}