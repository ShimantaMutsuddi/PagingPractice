package com.chutyrooms.pagingpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chutyrooms.pagingpractice.databinding.ActivityMainBinding
import com.chutyrooms.pagingpractice.paging.CharacterAdapter
import com.chutyrooms.pagingpractice.paging.LoaderAdapter
import com.chutyrooms.pagingpractice.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CharacterViewModel
    lateinit var adapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= CharacterAdapter()
        viewModel= ViewModelProvider(this)[CharacterViewModel::class.java]

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter=adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter(),
        )

        viewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle,it)
        })



    }
}