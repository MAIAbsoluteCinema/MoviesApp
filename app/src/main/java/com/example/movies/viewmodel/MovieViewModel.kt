package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.network.RetrofitInstance8082
import com.example.movies.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchMovies(numberOfFilms: Int) {
        val call = RetrofitInstance8082.api.getFilms(numberOfFilms)

        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    _movies.value = response.body()
                } else {
                    Log.e(
                        "MovieViewModel",
                        "Ошибка загрузки фильмов: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.e("MovieViewModel", "Не удалось выполнить запрос: ${t.message}", t)
            }
        })
    }
}