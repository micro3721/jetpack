package com.mysolution.module0003.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mysolution.module0003.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/7/3
 * Describe:
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserFragment())
                .commit()
        }
    }
}
