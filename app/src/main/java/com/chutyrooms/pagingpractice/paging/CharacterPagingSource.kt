package com.chutyrooms.pagingpractice.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chutyrooms.pagingpractice.retrofit.CharacterService
import com.example.rickandmorty.data.entities.Character

class CharacterPagingSource(val characterService: CharacterService): PagingSource<Int,Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // load PagingSource er help kore jodi page harai fele
        //ekane kiso logic ;ikha hoy jar basis judge kora jai next kon page
        // amder ekta anchor position aase jar basis e amra deside kori amader next  page
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)

        }
        /*if (state.anchorPosition !=null){
            val anchorPage=state.closestPageToPosition(state.anchorPosition!!)
            if(anchorPage?.prevKey!=null)
            {
                return anchorPage.prevKey!!.plus(1)
            }
            else if(anchorPage?.nextKey!=null)
            {
                return anchorPage.nextKey!!.minus(1)
            }
        }
        else
        {
            return null
        }*/

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        // page load korar jnn logic likhbo
        return try {
            val position = params.key ?: 1
            val response=characterService.getAllCharacters(position)
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if(position==1) null else position-1,
                nextKey = if(position==response.body()?.info?.pages) null else position+1

            )

        }catch (e:Exception){
            LoadResult.Error(e)

        }

    }
}