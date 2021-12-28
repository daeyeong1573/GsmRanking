package org.gsm.software.gsmranking.generation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.adapter.RankingListAdapter
import org.gsm.software.gsmranking.databinding.GenerationRankingFragmentBinding
import org.gsm.software.gsmranking.viewmodel.RankingViewModel

@AndroidEntryPoint
class GenerationRankingFragment : Fragment() {

    lateinit var binding: GenerationRankingFragmentBinding
    private val vm: RankingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.generation_ranking_fragment,
            container,
            false
        )
        binding.vm = vm
        binding.fragment = this
        binding.lifecycleOwner = this
        setRecyclerView()
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        binding.getGeneration.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                handled = true
                if (binding.getGeneration.text.isNotEmpty()) {
                    vm.getGeneration(Integer.parseInt(binding.getGeneration.text.toString()))
                    vm.getRanking()
                } else {
                    Toast.makeText(context, "기수를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            handled
        }
    }

    //RecyclerView 초기화
    private fun setRecyclerView() {
        val adapter = RankingListAdapter(vm, this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)
    }


}