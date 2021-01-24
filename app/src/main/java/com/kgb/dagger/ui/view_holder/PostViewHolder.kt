package com.kgb.dagger.ui.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.kgb.dagger.data.model.Post
import com.kgb.dagger.databinding.ItemPostViewBinding
import com.kgb.dagger.ui.viewModel.PostViewModel

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
class PostViewHolder(private val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root) {
    private val viewModel = PostViewModel()

    fun bindView(post: Post) {
        viewModel.setData(post)
        binding.viewModel = viewModel
    }
}