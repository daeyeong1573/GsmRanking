package org.gsm.software.gsmranking.allrangking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.AllRankingFragmentBinding
import org.gsm.software.gsmranking.adapter.RankingListAdapter
import org.gsm.software.gsmranking.viewmodel.RankingViewModel

@AndroidEntryPoint
class AllRankingFragment : Fragment() {

    lateinit var binding : AllRankingFragmentBinding
    private val vm : RankingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.all_ranking_fragment,container,false)
        binding.vm = vm
        binding.lifecycleOwner = this
        setRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.getRanking()
    }

    //RecyclerView 초기화
    private fun setRecyclerView(){
        val adapter = RankingListAdapter(vm, this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)
    }

}