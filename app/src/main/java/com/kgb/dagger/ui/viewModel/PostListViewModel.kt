package com.kgb.dagger.ui.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.kgb.dagger.base.BaseViewModel
import com.kgb.dagger.data.db.PostDao
import com.kgb.dagger.data.model.Post
import com.kgb.dagger.data.network.RestApi
import com.kgb.dagger.ui.adapter.PostAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {
    @Inject
    lateinit var postApi: RestApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var postAdapter: PostAdapter = PostAdapter()

    val errorMessageRequest: MutableLiveData<String> = MutableLiveData()
    val errorActionListener = View.OnClickListener { loadPosts() }

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.getAll }
            .concatMap { dbPost ->
                if (dbPost.isEmpty()) {
                    postApi.getPosts().concatMap { apiPost ->
                        postDao.insertData(*apiPost.toTypedArray())
                        Observable.just(dbPost)
                    }
                } else {
                    Observable.just(dbPost)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostStart() }
            .doOnTerminate { onRetrievePostFinish() }
            .subscribe(
                { result -> onRetrievePostSuccess(result) },
                { onRetrievePostError() }
            )
    }

    private fun onRetrievePostSuccess(result: List<Post>) {
        postAdapter.setDataList(result)
        errorMessageRequest.value = null
    }


    private fun onRetrievePostError() {
        errorMessageRequest.value = "Request Failed!"
        Log.d("TAG", "onRetrievePostError: ${errorMessageRequest.value}")
    }


    private fun onRetrievePostStart() {
        loadingVisibility.value = View.VISIBLE
        Log.d("TAG", "onRetrievePostStart: ${loadingVisibility.value}")
    }

    private fun onRetrievePostFinish() {
        loadingVisibility.value = View.GONE
        Log.d("TAG", "onRetrievePostFinish: ${loadingVisibility.value}")
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}