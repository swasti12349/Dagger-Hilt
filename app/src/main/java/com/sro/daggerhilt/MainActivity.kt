package com.sro.daggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sro.daggerhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRV()

        mainViewModel.getPost()

        lifecycleScope.launchWhenCreated {
            mainViewModel._postStateFlow.collect { it ->
                when (it) {

                    is ApiState.loading -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = true
                    }

                    is ApiState.failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }

                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setList(it.data)
                    }

                    is ApiState.Empty -> {}

                }
            }
        }
    }

    private fun initRV() {
        postAdapter = PostAdapter(ArrayList())
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = postAdapter

        }
    }
}