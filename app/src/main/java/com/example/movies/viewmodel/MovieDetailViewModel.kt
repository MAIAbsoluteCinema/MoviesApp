package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.network.RetrofitInstance8082
import com.example.movies.model.MovieDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchMovieDetail(filmId: Long) {
        val call = RetrofitInstance8082.api.getFilm(filmId)

        call.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    _movieDetail.value = response.body()
                } else {
                    val errorBody = response.errorBody()?.string()
                    _error.value = "Ошибка загрузки фильма: $errorBody"
                    Log.e("MovieDetailViewModel", "Ошибка загрузки фильма: $errorBody")
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                _error.value = "Не удалось выполнить запрос: ${t.message}"
                Log.e("MovieDetailViewModel", "Не удалось выполнить запрос: ${t.message}", t)
            }
        })
    }
}