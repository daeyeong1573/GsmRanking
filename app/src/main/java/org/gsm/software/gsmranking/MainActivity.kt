package org.gsm.software.gsmranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.gsm.software.gsmranking.adapter.MainListAdapter
import org.gsm.software.gsmranking.databinding.ActivityMainBinding
import org.gsm.software.gsmranking.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm :MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this@MainActivity
        binding.vm = vm
        binding.lifecycleOwner = this@MainActivity
        setRecyclerView()
        vm.getRanking()

    }
    private fun setRecyclerView(){
        val adapter = MainListAdapter(vm)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)
    }

}

