package com.kgb.dagger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kgb.dagger.data.model.Post

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}