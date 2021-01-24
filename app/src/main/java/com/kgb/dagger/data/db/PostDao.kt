package com.kgb.dagger.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kgb.dagger.data.model.Post

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
@Dao
interface PostDao {
    @get: Query( "SELECT * FROM post")
    val getAll: List<Post>

    @Insert
    fun insertData(vararg users: Post)
}