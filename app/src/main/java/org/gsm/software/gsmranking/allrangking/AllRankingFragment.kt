package org.gsm.software.gsmranking.allrangking

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.AllRankingFragmentBinding
import org.gsm.software.gsmranking.adapter.RankingListAdapter
import org.gsm.software.gsmranking.viewmodel.RankingViewModel

@AndroidEntryPoint
class AllRankingFragment : Fragment() {

    private val binding by lazy { AllRankingFragmentBinding.inflate(layoutInflater) }
    private val vm: RankingViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        setRecyclerView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onAllRankingCreated: ${vm.criteria.value}" )
        vm.getRanking()
    }

    private fun initView(){
        binding.vm = vm
        binding.lifecycleOwner = this@AllRankingFragment
    }

    //RecyclerView 초기화
    private fun setRecyclerView() {
        val adapter = RankingListAdapter( this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)
    }

}