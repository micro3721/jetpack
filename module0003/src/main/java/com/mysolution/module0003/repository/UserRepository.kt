package com.mysolution.module0003.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mysolution.module0003.model.User
import com.mysolution.module0003.network.ApiService
import com.mysolution.module0003.util.UserPagingSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/7/3
 * Describe:
 */

@Singleton
class UserRepository @Inject constructor(private val apiService: ApiService) {

    fun getUsers(): Pager<Int, User> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(apiService) }
        )
    }
}
