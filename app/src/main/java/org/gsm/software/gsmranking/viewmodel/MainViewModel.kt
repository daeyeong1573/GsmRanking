package org.gsm.software.gsmranking.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.model.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository : MainRepository): ViewModel() {

    private val _ranking = MutableLiveData<Int>()
            val rannking : LiveData<Int> get() = _ranking

    val items = ObservableArrayList<RankingResultQuery.Ranking>()

    init {
        _ranking.value = 0
    }

    fun getRanking(){
        viewModelScope.launch {
            val response = try {
                 repository.getRanking(0).execute()
               }catch(e: Exception){
                   Log.d(TAG, "getRanking:$e")
                   null
               }
            val data = response?.data?.ranking?.filterNotNull()
            if(data != null && !response.hasErrors()){
                items.addAll(data)
                Log.d(TAG, "getRanking: ${data[1].name}")
            }
        }
    }

}