package org.gsm.software.gsmranking.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.model.repository.RankingRepository
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(val repository: RankingRepository) : ViewModel() {

    var ranking = mutableListOf<String>()

    private val _items = MutableLiveData<List<RankingResultQuery.Ranking>>()
    val items: LiveData<List<RankingResultQuery.Ranking>> get() = _items

    private val _generation = MutableLiveData<Int>()
    val generation: LiveData<Int> get() = _generation

    private val _found = MutableLiveData<Boolean>()
    val found: LiveData<Boolean> get() = _found

    private val _recyclerFound = MutableLiveData<Boolean>()
    val recyclerFound: LiveData<Boolean> get() = _recyclerFound

    private val _notFound = MutableLiveData<Boolean>()
    val notFound: LiveData<Boolean> get() = _notFound

    private val _notFoundMessage = MutableLiveData<String>()
    val notFoundMessage: LiveData<String> get() = _notFoundMessage


    init {
        _generation.value = 0
        _found.value = true
        _notFound.value = false
        _notFoundMessage.value = ""
        _recyclerFound.value = true
    }

    fun getRanking() {
        viewModelScope.launch {
            val response = try {
                generation.value?.let { repository.getRanking(it).execute() }
            } catch (e: Exception) {
                Log.d(TAG, "getRanking:$e")
                null
            }

            val data = response?.data?.ranking?.filterNotNull()

            if (data != null && !response.hasErrors()) {
                //?????? ????????? ??????
                _found.value = false
                //data?????? null??? ????????? ??????
                if (data.isNotEmpty()) {
                    _items.value = data!!
                    _recyclerFound.value = false
                    Log.d(TAG, "getRanking: ${data.size}")
                } else {
                    //???????????? ????????? ????????? ?????? ??????
                    _notFound.value = true
                    _notFoundMessage.value = "?????? ????????? ????????????."
                    _recyclerFound.value = true
                    Log.d(TAG, "getRanking: ????????? ????????????.")
                }

            }
        }
    }

    //?????? ???????????? ??????
    fun getGeneration(gen: Int) {
        _generation.value = gen
    }

}