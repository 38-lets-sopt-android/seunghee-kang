package com.example.letssopt.feature.catagory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.database.MovieDatabase
import com.example.letssopt.core.data.database.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    val movieList = listOf(
        MovieData("이 사랑 통역 되나요", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3),
        MovieData("이 사랑 통역 되나요", R.drawable.img_content1),
        MovieData("이상한일5", R.drawable.img_content2),
        MovieData("하일매리", R.drawable.img_content3)
    )
    fun saveMovie(title: String, imageRes: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val isExist = movieDao.isMovieExist(title)
            if (isExist) {
                _toastMessage.emit("${title}은(는) 이미 보관함에 있는 작품입니다.")
            } else {
                movieDao.insertMovie(MovieEntity(title = title, imageRes = imageRes))
                _toastMessage.emit("${title}을(를) 보관함에 담았습니다!")
            }
        }
    }
}