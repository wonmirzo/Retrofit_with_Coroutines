package com.wonmirzo.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonmirzo.retrofit.model.Post
import com.wonmirzo.retrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPosts2: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPost(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getCustomPost(userId, sort, order)
            myCustomPosts.value = response
        }
    }

    fun getCustomPost2(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId, options)
            myCustomPosts2.value = response
        }
    }
}