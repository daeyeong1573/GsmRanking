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

    var ranking = mutableListOf<String>()

    private val _items = MutableLiveData<List<RankingResultQuery.Ranking>>()
            val items : LiveData<List<RankingResultQuery.Ranking>> get() = _items

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
                _items.value = data
                Log.d(TAG, "getRanking: ${data[0].contributions}")
            }
        }
    }

}