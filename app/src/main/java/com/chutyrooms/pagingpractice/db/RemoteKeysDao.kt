package com.chutyrooms.pagingpractice.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chutyrooms.pagingpractice.model.CharacterRemoteKeys
import com.example.rickandmorty.data.entities.Character

interface RemoteKeysDao {
    @Query("SELECT * FROM CharacterRemoteKeys WHERE id=:id")
    suspend fun getRemoteKeys(id: Int) : CharacterRemoteKeys

    /*Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(characters: List<CharacterRemoteKeys>)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)*/
}