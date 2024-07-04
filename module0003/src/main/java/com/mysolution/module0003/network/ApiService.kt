package com.mysolution.module0003.network


import com.mysolution.module0003.model.User
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/7/3
 * Describe:
 */

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int, @Query("size") size: Int): List<User>
}
