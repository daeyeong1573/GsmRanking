package org.gsm.software.gsmranking.main

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
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.ActivityMainBinding
import org.gsm.software.gsmranking.databinding.HeaderBinding
import org.gsm.software.gsmranking.login.LoginActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var headB: HeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        setNavigationView()
    }


    //기초 뷰 초기화
    private fun initViews(){
        binding.activity = this@MainActivity
        binding.lifecycleOwner = this@MainActivity
        binding.viewPager.adapter = ViewPagerFragmentAdapter(this)
        val tabTitles = listOf<String>("전체 랭킹","기수별 랭킹")
        TabLayoutMediator(binding.tabLayout,binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    //NavigationView 초기화
    private fun setNavigationView() = with(binding){
        //네비게이션뷰 연결 아래 코드 없을시 메뉴속 아이템 클릭반응 없음
        navigationView.setNavigationItemSelectedListener(this@MainActivity)
        //headerLayout 바인딩 연결
        val headV = binding.navigationView.getHeaderView(0)
        headB = HeaderBinding.bind(headV)
        headB.activity = this@MainActivity
        //메뉴바 그리기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
    }


    //메뉴 버튼 열기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //메뉴속 아이템 클릭 이벤트
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

    fun goLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
        overridePendingTransition(R.anim.left_slide_enter,R.anim.left_slide_exit)
    }


}

