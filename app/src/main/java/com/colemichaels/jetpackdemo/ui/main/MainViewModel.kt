package com.colemichaels.jetpackdemo.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colemichaels.jetpackdemo.api.RetrofitInstance
import com.colemichaels.jetpackdemo.api.SafeApiCaller
import com.colemichaels.jetpackdemo.api.jsonParser.MyMoshi
import com.colemichaels.jetpackdemo.api.response.ResponseWrapper
import com.colemichaels.jetpackdemo.data.Repository
import com.colemichaels.jetpackdemo.utilities.coroutines.launchIdling
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val retrofit = RetrofitInstance().api
    private val dataRepo = Repository(retrofit, Dispatchers.IO, SafeApiCaller(MyMoshi.moshi))
    var showDataFetched = MutableLiveData(View.INVISIBLE)

    fun getGames() {
        viewModelScope.launchIdling {
            when (val response = dataRepo.getGames("${FILTER_PREFIX}mario", 10)) {
                is ResponseWrapper.NetworkError -> Log.i("TEST", "Network error")
                is ResponseWrapper.GenericError -> Log.i("TEST", "Generic error")
                is ResponseWrapper.Success -> {
                    Log.i("TEST", "Success ${response.value}")
                    showDataFetched.value = View.VISIBLE
                }
            }
        }
    }

    companion object {
        private const val FILTER_PREFIX = "name:"
    }
}
