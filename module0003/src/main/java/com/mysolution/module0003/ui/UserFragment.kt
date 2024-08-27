package com.mysolution.module0003.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mysolution.module0003.R
import com.mysolution.module0003.ui.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/7/3
 * Describe:
 */

@AndroidEntryPoint
class UserFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val repository = UserRepository()
//        val factory = UserViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.userFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        // Adding LoadStateListener to monitor loading states
        adapter.addLoadStateListener { loadState ->
            val refreshState = loadState.source.refresh
            if (refreshState is LoadState.Loading) {
                Log.d("UserFragment", "Loading data...")
            } else if (refreshState is LoadState.NotLoading && adapter.itemCount > 0) {
                Log.d("UserFragment", "Data received: ${adapter.itemCount} items")
                Log.d("UserFragment", "PagingData: ${adapter.snapshot()}")
            } else if (refreshState is LoadState.NotLoading && adapter.itemCount == 0) {
                Log.d("UserFragment", "No data received")
            } else if (refreshState is LoadState.Error) {
                Log.e(
                    "UserFragment",
                    "Error loading data: ${(refreshState as LoadState.Error).error}"
                )
            }
        }
    }
}
