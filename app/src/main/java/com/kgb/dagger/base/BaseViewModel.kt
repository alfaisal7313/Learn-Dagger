package com.kgb.dagger.base

import androidx.lifecycle.ViewModel
import com.kgb.dagger.base.di.module.NetworkModule
import com.kgb.dagger.base.di.component.DaggerViewModelComponent
import com.kgb.dagger.base.di.component.ViewModelComponent
import com.kgb.dagger.ui.viewModel.PostListViewModel
import com.kgb.dagger.ui.viewModel.PostViewModel

/**
 * Created by Muhammad Al Faisal on 1/22/21.
 */
abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
        }
    }
}