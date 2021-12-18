package org.gsm.software.gsmranking

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.gsm.software.gsmranking.adapter.MainListAdapter
import org.gsm.software.gsmranking.databinding.ActivityMainBinding
import org.gsm.software.gsmranking.util.showVertical
import org.gsm.software.gsmranking.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm :MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this@MainActivity
        binding.vm = vm
        binding.lifecycleOwner = this@MainActivity
        setRecyclerView()
        setDrawerNav()
        vm.getRanking()

    }
    private fun setRecyclerView(){
        val adapter = MainListAdapter(vm)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.showVertical(this@MainActivity)
    }

    private fun setDrawerNav() = with(binding){
        //네비게이션뷰 연결 아래 코드 없을시 메뉴속 아이템 클릭반응 없음
        navigationView.setNavigationItemSelectedListener(this@MainActivity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
    }




    //메뉴 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: 불러오기")
        when (item.itemId) {
            R.id.addGit -> {
                Log.d(TAG, "onNavigationItemSelected: addGit 클릭")
                Toast.makeText(this, "테스트", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/login/oauth/authorize?client_id=685ffb52e4dd768b3f66&redirect_uri=https://d6ui2fy5uj.execute-api.ap-northeast-2.amazonaws.com/api/auth&scope=user:email")
                )
                startActivity(intent)
            }
        }
        return false
    }

}

