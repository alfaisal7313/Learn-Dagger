package com.kgb.dagger.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kgb.dagger.R
import com.kgb.dagger.databinding.ActivityPostBinding
import com.kgb.dagger.ui.viewModel.PostListViewModel
import com.kgb.dagger.ui.viewModel.ViewModelFactory

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        setView()
        setViewModel()
    }

    private fun setView() {
        binding.rvListPost.apply {
            layoutManager = LinearLayoutManager(this@PostActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@PostActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
        viewModel.errorMessageRequest.observe(this, {
            val snackBar = Snackbar.make(binding.root, it, Snackbar.LENGTH_INDEFINITE)
            snackBar.setAction("Try Again", viewModel.errorActionListener)
            if (!it.isNullOrBlank()) snackBar.show() else snackBar.dismiss()
        })
        binding.viewModel = viewModel
    }

}