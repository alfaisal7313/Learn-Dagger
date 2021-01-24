package com.kgb.dagger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Muhammad Al Faisal on 1/22/21.
 */

@Entity
data class Post(var userId: Int,  @field:PrimaryKey val id: Int, val title: String, val body: String)