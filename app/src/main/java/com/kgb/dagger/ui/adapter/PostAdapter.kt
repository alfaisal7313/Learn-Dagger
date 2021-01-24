package com.kgb.dagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kgb.dagger.R
import com.kgb.dagger.data.model.Post
import com.kgb.dagger.databinding.ItemPostViewBinding
import com.kgb.dagger.ui.view_holder.PostViewHolder

/**
 * Created by Muhammad Al Faisal on 1/23/21.
 */
class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var postData: List<Post>

    fun setDataList(post: List<Post>) {
        this.postData = post
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val bindingItemView: ItemPostViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post_view,
            parent,
            false
        )
        return PostViewHolder(bindingItemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(postData[position])
    }

    override fun getItemCount(): Int {
        return if (::postData.isInitialized) postData.size else 0
    }
}