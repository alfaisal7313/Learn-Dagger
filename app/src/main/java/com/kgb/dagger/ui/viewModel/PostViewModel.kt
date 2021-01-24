package com.kgb.dagger.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.kgb.dagger.base.BaseViewModel
import com.kgb.dagger.data.model.Post

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
class PostViewModel : BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun setData(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }
}