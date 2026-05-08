package com.example.letssopt.feature.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.database.MovieDatabase
import com.example.letssopt.core.data.database.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()

    val libraryList: StateFlow<List<MovieEntity>> = movieDao.getAllMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun deleteMovie(movie: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.deleteMovie(movie)
            _toastMessage.emit("${movie.title}이(가) 보관함에서 삭제되었습니다.")
        }
    }
}