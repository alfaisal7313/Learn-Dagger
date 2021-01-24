package com.kgb.dagger.ui.viewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.kgb.dagger.data.db.AppDatabase
import java.lang.IllegalArgumentException

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "post")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}