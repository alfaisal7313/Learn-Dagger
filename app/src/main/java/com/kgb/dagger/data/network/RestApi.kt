package com.kgb.dagger.data.network

import com.kgb.dagger.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Muhammad Al Faisal on 1/22/21.
 */
interface RestApi {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}