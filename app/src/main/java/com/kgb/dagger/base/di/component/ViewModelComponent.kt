package com.kgb.dagger.base.di.component

import com.kgb.dagger.base.di.module.NetworkModule
import com.kgb.dagger.ui.viewModel.PostListViewModel
import com.kgb.dagger.ui.viewModel.PostViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent {
    fun inject(postListViewModel: PostListViewModel)

    fun inject(postViewModel: PostViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}