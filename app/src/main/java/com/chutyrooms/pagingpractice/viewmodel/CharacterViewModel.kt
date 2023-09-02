package com.chutyrooms.pagingpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chutyrooms.pagingpractice.repo.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(val characterRepo: CharacterRepo): ViewModel() {
    val list=characterRepo.getQuotes().cachedIn(viewModelScope)
}